package br.com.rodrigoeduque.ApiControleVacinacaoBrasil;

import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity.Usuario;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.repository.UsuarioRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ApiControleVacinacaoBrasilApplication {

	/*@Bean
	public CommandLineRunner run(@Autowired UsuarioRepository usuarioRepository){
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Rodrigo Eustáquio Duque");
			usuario.setCpf("07443573650");
			usuario.setEmail("rodrigoeduque@hotmail.com");
			usuario.setDataNascimento(LocalDate.of(Integer.parseInt("1985"),Integer.parseInt("08"),Integer.parseInt("01")));
			//usuario.setDataCadastro(LocalDate.now());
			usuarioRepository.save(usuario);
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ApiControleVacinacaoBrasilApplication.class, args);
	}

}
