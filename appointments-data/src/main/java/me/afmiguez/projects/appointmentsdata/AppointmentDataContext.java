package me.afmiguez.projects.appointmentsdata;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

//@Profile("spring")
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "me.afmiguez.projects.appointmentsdata")
public class AppointmentDataContext {

}
