package fr.uge.jee.hibernate.employees;


public class Application {

    public static void main(String[] args) {
        var employeeRep = new EmployeeRepository();
        employeeRep.create("Bob", "Moran", 500);
        employeeRep.create("Bob", "Dylan", 600);
        var lisaId = employeeRep.create("Lisa", "Simpson", 100);
        employeeRep.create("Marge", "Simpson", 1000);
        var homerId = employeeRep.create("Homer", "Simpson", 450);

        employeeRep.delete(lisaId);
        var homerOpt = employeeRep.get(homerId);
        if (homerOpt.isPresent()){
            var homer =  homerOpt.get();
            employeeRep.update(homerId, homer.getSalary()+100);
        }

        //employeeRep.getAll().forEach(System.out::println);


    /*
        employeeRep.getAll().forEach(e->{
            employeeRep.updateSalary(e.getId(), s-> s < 1000 ? s+100 : s+(s/10) );
            System.out.println(e);
        });
    */

        employeeRep.getAllByFirstName("Bob").forEach(System.out::println);

    }
}
