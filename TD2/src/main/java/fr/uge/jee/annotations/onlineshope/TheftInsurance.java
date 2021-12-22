package fr.uge.jee.annotations.onlineshope;

import org.springframework.stereotype.Component;

@Component
public class TheftInsurance implements Insurance{

    @Override
    public String getDescription() {
        return "Theft Assurance";
    }
}
