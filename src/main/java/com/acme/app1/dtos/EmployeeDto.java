package com.acme.app1.dtos;

import com.acme.app1.models.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter                 // lombok
@Setter                 // lombok
public class EmployeeDto {

    @NotBlank(message = "The email is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotBlank(message = "The firstname is required.")
    @Size(min = 3, max = 64, message = "The length of full name must be between 3 and 64 characters.")
    private String firstname;

    @NotBlank(message = "The lastname is required.")
    @Size(min = 3, max = 64, message = "The length of full name must be between 3 and 64 characters.")
    private String lastname;

    public Employee toEmployee() {
        return new Employee()
                .setEmail(email)
                .setFirstname(firstname)
                .setLastname(lastname)
                ;
    }
}
