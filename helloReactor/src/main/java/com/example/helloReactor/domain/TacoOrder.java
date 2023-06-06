package com.example.helloReactor.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

import static java.util.Map.entry;

@Data
@Table("tacoorders")
public class TacoOrder {
    @Id
    private Long id;

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

    private List<Taco> tacos=new ArrayList<>();

    public void addTaco(Taco taco) {
        taco.setTacoOrder(this);
        this.tacos.add(taco);
    }

    public Map<String,Object> getBillingInfo(){
        return Map.ofEntries(
                entry("id", id),
                entry("deliveryName", deliveryName),
                entry("deliveryStreet", deliveryStreet),
                entry("deliveryCity", deliveryCity),
                entry("deliveryState", deliveryState),
                entry("deliveryZip", deliveryZip),
                entry("createdAt", createdAt)
        );
    }

    public List<Object> getOrderItems(){
        return Collections.singletonList(tacos);
    }
    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}