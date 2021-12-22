package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReturnInsurance implements Insurance  {


    private Beneficiary beneficiary;

    ReturnInsurance(@Value("${onlineshop.returninsurance.membersonly}") boolean returnType){
        this.beneficiary = returnType ? Beneficiary.ALL : Beneficiary.MEMBERS;
    }


    /*
    public void setBeneficiary(Beneficiary beneficiary){
        this.beneficiary = beneficiary;
    }
    */
    @Override
    public String getDescription() {
        return "Return insurance "+beneficiary;
    }
}
