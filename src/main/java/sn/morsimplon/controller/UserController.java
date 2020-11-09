package sn.morsimplon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sn.morsimplon.dao.IUser;
import sn.morsimplon.dao.IRoles;
import sn.morsimplon.entities.User;
import sn.morsimplon.entities.Client;
import sn.morsimplon.entities.Roles;

@Controller
@EnableWebSecurity
public class UserController {
	
	//pour l"accès des données
		@Autowired
		private IUser userdao;
		@Autowired
		private IRoles roledao;
		
		
	//===========================================================================Lister users=================================================
		
		//ModelAndView pour retourner les données et la vue
		//requete pour accéder à la page
		@RequestMapping(value="/User/listeUser")
		public String listeUser(ModelMap model,
			//affiche la liste par page et par taille
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size)
			/*@RequestParam(name = "nomFamille", defaultValue = "")String mc)*/ {
			
			//le controller recupère la liste des roles avec le model (villagedao)
			Page<User> users = userdao.findAll(PageRequest.of(page, size));
			List<Roles> roles = roledao.findAll();
			//et passe la liste au views
			/* return new ModelAndView("village/liste", "liste_village", villages); */
			//pour retourner plusieur élément de sources différentes on utilise ModeMap
			model.put("liste_users", users.getContent());
			model.put("liste_roles", roles);
			//on declare village à null pour le formulaire lorsqu'on ne click pas sur edit
			//car à chaque on vérifie si village est null ou pas sinon on remplie le formulaire avec les données reçues
			model.put("user", new User());
			// On cré les pages de pagination avec le nombre total de pages
			model.addAttribute("pages", new int[users.getTotalPages()]);
		// On recupèere la page courante
			model.addAttribute("currentPage", page);
			model.addAttribute("mode", "ajout");
			
			return "user/liste";
		}

	//===========================================================================Ajouter user=====================================================
		
		@RequestMapping(value="/User/addUser", method = RequestMethod.POST)
		public String addUser(Model model, int id, String nom, String prenom, String email, String password, String urlPhoto,
				int etat/* , int idRole */, String admin, String commercial, String clientel, String compteur) {
			
			BCryptPasswordEncoder encodepassword = new BCryptPasswordEncoder();
			//PasswordEncoder encodepassword = new BCryptPasswordEncoder();
			String passwordCrypter = encodepassword.encode(password);
			
			//ModelAndView modelandview = new ModelAndView();
			User user = new User();
			user.setId(id);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setPassword(passwordCrypter);
			user.setUrlPhoto(urlPhoto);
			user.setEtat(etat);
			
			
			//List<Roles> roles = new ArrayList<Roles>();
			//Roles role = roledao.getOne(idRole);
			//roles = roledao.getOne(idRole);
			//List<Roles> roles = (List<Roles>) roledao.getRoleById(idRole);
			//Roles role = roles.get(idRole);
			List<Roles> lesroles = roledao.getRolesByNames(admin, commercial, clientel, compteur);
			
			//List<Roles> roles = roledao.getRoleById(idRole);
		
			user.setRoles(lesroles);
			try {
				userdao.save(user);
				//modelandview.addObject("resultat", new String("user ajouté"));
			} catch (Exception e) {
				//modelandview.addObject("resultat", new String("Erreur user non ajouté"));
				e.printStackTrace();
			}
			
			model.addAttribute("mode", "ajout");
			return "redirect:/User/listeUser";
		}
		
	//===========================================================================Editer user======================================================
	
			@RequestMapping(value="/User/editer", method = RequestMethod.GET)
			public String editer(int id, ModelMap model,
					// affiche la liste par page et par taille 
					@RequestParam(name = "page", defaultValue = "0")int page,
					@RequestParam(name = "size", defaultValue = "6")int size)
					/*@RequestParam(name = "nomFamille", defaultValue = "")String mc)*/ {
				
				//le controller recupère la liste des villages avec le model (villagedao)
				Page<User> users = userdao.findAll(PageRequest.of(page, size));
				List<Roles> roles = roledao.findAll();
				//et passe la liste au view
				/* return new ModelAndView("village/liste", "liste_village", villages); */
				//pour retourner plusieur élément de sources différentes on utilise ModeMap
				model.put("liste_users", users);
				model.put("liste_roles", roles);
				
				// On cré les pages de pagination avec le nombre total de pages
				model.addAttribute("pages", new int[users.getTotalPages()]);
			// On recupèere la page courante
				model.addAttribute("currentPage", page);
				
				User user = userdao.getOne(id);
				model.put("user", user);
				model.addAttribute("mode", "edit");
				return "user/liste";
			}
		
	//===========================================================================Supprimer user=================================================
		
		@RequestMapping(value="/User/supprimer", method = RequestMethod.GET)
		public String supprimer(int id) {
			
			try {
				userdao.delete(userdao.getOne(id));
				//modelandview.addObject("resultat", new String("user ajouté"));
			} catch (Exception e) {
				//modelandview.addObject("resultat", new String("Erreur user non ajouté"));
				e.printStackTrace();
			}
			
			return "redirect:/User/listeUser";
		}
		
	

}
