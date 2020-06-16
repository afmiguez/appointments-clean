package me.afmiguez.projects.appointmentsweb;


import me.afmiguez.projects.appointmentsdata.AppointmentDataContext;
import me.afmiguez.projects.appointmentsservice.AppointmentsServiceApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {AppointmentDataContext.class,AppointmentsServiceApplication.class})
public class AppointmentsWebApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppointmentsWebApplication.class, args);
    }

}
