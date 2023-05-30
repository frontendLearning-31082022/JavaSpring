package com.example.tacorebuild.SpringIntegration;

import com.example.tacorebuild.structs.TacoOrder;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

// need deep look
public class SplitterTacoOrder {

    public Collection<Object> splitOrderByParts(TacoOrder to){
        return  Arrays.asList(
                to.getBillingInfo() ,
               to.getOrderItems() );
    }

    @Bean
    @Splitter(inputChannel = "tacoOrderChannel",outputChannel = "splitTacoOrderChannel")
    public SplitterTacoOrder splitterTacoOrder(){
        return new SplitterTacoOrder();
    }

    @Bean
    @Router(inputChannel = "splitTacoOrderChannel")
    public MessageRouter splitTacoOrderRouter(){
        PayloadTypeRouter router=new PayloadTypeRouter();  //classes as key
        router.setChannelMapping("getBillingInfo","billingInfoChannel");
        router.setChannelMapping("getOrderItems","orderItemsChannel");
        return router;
    }

}
