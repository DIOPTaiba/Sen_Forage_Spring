package sn.morsimplon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import sn.morsimplon.dao.IRoles;
import sn.morsimplon.dao.IUser;
import sn.morsimplon.entities.Roles;
import sn.morsimplon.entities.User;

@SpringBootApplication
public class SpringSenForageApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringSenForageApplication.class, args);
		
		Roles role = new Roles();
		//role.setId(1);
		role.setNom("ROLE_USER");
		/*
		 * List<User> users = new ArrayList<User>(); users.add(user);
		 * role.setUsers(users);
		 */
		
		User user = new User();
		user.setNom("DIOP");
		user.setPrenom("Mor");
		user.setEmail("mor@gmail.sn");
		user.setPassword("passer");
		user.setUrlPhoto("template/images");
		user.setEtat(1);
		List<Roles> roles = new ArrayList<Roles>();
		roles.add(role);
		user.setRoles(roles);
		
		IUser iUser = ctx.getBean(IUser.class);
		try {
			iUser.save(user);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*
		 * IRoles iRoles = ctx.getBean(IRoles.class); try { iRoles.save(role);
		 * System.out.println("ok"); } catch (Exception e) { e.printStackTrace(); }
		 */
		
		/*IUser iUser = ctx.getBean(IUser.class);
		
		User user = new User();
		user.setNom("DIOP");
		user.setPrenom("Mor");
		user.setEmail("mor@gmail.sn");
		user.setPassword("passer");
		user.setUrlPhoto("template/images");
		user.setEtat(1);
		
		try {
			iUser.save(user);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		
	}

}
