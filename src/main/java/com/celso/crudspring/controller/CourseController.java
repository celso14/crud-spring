package com.celso.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.celso.crudspring.dto.input.CourseInputDTO;
import com.celso.crudspring.dto.output.CourseOutputDTO;
import com.celso.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@Validated
@AllArgsConstructor // gera um construtor que inclui todas as propriedades da classe, incluindo as
                    // dependências.
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseOutputDTO> list() {
        return this.courseService.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CourseOutputDTO findById(@PathVariable @NotNull @Positive Long id) {
        return this.courseService.findById(id);
    }

    // Não existe uma melhor prática entre a notation ou a response entity, apenas a
    // adaptação de projeto para projeto
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    // public ResponseEntity<Course> create(@RequestBody Course course){
    public CourseOutputDTO create(@RequestBody @Valid CourseInputDTO courseInputDTO) {
        return this.courseService.create(courseInputDTO);
        // A vantagem do response entity é que vc pode manipular o header do seu
        // response
        // caso não precise utilizar a annotation @responseStatus
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public CourseOutputDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseInputDTO courseInputDTO) {
        return this.courseService.update(id, courseInputDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        this.courseService.delete(id);
    }

}
