package com.celso.crudspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.celso.crudspring.entity.Course;
import com.celso.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor // gera um construtor que inclui todas as propriedades da classe, incluindo as
                    // dependências.
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return this.courseRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Course findById(@PathVariable Long id) {
        return this.courseRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
    }

    // Não existe uma melhor prática entre a notation ou a response entity, apenas a
    // adaptação de projeto para projeto
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    // public ResponseEntity<Course> create(@RequestBody Course course){
    public Course create(@RequestBody Course course) {
        System.out.println(course.toString());
        return this.courseRepository.save(course);
        // A vantagem do response entity é que vc pode manipular o header do seu
        // response
        // caso não precise utilizar a annotation @responseStatus
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        return this.courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return updated;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
    }

    // @DeleteMapping("/{id}")
    // @ResponseStatus(code = HttpStatus.NO_CONTENT)
    // public void delete(@PathVariable Long id) {
    // this.courseRepository.deleteById(id);
    // }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Course> course = this.courseRepository.findById(id);

        if (course.isPresent()) {
            this.courseRepository.delete(course.get());
        } 
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!");
        }
    }

}
