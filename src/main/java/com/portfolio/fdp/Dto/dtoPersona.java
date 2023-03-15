package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPersona {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String imgProfile;

    @NotBlank
    private String tituloPer;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    public dtoPersona() {
    }

    public dtoPersona(String name, String lastname, String imgProfile, String tituloPer, String email, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.imgProfile = imgProfile;
        this.tituloPer = tituloPer;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public String getTituloPer() {
        return tituloPer;
    }

    public void setTituloPer(String tituloPer) {
        this.tituloPer = tituloPer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
