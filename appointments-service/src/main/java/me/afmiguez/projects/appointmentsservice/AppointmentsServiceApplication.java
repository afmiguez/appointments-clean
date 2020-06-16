package me.afmiguez.projects.appointmentsservice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"me.afmiguez.projects.appointmentsservice","me.afmiguez.projects.appointmentsdata"})
public class AppointmentsServiceApplication {
}
