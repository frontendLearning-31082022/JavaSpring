package com.example.tacorebuild.structs;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;


import java.util.ArrayList;
import java.util.List;
@Data
public class TacoOrder {
    @NotBlank(message="Required delivery name")
    private String deliveryName;
//    @Pattern(regexp = ".*",message="")
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
//    @CreditCardNumber(message="not valid")
    private String ccNumber;
    private String ccExpiration;
//    @Digits(integer=3, fraction=0, message="invalid CCV")
    private String ccCVV;
    private List<Taco> tacos=new ArrayList<>();



    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}