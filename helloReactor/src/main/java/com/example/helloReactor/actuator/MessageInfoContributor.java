package com.example.helloReactor.actuator;

import com.example.helloReactor.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class MessageInfoContributor implements InfoContributor {
    @Autowired
    MessageRepo mr;

    @Override
    public void contribute(Info.Builder builder) {
        long countMessages=mr.findAll().count().block();
        builder.withDetail("msg-cnt",
                Map.of("count",countMessages));
    }
}