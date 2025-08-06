package com.learning.springbootWebApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 10, message = "Title should be atleast 2 char long and should be less than 10")
    private String title;

    private Boolean isActive;

    @Past(message = "past date should me mentioned")
    private LocalDate createdAt;
}
