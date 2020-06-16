package me.afmiguez.projects.appointmentsdata;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"me.afmiguez.projects.appointmentsdata","me.afmiguez.projects.appointmentsdomain"})
public class AppointmentDataContext {

}
