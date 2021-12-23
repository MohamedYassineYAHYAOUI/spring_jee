package fr.uge.jee.hibernate.studentsBi;

public class Application {


    public static void main(String[] args) {
        // ------------INIT REPOSITORIES---------------
        var universitiesRepository = new UniversityRepository();
        var lecturesRepository = new LectureRepository();
        var studentRepository = new StudentRepository();

        //-------------CREATE STUDENTS-----------------
        var momo = studentRepository.create(new Student("Mohamed", "Yahyaoui",
                new Address("Boulevard Gallieni", "Neuilly-Plaisance", 93360, 33), null ));
        var harry = studentRepository.create(new Student("Harry", "Potter",
                new Address("Privet Drive", "Surrey", 533704, 4), null));
        var hermoine = studentRepository.create(new Student("Hermione", "Granger",
                new Address("Canterbury Road", "Surrey", 571845, 34), null));
        var ronald = studentRepository.create(new Student("Ronald", "Weasley",
                new Address("Brynglas Road", "GLENRAZIE", 24165, 99), null));
        var edgar = studentRepository.create(new Student("Edgar", "Mitchell",
                new Address("Brynglas Road", "GLENRAZIE", 52300, 120), null));

        //-------------CREATE UNIVERSITIES--------------
        var mit = universitiesRepository.create(new University("MIT"));
        var hogwarts = universitiesRepository.create(new University("Hogwarts"));
        var uge = universitiesRepository.create(new University("UGE"));

        //--------------CREATE LECTURES--------------------
        var jee = lecturesRepository.create(new Lecture("JEE"));
        var magic101 = lecturesRepository.create(new Lecture("Magic 101"));
        var math = lecturesRepository.create(new Lecture("Math"));
        var astronomy = lecturesRepository.create(new Lecture("Astronomy"));
        var charms = lecturesRepository.create(new Lecture("Charms"));

        //--------------UPDATE UNIVERSITIES---------------
        studentRepository.update(momo.getId(), (e)->{e.setUniversity(uge);});
        studentRepository.update(momo.getId(), (e)->{e.setUniversity(hogwarts);});
        studentRepository.update(momo.getId(), (e)->{e.setUniversity(uge);});

        studentRepository.update(harry.getId(), (e)->{e.setUniversity(hogwarts);});
        studentRepository.update(hermoine.getId(), (e)->{e.setUniversity(hogwarts);});
        studentRepository.update(ronald.getId(), (e)->{e.setUniversity(hogwarts);});
        studentRepository.update(edgar.getId(), (e)->{e.setUniversity(mit);});

        //-------------UPDATE LECTURES--------------------
        studentRepository.update(momo.getId(), (e)->{e.addLectures(jee);});
        studentRepository.update(momo.getId(), (e)->{e.addLectures(math);});
        studentRepository.update(harry.getId(), (e)->{e.addLectures(math);});
        studentRepository.update(harry.getId(), (e)->{e.addLectures(magic101);});
        studentRepository.update(hermoine.getId(), (e)->{e.addLectures(magic101);});

        studentRepository.update(hermoine.getId(), (e)->{e.addLectures(charms);});
        studentRepository.update(ronald.getId(), (e)->{e.addLectures(astronomy);});
        studentRepository.update(ronald.getId(), (e)->{e.addLectures(magic101);});


        //--------------DELETE UNIVERSITIES--------------
        // doit êtes attaché a des étudiant
        universitiesRepository.deleteUniversity(mit.getId());

        //-------------DELETE LECTURES-----------------------
        lecturesRepository.deleteLecture(math.getId());

        //----------------UPDATE ADRESSE----------------------
        var adr = new Address("Brynglas Road", "GLENRAZIE", 24165, 99);
        studentRepository.update(harry.getId(), (e)->{e.setAddress(adr);});
        studentRepository.update(hermoine.getId(), (e)->{e.setAddress(adr);});

        //-----------------ADD COMMENT------------------------
        var comment = new Comment("awesome student");
        studentRepository.update(harry.getId(), (e)->{e.addComments(new Comment("great magician"));});
        studentRepository.update(hermoine.getId(), (e)->{e.addComments(comment);});
        studentRepository.update(ronald.getId(), (e)->{e.addComments( new Comment("awesome student 2"));});
        studentRepository.update(hermoine.getId(), (e)->{e.addComments(new Comment("comment harmoine"));});


        //-----------------DELETE COMMENT--------------------
        studentRepository.deleteComment(hermoine.getId(),comment.getId());

        //---------GET LIST OF STUDENTS BY LECTURE -----------
        lecturesRepository.getStudentsByCourse(astronomy.getId()).forEach(s-> System.out.println(s.getFirstName()));
        //lecturesRepository.getStudentsByCourse(math.getId()).forEach(s-> System.out.println(s.getFirstName()));;

        //------------- GET LECTURES BY STUDENT ----------------
        studentRepository.getLectures(hermoine.getId()).forEach(l-> System.out.println(l.getName()));

        //-----------------DELETE STUDENT------------------------
        // a ajouter les différents compasant a un étudiant
        //studentRepository.deleteStudent(hermoine.getId());
    }
}
