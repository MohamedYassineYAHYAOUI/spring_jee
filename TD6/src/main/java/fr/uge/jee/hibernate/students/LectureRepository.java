package fr.uge.jee.hibernate.students;

import fr.uge.jee.hibernate.utility.PersistenceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LectureRepository implements Repository<Lecture, Long>{

    @Override
    public Class<Lecture> getClazz() {
        return Lecture.class;
    }

    public void deleteLecture(Long toDeleteId){
        PersistenceUtils.inTransaction(em->{
            var lectureEntity = em.find(Lecture.class, toDeleteId);
            if(lectureEntity == null){
                return ;
            }
            var q = "SELECT s FROM Student s LEFT JOIN FETCH s.lectures";
            var query = em.createQuery(q,Student.class);
            var students = query.getResultList();
            for(var student : students){
                student.getLectures().remove(lectureEntity);
            }
            em.remove(lectureEntity);
        });
    }

    public List<Student> getStudentsByCourse(Long lectureId){ // doublon de Student !!!!!
        return PersistenceUtils.inTransaction(em->{
            List<Student> studentList = new ArrayList<>();
            var q = "SELECT s FROM Student s LEFT JOIN FETCH s.lectures";
            var query = em.createQuery(q,Student.class);
            var students = query.getResultList();
            var lectureEntity = em.find(Lecture.class, lectureId);
            if(lectureEntity == null){
                throw new IllegalArgumentException();
            }
            for(var student: students){
                if(student.getLectures().contains(lectureEntity)){ // est-ce que ça fait un find ?!!
                    studentList.add(student);
                }
            }
            return studentList;
        });
    }


    /*
    @Override
    public boolean delete(Long toDeleteId) {
        return PersistenceUtils.inTransaction(em-> {
            var request = "SELECT s FROM Students s JOIN Students_by_Lecture sl on s.id = sl.id where sl.id:=id ";
            var query = em.createQuery(request, Student.class);
            query.setParameter("id",toDeleteId);
            var students = query.getResultList();


            var lectureToRemove = em.find(Lecture.class,toDeleteId );
            students.forEach(s->s.getLectures().remove(lectureToRemove));
            em.remove(lectureToRemove);
            return true;
        });
    }*/
}
