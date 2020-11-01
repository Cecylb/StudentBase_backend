package com.cecylb.studentbase.demo.web;

import com.cecylb.studentbase.demo.model.Student;
import com.cecylb.studentbase.demo.service.StudentService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;
    private Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/search")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity findStudentById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(studentService.findById(Long.parseLong(id)));
        } catch (NotFoundException e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/add/fields")
    public ResponseEntity getFields() {
        return ResponseEntity.ok(studentService.getFields());
    }

    @PostMapping("/add")
    public void addNewStudent(@Valid @RequestBody Student student) {
        if(student != null) {
            studentService.addStudent(student);
        } else {
            log.error("Invalid student data");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id) {
        try {
            studentService.deleteStudent(Long.parseLong(id));
        } catch (NotFoundException e) {
            log.error(e.toString());
        }
    }
}
