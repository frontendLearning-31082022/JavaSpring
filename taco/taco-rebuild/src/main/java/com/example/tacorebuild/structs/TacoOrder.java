package com.example.tacorebuild.structs;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.CreditCardNumber;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class TacoOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
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
    private Date createdAt=new Date();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos=new ArrayList<>();

    public void addTaco(Taco taco) {
        taco.setTacoOrder(this);
        this.tacos.add(taco);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}