package com.celso.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.celso.crudspring.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
