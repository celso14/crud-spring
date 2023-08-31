package com.celso.crudspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.celso.crudspring.dtos.input.CourseInputDTO;
import com.celso.crudspring.dtos.output.CourseOutputDTO;
import com.celso.crudspring.entities.Course;
import com.celso.crudspring.exceptions.NotFoundException;
import com.celso.crudspring.repositories.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public List<CourseOutputDTO> list() {
        List<Course> listCourses = this.courseRepository.findAll();

        List<CourseOutputDTO> courseDTOs = listCourses.stream()
                .map(course -> modelMapper.map(course, CourseOutputDTO.class))
                .collect(Collectors.toList());

        // List<CourseOutputDTO> courseDTOs = listCourses.parallelStream()
        // .map(course -> modelMapper.map(listCourses, CourseOutputDTO.class))
        // .collect(Collectors.toList());

        return courseDTOs;
    }

    public CourseOutputDTO findById(Long id) {
        Course course = this.courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id));
        return modelMapper.map(course, CourseOutputDTO.class);
    }

    public CourseOutputDTO create(CourseInputDTO courseInputDTO) {
        Course course = this.courseRepository.save(modelMapper.map(courseInputDTO, Course.class));
        return modelMapper.map(course, CourseOutputDTO.class);
    }

    public CourseOutputDTO update(Long id, CourseInputDTO courseInputDTO) {
        Course course =  this.courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(courseInputDTO.getName());
                    recordFound.setCategory(courseInputDTO.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return updated;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
        return modelMapper.map(course, CourseOutputDTO.class);
    }

    public void delete(Long id) {
        Optional<Course> course = this.courseRepository.findById(id);

        if (course.isPresent()) {
            this.courseRepository.delete(course.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!");
        }
    }
}
