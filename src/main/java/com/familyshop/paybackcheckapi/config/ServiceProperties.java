package com.familyshop.paybackcheckapi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ServiceProperties {
    @Value("${env}")
    private String runningEnv;
}
