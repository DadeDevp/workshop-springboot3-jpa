package com.davidprojects.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidprojects.course.entities.Category;
import com.davidprojects.course.entities.Order;
import com.davidprojects.course.entities.OrderItem;
import com.davidprojects.course.entities.Payment;
import com.davidprojects.course.entities.Product;
import com.davidprojects.course.entities.User;
import com.davidprojects.course.entities.enums.OrderStatus;
import com.davidprojects.course.repositories.CategoryRepository;
import com.davidprojects.course.repositories.OrderItemRepository;
import com.davidprojects.course.repositories.OrderRepository;
import com.davidprojects.course.repositories.ProductRepository;
import com.davidprojects.course.repositories.UserRepository;

@Configuration //data annotation do spring para definir uma classe de configuracao
@Profile("test") //Define qual o perfil esta classe vai ser executada no caso é o test conforme application.propertions
public class TestConfig implements CommandLineRunner{
	
	@Autowired //data annotation do spring que faz injecao de dependencia
	private UserRepository userRepository;
	
	@Autowired 
	private OrderRepository orderRepository;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	

	@Override // todo codigo dentro desse metodo será executado quando a aplicacao for iniciada
	public void run(String... args) throws Exception {
		
		//CATEGORY
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//inserir o produto nas categorias
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//Salvar os produtos com as associacoes
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
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
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
	
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		//CRIAR UM PAYMENT
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		//Associar o payment no order
		o1.setPayment(pay1);
		//Salvar as alteraçoes no pedido no banco
		orderRepository.save(o1);
		
		
	}
}
