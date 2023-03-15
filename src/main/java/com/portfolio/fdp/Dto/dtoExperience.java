package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;

public class dtoExperience {

    @NotBlank
    private String puesto;
    @NotBlank
    private String empresa;
    @NotBlank
    private String tiempo;

    public dtoExperience() {
    }

    public dtoExperience(String puesto, String empresa, String tiempo) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.tiempo = tiempo;
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
