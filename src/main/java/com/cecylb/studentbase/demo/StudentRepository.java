package com.cecylb.studentbase.demo;

import com.cecylb.studentbase.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findById(long id);
}
