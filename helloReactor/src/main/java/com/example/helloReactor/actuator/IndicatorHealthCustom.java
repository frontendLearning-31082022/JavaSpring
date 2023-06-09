package com.example.helloReactor.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import java.util.Calendar;

@Component
public class IndicatorHealthCustom implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        if (min%2==0)return Health.up().withDetail("reason", "Iam working cool").build();
        if (min%2!=0)return Health.outOfService().withDetail("reason", "tired").build();

        return null;
    }
}