package fr.uge.jee.hibernate.employees;

import javax.persistence.*;


@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEEID")
    private long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "SALARY")
    private int salary;

    public Employee(){} // for Hiberanate

    public Employee(String firstName, String lastName, int salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public long getId() {return id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {return lastName;}

    public int getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}