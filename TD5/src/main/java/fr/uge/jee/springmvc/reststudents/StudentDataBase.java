package fr.uge.jee.springmvc.reststudents;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class StudentDataBase {

    private final Object lock = new Object();
    private final HashMap<Integer, Student> studentDatabase = new HashMap<>();

    @PostConstruct
    public void init(){
        studentDatabase.put(1, new Student("Mohamed","YAHYAOUI", 24));
        studentDatabase.put(2, new Student("Ruben", "SERO", 21));
        studentDatabase.put(3, new Student("Vincent", "LIN", 22));
    }

    Student getStudent(int studentID){
        synchronized (lock){
            return studentDatabase.get(studentID);
        }
    }

    public List<Student> getAllStudents() {
        synchronized(lock){
            return List.copyOf(studentDatabase.values());
        }
    }
}
