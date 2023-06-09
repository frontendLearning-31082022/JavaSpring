package com.example.tacorebuild.controllers;

import com.example.tacorebuild.repos.OrderRepository;
import com.example.tacorebuild.structs.TacoOrder;
import com.example.tacorebuild.variables.OneVal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@ConfigurationProperties("taco.orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private int maxCountLastOrders=20;
    public void setMaxCountLastOrders(int size){this.maxCountLastOrders=size;}
    @Autowired
    OneVal oneVal;
    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";}

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm"; }
        orderRepo.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";	 }

    @GetMapping("/lastOrders")
    public String lastorders(Model model){
        oneVal.getValueOne();

        Pageable pageable = PageRequest.of(0, maxCountLastOrders, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<TacoOrder> finded= orderRepo.findAll(pageable);
        model.addAttribute("lastorders",finded.getContent());
        return "lastorders";
    }

}

