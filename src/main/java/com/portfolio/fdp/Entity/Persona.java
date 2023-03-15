
package com.portfolio.fdp.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String name;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String lastname;
    
    @Size(min = 1, max = 200, message = "Longitud exedida")
    private String imgProfile;

    @Size( max = 50, message ="Longitud exedida")
    private String tituloPer;

    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String email;
    
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String phone;

    public Persona() {
    }

    public Persona(String name, String lastname, String imgProfile, String tituloPer, String email, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.imgProfile = imgProfile;
        this.tituloPer = tituloPer;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
