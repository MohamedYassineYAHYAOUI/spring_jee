package fr.uge.jee.aop.students.studentversion;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class RegistrationAspect {

    private final ArrayList<Long> saveToDBTime = new ArrayList<>();
    private final ArrayList<Long> loadFromDBTime = new ArrayList<>();

    // Exercice 3 Question 1
    /*
    @Before("execution(* fr.uge.jee.aop.students.studentversion.RegistrationService.create*(..))")
    public void beforeCreate(JoinPoint jp) throws Throwable{
        System.out.println("Before create");
    }

    @After("execution(* fr.uge.jee.aop.students.studentversion.RegistrationService.create*(..))")
    public void afterCreate(JoinPoint jp) throws Throwable{
        System.out.println("After create");
   }
   */

    // Exercice 3 question 2

    @Around(value="execution(* fr.uge.jee.aop.students.studentversion.RegistrationService.create*(..))")
    public Object aroundStudent(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Calling "+pjp.getSignature().getName()+" with arguments"+
                Arrays.stream(pjp.getArgs()).map(Object::toString).collect(Collectors.joining(",","[","]")));
        var id = pjp.proceed(); // call the method
        System.out.println("Return id "+id+" by "+pjp.getSignature().getName());
        return id;
    }

    // Exercice 3 question 3

    @Around(value="execution(* fr.uge.jee.aop.students.studentversion.RegistrationService.*DB(..))")
    public Object aroundDbMethodes(ProceedingJoinPoint pjp) throws Throwable{

        var startTime = System.currentTimeMillis();
        var ret = pjp.proceed();
        switch (pjp.getSignature().getName()){
            case "loadFromDB":
                loadFromDBTime.add(System.currentTimeMillis()- startTime);
                break;
            case "saveToDB":
                saveToDBTime.add(System.currentTimeMillis()- startTime);
                break;
            default:
                throw new IllegalStateException("unknown methode "+pjp.getSignature().getName());
        }
        return ret;
    }



    @After("execution(* fr.uge.jee.aop.students.studentversion.RegistrationService.printReport(..))")
    public void afterAppExecution() {
        System.out.println("DB timing report:");
        System.out.println(" saveToDB\n  Measured access times :"
                +saveToDBTime.stream().map(a->a.toString()).collect(Collectors.joining(",", "[", "]"))
                +"\n  Average time :"+
                saveToDBTime.stream().reduce((a,b)->a+b).get()/saveToDBTime.size());
        System.out.println(" loadFromDBTime\n   Measured access times :"
                +loadFromDBTime.stream().map(a->a.toString()).collect(Collectors.joining(",", "[", "]"))
                +"\n   Average time :"+
                loadFromDBTime.stream().reduce((a,b)->a+b).get()/loadFromDBTime.size());
    }


}
