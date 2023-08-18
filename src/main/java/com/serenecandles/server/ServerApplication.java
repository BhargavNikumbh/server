package com.serenecandles.server;

import com.serenecandles.server.model.Role;
import com.serenecandles.server.model.User;
import com.serenecandles.server.model.UserRole;
import com.serenecandles.server.service.UserService;
import com.serenecandles.server.service.impl.UserDetailsSericeImpl;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

// implements CommandLineRunner
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories
public class ServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//
//		user.setUsername("bhargav");
//		user.setPassword(this.passwordEncoder.encode("Bhargav@1"));
//		user.setFirstName("Bhargav");
//		user.setLastName("Nikumbh");
//		user.setEmail("bhargavnikumbh99@gmail.com");
//		user.setPhone("8975987068");
//		user.setAddress("18, Abhinav Colony, Nagar Pune Road, Ahmednagar");
//
//		Role role = new Role();
//		role.setRoleId(1L);
//		role.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUserName());
//	}
}
