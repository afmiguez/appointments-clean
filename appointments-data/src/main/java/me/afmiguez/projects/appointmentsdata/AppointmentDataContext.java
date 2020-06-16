package me.afmiguez.projects.appointmentsdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"me.afmiguez.projects.appointmentsdata","me.afmiguez.projects.appointmentsdomain"})
public class AppointmentDataContext {
    public static void main(String[] args) {
        SpringApplication.run(AppointmentDataContext.class,args);
    }

/*
    @EventListener(ApplicationReadyEvent.class)
    public void bootstrap(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        System.out.println(applicationContext);
    }*/
}
