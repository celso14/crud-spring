package com.celso.crudspring.dtos.output;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import com.celso.crudspring.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Validated
public class CourseOutputDTO {

    Long id;

    @NotBlank
    @NotNull
    String name;

    @NotNull
    @Length(min = 5, max = 100)
    @Pattern(regexp = "Back-End|Front-End")
    Category category;

    List<LessonOutputDTO> lessons;
}
