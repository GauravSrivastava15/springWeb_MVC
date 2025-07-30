package com.learning.springbootWebApplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.springbootWebApplication.annotations.EmployeeRoleValidation;
import com.learning.springbootWebApplication.annotations.PrimeNoValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name is a required field")
    @Size(min = 3, max = 10, message = "Number of character in name should be in the range: [3, 10]")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "You must Enter Age")
    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age of employee should be greater than 18")
    private Integer age;

    @NotBlank(message = "You need to mention the role")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;  // only two roles can be there ADMIN, USER

    @NotNull(message = "You need to pass the salary")
    @Positive(message = "Salary of the employee should be positive")
    private Double salary;

    @PastOrPresent(message = "Date should be past or present")
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;

//    @NotNull(message = "You must enter a number")
//    @PrimeNoValidation
//    private Integer no;


}
