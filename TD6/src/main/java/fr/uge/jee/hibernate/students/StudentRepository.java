package fr.uge.jee.hibernate.students;

import fr.uge.jee.hibernate.employees.Employee;
import fr.uge.jee.hibernate.utility.PersistenceUtils;

import java.util.List;
import java.util.Optional;

public class StudentRepository implements Repository<Student, Long> {

    //private final LectureRepository lectureRepository;
    //private final UniversityRepository universityRepository;

    /*
    public StudentRepository(LectureRepository lectureRepository, UniversityRepository universityRepository){
        this.lectureRepository = lectureRepository;
        this.universityRepository = universityRepository;
    }*/

    @Override
    public Class<Student> getClazz() {
        return Student.class;
    }


    public boolean deleteComment(Long studentId, Long commentId){
        return PersistenceUtils.inTransaction(em->{
            var q = "SELECT s FROM Student s LEFT JOIN FETCH s.comments where s.id=:id";
            var query = em.createQuery(q, Student.class);
            query.setParameter("id", studentId);
            var studentEntity = query.getSingleResult();
            if(studentEntity == null){
                throw new IllegalArgumentException();
            }
            var commentEntity = em.find(Comment.class, commentId);

            studentEntity.getComments().remove(commentEntity);
            em.remove(commentEntity);
            return true;
        });
    }

    public List<Lecture> getLectures(Long studentId){
        return PersistenceUtils.inTransaction(em->{
            var q  ="SELECT s FROM Student s LEFT JOIN FETCH s.comments where s.id=:id";
            var query = em.createQuery(q, Student.class);
            query.setParameter("id", studentId);
            var studentEntity = query.getSingleResult();
            if(studentEntity == null){
                throw new IllegalArgumentException();
            }
            return List.copyOf(studentEntity.getLectures());
        });
    }

    public boolean deleteStudent(Long studentId){
        return PersistenceUtils.inTransaction(em->{
            var q  ="SELECT s FROM Student s LEFT JOIN FETCH s.comments LEFT JOIN FETCH s.lectures where s.id=:id";
            var query = em.createQuery(q, Student.class);
            query.setParameter("id", studentId);
            var studentEntity = query.getSingleResult();
            if(studentEntity == null){
                throw new IllegalArgumentException();
            }
            em.remove(studentEntity);

            return false;
        });
    }

}
