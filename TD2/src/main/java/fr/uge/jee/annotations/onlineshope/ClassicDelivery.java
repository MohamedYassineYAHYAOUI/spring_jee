package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class ClassicDelivery implements Delivery  {

    @Value("${onlineshop.standarddelivery.delay}")
    private int delayInDays;

    /*
    ClassicDelivery(int delayInDays){
        this.delayInDays = delayInDays;
    }
    */
    /*
    public void setdelayInDays(int delayInDays){
        this.delayInDays = delayInDays;
    }*/

    @Override
    public String getDescription() {
        return " Standard Delivery with a delay of "+delayInDays+" days";
    }
}
