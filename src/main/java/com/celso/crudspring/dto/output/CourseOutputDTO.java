package com.celso.crudspring.dto.output;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Validated
public class CourseOutputDTO {
    Long id;
    @NotBlank @NotNull String name;
    @NotNull @Length(min = 5, max = 100) @Pattern(regexp = "Back-End|Front-End") String category;
}
