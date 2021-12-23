package fr.uge.jee.hibernate.students;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
public class Comment { //serializable ?

    @Id
    @GeneratedValue
    @Column(name = "COMMENTID")
    private long id;


    @Column(name = "COMMENT")
    private String comment;

    public Comment() {}

    public Comment(String comment) {
        this.comment = comment;
    }


    public String getComment() {
        return comment;
    }

    public long getId() {
        return id;
    }
}
