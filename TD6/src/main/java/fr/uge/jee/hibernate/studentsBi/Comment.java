package fr.uge.jee.hibernate.studentsBi;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
public class Comment { //serializable ?

    @Id
    @GeneratedValue
    @Column(name = "COMMENTID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name= "STUDENT_OWNER")
    private Student owner;

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
