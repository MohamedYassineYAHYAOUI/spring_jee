package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OnlineShop {

    @Value("${onlineshop.name}")
    private String name;
    @Autowired
    private Set<Delivery> deliveryOptions;
    @Autowired
    private Set<Insurance> insurances;

    /*
    public OnlineShop(String name){
        this.name = name;
    }*/
/*
    public void setdeliveryOptions(Set<Delivery> deliveryOptions){
        this.deliveryOptions = deliveryOptions;
    }

    public void setinsurances(Set<Insurance> insurances){
        this.insurances = insurances;
    }
*/
    public void printDescription(){
        System.out.println(name);
        System.out.println("Delivery options");
        deliveryOptions.forEach(opt -> System.out.println("\t"+opt.getDescription()));
        System.out.println("insurance options");
        insurances.forEach(insurance -> System.out.println("\t"+insurance.getDescription()));
    }

}
