package com.mthree.school_jpa.controller;

import com.mthree.school_jpa.dao.StudentRepo;
import com.mthree.school_jpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> students = studentRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student = studentRepo.findById(id).orElse(null);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    //They used /add to make easier for humans writing HTTP rwquests and code readers to quickly recognise Add endpoint.
    @PostMapping("/add")
    public ResponseEntity<Void> addNewStudent(@RequestBody Student student) {
        studentRepo.save(student);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id) {
        studentRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //Similarly for readability
    //Id unused here since they decided to simply keep in request body.
    //Not required, as long as user knows what to do.
    //Consistency is best through, both add and update should have similar format (w/ wo/ id).
    //Consistency = prevent confusion and mistakes.
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentRepo.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

}
