package com.davidprojects.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidprojects.course.entities.Category;
import com.davidprojects.course.entities.Order;
import com.davidprojects.course.entities.User;
import com.davidprojects.course.entities.enums.OrderStatus;
import com.davidprojects.course.repositories.CategoryRepository;
import com.davidprojects.course.repositories.OrderRepository;
import com.davidprojects.course.repositories.UserRepository;

@Configuration //data annotation do spring para definir uma classe de configuracao
@Profile("test") //Define qual o perfil a ser executado no caso é o test conforme application.propertions
public class TestConfig implements CommandLineRunner{
	
	@Autowired //data annotation do spring que faz injecao de dependencia
	private UserRepository userRepository;
	
	@Autowired //data annotation do spring que faz injecao de dependencia
	private OrderRepository orderRepository;
	
	@Autowired //data annotation do spring que faz injecao de dependencia
	private CategoryRepository categoryRepository;

	@Override // todo codigo dentro desse metodo será executado quando a aplicacao for iniciada
	public void run(String... args) throws Exception {
		
		//CATEGORY
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		//USER
		//Cria dois users para inserir no banco
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Salva os 2 users no banco
		userRepository.saveAll(Arrays.asList(u1,u2));
		
		//ORDER
		//T = time e Z = UTC
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
	
		
		
	}
}
