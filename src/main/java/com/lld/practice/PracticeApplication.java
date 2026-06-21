package com.lld.practice;

import com.lld.practice.props.ParkingRateProps;
import com.lld.practice.props.PricingRuleProps;
import com.lld.practice.props.RateCalculationProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ParkingRateProps.class, PricingRuleProps.class, RateCalculationProps.class})
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
