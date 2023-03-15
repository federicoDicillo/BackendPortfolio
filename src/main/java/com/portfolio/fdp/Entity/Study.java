package com.portfolio.fdp.Entity;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int idStudy;

    @NotNull
    @Size (max = 80, message = "Demasiados caracteres")
    private String titulo;
    
    @NotNull
    @Size (max = 80, message = "Demasiados caracteres")
    private String escuela;
    
    @NotNull
    @Size (max = 80, message = "Demasiados caracteres")
    private String tiempo;
    
    @NotNull
    @Size (max=20)
    private String nivel;
    
    public Study() {
    }

    public Study(String titulo, String escuela, String tiempo, String nivel) {
        this.titulo = titulo;
        this.escuela = escuela;
        this.tiempo = tiempo;
        this.nivel = nivel;
    }

    
       public int getIdStudy() {
        return idStudy;
    }

    public void setIdStudy(int idStudy) {
        this.idStudy = idStudy;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

   
    
}
