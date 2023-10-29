package com.example.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    @NotNull
    @NotBlank(message = "Please enter a name")
    private String name;
    @NotBlank(message = "email is mandatory")
    @Email(message = "please enter a valid email")
    private String email;
    @NotBlank(message = "please enter a strong password eg : dgA12#hsd")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "please enter a valid password eg : 2137kjdDFH&&")
    private String pass;

    @NotBlank(message = "enter phone-number")
    @Length(min = 10 , max = 10,message = "please enter a 10 digit phone number")
    private String phoneNum;
}
