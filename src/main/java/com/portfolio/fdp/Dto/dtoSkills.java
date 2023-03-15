
package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;


public class dtoSkills {
    @NotBlank
    private String unaSkill;

    public String getUnaSkill() {
        return unaSkill;
    }

    public void setUnaSkill(String unaSkill) {
        this.unaSkill = unaSkill;
    }

    public dtoSkills(String unaSkill) {
        this.unaSkill = unaSkill;
    }

    public dtoSkills() {
    }

    
}
