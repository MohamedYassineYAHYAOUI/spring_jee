package fr.uge.jee.hibernate.studentsBi;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(generator = "student_gen")
    @Column(name = "STUDENTID")
    private long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    //@ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY) // est-ce que CascadeType.All supprime l'objet dans la BDD si Student est supprimé ?
    //@JoinColumn(name="Address_Id") // est-ce que c'est la bonne position de JoinColumn
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY) // il ne faut pas mettre cascade= CascadeType.PERSIST, car on ne veut pas créer une université pour chaque nouvel étudiant
    @JoinColumn(name="University_Id") // sans cela ça crée un
    private University university;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "owner") // par défaut Lazy
    @JoinColumn(name="Student_Id")
    @OrderColumn
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany  // par défaut Lazy pas de persist
    @JoinTable(name="Students_by_Lecture",
            joinColumns = @JoinColumn(name="Student_Id"),
    inverseJoinColumns = @JoinColumn(name="Lecture_Id"))
    private Set<Lecture> lectures = new HashSet<>();


    public Student(){}

    public Student(String firstName, String lastName, Address address, University university){
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = Objects.requireNonNull(address);
        this.comments = new ArrayList<Comment>();
        this.university = university; // can be null
        this.lectures = new HashSet<Lecture>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        Objects.requireNonNull(comment);
        this.comments.add(comment);
    }

    public long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void addLectures(Lecture lectures) {
        this.lectures.add(lectures);
    }

    public University getUniversity() {
        return university;
    }
}

