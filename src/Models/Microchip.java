package Models;

import java.time.LocalDate;




public class Microchip extends Base {

    private String code;           // NOT NULL, UNIQUE, máx. 25
    private LocalDate implantationDate;
    private String veterinaryClinic;      // máx. 120
    private String observations;    // máx. 255

    /**
     * Constructor por defecto para crear un microchip nuevo.
     * El ID será asignado por la BD al insertar.
     * El flag eliminado se inicializa en false por Base.
     */
    public Microchip() {
        super();
    }
    /**
     * Constructor completo para reconstruir un Microchip desde la base de datos.
     * Usado por MascotaDAO y MicrochipDAO al mapear ResultSet.
     */
    public Microchip(int id, String code, LocalDate implantationDate,
                     String veterinaryClinic, String observations) {
        super(id, false); // Llama al constructor de Base con eliminado=false
        this.code = code;
        this.implantationDate = implantationDate;
        this.veterinaryClinic = veterinaryClinic;
        this.observations = observations;
    }


    public String getCode() {
        return code;
    }
    /**
     * Establece el codigo.
     * Validación: MicrochipServiceImpl verifica que no esté vacío.
     */
    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getImplantationDate() {
        return implantationDate;
    }
    /**
     * Establece la fecha de implantacion.
    */
    public void setImplantationDate(LocalDate implantationDate) {
        this.implantationDate = implantationDate;
    }

    public String getVeterinaryClinic() {
        return veterinaryClinic;
    }
    /**
     * Establece la veterinaria.
    */
    public void setVeterinaryClinic(String veterinaryClinic) {
        this.veterinaryClinic = veterinaryClinic;
    }

    public String getObservations() {
        return observations;
    }
    /**
     * Establece las observaciones.
    */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Microchip{" +
                "id=" + getId() +
                ", deleted=" + isDeleted() +
                ", code='" + code + '\'' +
                ", implantationDate=" + implantationDate +
                ", veterinaryClinic='" + veterinaryClinic + '\'' +
                ", observations='" + observations + '\'' +
                '}';
    }
}
