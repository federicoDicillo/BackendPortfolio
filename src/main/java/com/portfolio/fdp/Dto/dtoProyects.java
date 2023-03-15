
package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;


public class dtoProyects {
    
    @NotBlank
    private String nameProy;
    @NotBlank
    private String detailsProy;

    public dtoProyects() {
    }

    public dtoProyects(String nameProy, String detailsProy) {
        this.nameProy = nameProy;
        this.detailsProy = detailsProy;
    }

    public String getNameProy() {
        return nameProy;
    }

    public void setNameProy(String nameProy) {
        this.nameProy = nameProy;
    }

    public String getDetailsProy() {
        return detailsProy;
    }

    public void setDetailsProy(String detailsProy) {
        this.detailsProy = detailsProy;
    }
    
    
    
    
}
