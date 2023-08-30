package com.celso.crudspring.dto.input;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Validated
public class CourseInputDTO {
    Long id;
    @NotBlank @NotNull String name;
    @NotNull @Length(min = 5, max = 100) @Pattern(regexp = "Back-End|Front-End") String category;
}
