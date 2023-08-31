package com.celso.crudspring.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import com.celso.crudspring.enums.Category;
import com.celso.crudspring.enums.utils.CategoryConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;
// import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Validated
// @Table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = false WHERE id = ?")//Modifica o metodo delete
@Where(clause = "status = true")// toda vez que fizer uma consulta ele adiciona essa clausula status = true no where 
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo não pode ser vazio")
    @NotBlank(message = "O campo não pode ser vazio")
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    // @Pattern(regexp = "Back-End|Front-End")
    @Column(length = 20, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    // @JoinColumn(name = "course_id") não recomendo pelo problema sql n+1
    private List<Lesson> lessons = new ArrayList<>();
}
