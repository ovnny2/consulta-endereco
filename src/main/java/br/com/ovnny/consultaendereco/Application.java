package br.com.ovnny.consultaendereco;

import br.com.ovnny.consultaendereco.controller.ConsultaEnderecoController;
import br.com.ovnny.consultaendereco.exception.NotFoundException;
import br.com.ovnny.consultaendereco.handler.CustomMessageError;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(
        basePackages = "br.com.ovnny.consultaendereco",
        basePackageClasses = { ConsultaEnderecoController.class, NotFoundException.class, CustomMessageError.class },
        lazyInit = true
)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}