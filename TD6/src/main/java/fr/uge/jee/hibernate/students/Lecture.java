package fr.uge.jee.hibernate.students;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Lectures")
public class Lecture {

    @Id
    @GeneratedValue(generator = "lecture_gen")
    @Column(name = "LECTUREID")
    private Long id;

    @Column(name="LECTURE_NAME")
    private String name;

    //TODO : student list

    public Lecture(){} // for Hibernate

    public Lecture(String name) {
        this.name = Objects.requireNonNull(name);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
