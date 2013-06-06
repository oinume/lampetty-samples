package net.lampetty.samples.spring.mvc.form;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
