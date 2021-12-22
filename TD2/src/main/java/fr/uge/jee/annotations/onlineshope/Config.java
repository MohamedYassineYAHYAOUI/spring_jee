package fr.uge.jee.annotations.onlineshope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Set;

@Configuration
@ComponentScan
@PropertySource(value={"onlineshop.properties"})
public class Config {
/*
    // exercice 1 Question 1
    @Bean
    ClassicDelivery classicDeliveryBean(){
        return new ClassicDelivery(999);
    }

    @Bean
    DroneDelivery droneDeliveryBean(){
        return new DroneDelivery();
    }

    @Bean
    ReturnInsurance returnInsuranceBean(){
        return new ReturnInsurance(Insurance.Beneficiary.MEMBERS);
    }

    @Bean
    TheftInsurance theftInsuranceBean(){
        return new TheftInsurance();
    }

    @Bean
    OnlineShop onlineShopBean(Set<Delivery> deliveryOptions,  Set<Insurance>  insurances){
        return new OnlineShop("Amazon", deliveryOptions, insurances);
    }*/


    // exercice 1 question 2

    // fichier configuration vide

    // exercice 1 question 3


}
