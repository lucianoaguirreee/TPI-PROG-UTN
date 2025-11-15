CREATE DATABASE IF NOT EXISTS pets_microchips
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE pets_microchips;

-- Creación de microchips primero para poder referenciarlos después
CREATE TABLE IF NOT EXISTS microchips (
  id                  BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  deleted             BOOLEAN NOT NULL DEFAULT FALSE,
  code                VARCHAR(25)  NOT NULL UNIQUE,
  implantation_date   DATE         NULL,
  veterinary          VARCHAR(120) NULL,
  observations        VARCHAR(255) NULL,
  -- Controles de datos válidos
  CONSTRAINT chk_micro_deleted     CHECK (deleted IN (0,1)),
  CONSTRAINT chk_code_not_empty    CHECK (TRIM(code) <> '')
) ENGINE=InnoDB;

-- Registro de animales domésticos con vínculo opcional a su identificador electrónico
CREATE TABLE IF NOT EXISTS pets (
  id               BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  deleted          BOOLEAN NOT NULL DEFAULT FALSE,
  name             VARCHAR(60)  NOT NULL,
  species          VARCHAR(30)  NOT NULL,
  breed            VARCHAR(60)  NULL,
  birth_date       DATE         NULL,
  owner            VARCHAR(120) NOT NULL,
  microchip_id     BIGINT UNSIGNED NULL UNIQUE,

  CONSTRAINT fk_pets_microchip
    FOREIGN KEY (microchip_id) REFERENCES microchips (id)
    ON UPDATE RESTRICT ON DELETE RESTRICT,

  -- Verificación de información obligatoria
  CONSTRAINT chk_name_not_empty    CHECK (TRIM(name)    <> ''),
  CONSTRAINT chk_species_not_empty CHECK (TRIM(species) <> ''),
  CONSTRAINT chk_owner_not_empty   CHECK (TRIM(owner)   <> ''),
  CONSTRAINT chk_pet_deleted       CHECK (deleted IN (0,1))
) ENGINE=InnoDB;

-- Trigger que impide reasignar chips entre diferentes mascotas
DELIMITER //

CREATE TRIGGER trg_pets_no_reassign_microchip
BEFORE UPDATE ON pets
FOR EACH ROW
BEGIN
  -- Validar que un chip previamente vinculado no pueda modificarse ni eliminarse
  IF OLD.microchip_id IS NOT NULL
     AND NEW.microchip_id <> OLD.microchip_id THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot change or remove a microchip already assigned to a pet';
  END IF;
END//

DELIMITER ;
