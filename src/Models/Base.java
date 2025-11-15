/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author larap
 */
public class Base {
    private int id; 
    private Boolean deleted; 
    
    public Base(int id, Boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }
    
    public Base() {
        this.deleted = false;
    }
    public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
       
    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
