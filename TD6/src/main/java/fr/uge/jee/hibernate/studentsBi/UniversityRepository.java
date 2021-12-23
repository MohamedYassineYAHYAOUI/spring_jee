package fr.uge.jee.hibernate.studentsBi;

import fr.uge.jee.hibernate.utility.PersistenceUtils;

public class UniversityRepository implements Repository<University, Long> {


    @Override
    public Class<University> getClazz() {
        return University.class;
    }


    public void deleteUniversity(Long universityId){
        PersistenceUtils.inTransaction(em ->{
            var q = "SELECT s FROM Student s LEFT JOIN FETCH s.university where s.university.id=:id";
            var query = em.createQuery(q, Student.class);
            query.setParameter("id",universityId);
            var tmp = em.find(University.class,universityId);
            var studentList = query.getResultList();
            if(studentList != null ){
                for (var student : studentList) {
                    student.setUniversity(null);
                }
            }
            if(tmp == null){
                return false;
            }
            em.remove(tmp);
            return true;
        });

    }

}
