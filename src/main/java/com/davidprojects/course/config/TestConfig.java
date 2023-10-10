package com.davidprojects.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidprojects.course.entities.User;
import com.davidprojects.course.repositories.UserRepository;

@Configuration //data annotation do spring para definir uma classe de configuracao
@Profile("test") //Define qual o perfil a ser executado no caso é o test conforme application.propertions
public class TestConfig implements CommandLineRunner{
	
	@Autowired //data annotation do spring que faz injecao de dependencia
	private UserRepository userRepository;

	@Override // todo codigo dentro desse metodo será executado quando a aplicacao for iniciada
	public void run(String... args) throws Exception {
		
		//Cria dois users para inserir no banco
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Salva os 2 users no banco
		userRepository.saveAll(Arrays.asList(u1,u2));
		
	}
}
