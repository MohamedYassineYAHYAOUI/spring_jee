package fr.uge.jee.springmvc.reststudents;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    private final StudentDataBase studentDataBase = new StudentDataBase();

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent(@PathVariable("id") int id){
        var student = studentDataBase.getStudent(id);
        if (student == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id "+id+" not available");
        }
        return student;
    }


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getStudents(){
        return studentDataBase.getAllStudents();
    }
}
