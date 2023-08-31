package com.celso.crudspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.celso.crudspring.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
