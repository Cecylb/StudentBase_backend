package com.cecylb.studentbase.demo.service;

import com.cecylb.studentbase.demo.StudentRepository;
import com.cecylb.studentbase.demo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(students::add);
        return students;
    }

    public List<Student> findById(long id) throws NotFoundException {
        Student student = studentRepository.findById(id);
        if(student == null) throw new NotFoundException("No student with id " + id);
        return new ArrayList<>() {{ add(student); }};
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(long id) throws NotFoundException {
        if(studentRepository.findById(id) != null) {
            studentRepository.deleteById(id);
        } else {
            throw new NotFoundException("No student with id " + id);
        }
    }

    public String[] getFields() {
        return Arrays.stream(Student.class.getDeclaredFields()).map(Field::getName).filter(name -> !name.equals("student_id")).toArray(String[]::new);
    }
}
