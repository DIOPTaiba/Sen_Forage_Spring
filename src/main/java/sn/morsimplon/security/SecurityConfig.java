package sn.morsimplon.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//La source de données qui permet d'effectuer des requete sql
	@Autowired
	private DataSource dataSource;
	
	// Pour la récupération des infos du user connecté
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			// On vérifie l'email, le password et l'état à partir de l'email reçu au niveau de la table user
			.usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM user WHERE email=?")
			// On vérifie le role (r.nom=>nomRole) correspondant au useer à partir de son email 
			.authoritiesByUsernameQuery("SELECT u.email as principal, r.nom as role FROM user u, roles r, user_role ur WHERE u.id = ur.id_user AND r.id = ur.id_role AND u.email = ?")
			//utilisé lorsqu'on fait de l"encodage avec Md5
			//.passwordEncoder(new Md5PasswordEncoder())
			// Les roles au niveau de la base de données sont préfixés ROLE_
			.rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.formLogin(); //pour afficher un formulaire par défaut
		try {
			http.formLogin().loginPage("/login"); // On personnalise la page login
			// Les droits pour la Gestionnaire des clients
			http.authorizeRequests().antMatchers("/Client/**", "/Village/**").hasRole("CLIENTEL");
			// Les droits pour la Gestionnaire commerciale
			http.authorizeRequests().antMatchers("/Client/**").hasRole("COMMERCIAL");
			// Les droits pour la Gestionnaire des compteurs
			http.authorizeRequests().antMatchers("/Client/**").hasRole("COMPTEUR");
			// Les droits pour le role Admin
			http.authorizeRequests().antMatchers("/User/**", "/Roles/**").hasRole("ADMIN");
			// Redirection vers la page 403 si le user n'a pas les droits
			http.exceptionHandling().accessDeniedPage("/Admin/403");
			http.csrf().disable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
