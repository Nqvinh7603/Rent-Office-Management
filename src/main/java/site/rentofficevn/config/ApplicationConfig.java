package site.rentofficevn.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
@ComponentScan(basePackages = "site.rentofficevn")
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Filter siteMeshFilter(){
        return new MySiteMeshFilter();
    }
}
