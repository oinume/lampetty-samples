package net.lampetty.samples.spring.mvc.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String name;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
