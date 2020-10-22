package se.ecutb.loffe.webservices.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class User {

    @Id
    private String id;
    // @Field("fname") // name of field in db
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "Firstname length must be 3 to 10 characters")
    private String firstname;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "Lastname length must be 3 to 10 characters")
    private String lastname;
    @Past(message = "Birthday cannot be present or in the future")
    private LocalDate birthdate;
    @Email(message = "Not a valid email address")
    private String mail;
    @Indexed(unique = true)
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 3, max = 15)
    private String username;
    private String password;
    @Pattern(regexp = "[0-9]*", message = "Phone number not valid")
    //@Pattern(regexp = "([0-9]){2-4}-([0-9])", message = "Phone number not valid")
    private String phone;

}