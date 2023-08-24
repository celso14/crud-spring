package com.celso.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.celso.crudspring.entity.Course;
import com.celso.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor//gera um construtor que inclui todas as propriedades da classe, incluindo as dependências.
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }
    
    //Não existe uma melhor prática entre a notation ou a response entity, apenas a adaptação de projeto para projeto
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    // public ResponseEntity<Course> create(@RequestBody Course course){
    public Course create(@RequestBody Course course){
        System.out.println(course.toString());
        return courseRepository.save(course);
        //A vantagem do response entity é que vc pode manipular o header do seu response
        //caso não precise utilizar a annotation @responseStatus
    }
}
