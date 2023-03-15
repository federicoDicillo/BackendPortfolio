package com.portfolio.fdp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int idExp;
    
    @NotNull
    @Size (max = 80)
    private String puesto;
    
    @NotNull
    @Size (max = 80)
    private String empresa;
    
    @NotNull
    @Size (max = 80)
    private String tiempo;
    
    public Experience() {
    }

    public Experience(String puesto, String empresa, String tiempo) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.tiempo = tiempo;
    }

  

    public int getIdExp() {
        return idExp;
    }

    public void setIdExp(int idExp) {
        this.idExp = idExp;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }


    
    
    
    
    
}
