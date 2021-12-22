package fr.uge.jee.annotations.onlineshope;


import org.springframework.stereotype.Component;


public class DroneDelivery implements Delivery{

    @Override
    public String getDescription() {
        return "Delivery by drone";
    }
}
