package sn.morsimplon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sn.morsimplon.dao.IUser;
import sn.morsimplon.dao.IRoles;
import sn.morsimplon.entities.User;
import sn.morsimplon.entities.Roles;

@Controller
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
			@RequestParam(name = "size", defaultValue = "5")int size)
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
			
			return "user/liste";
		}

	//===========================================================================Ajouter user=====================================================
		
		@RequestMapping(value="/User/addUser", method = RequestMethod.POST)
		public String addUser(int id, String nom, String prenom, String email, String password, String urlPhoto,
				int etat , int idRole ) {
			
			//ModelAndView modelandview = new ModelAndView();
			User user = new User();
			user.setId(id);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setPassword(password);
			user.setUrlPhoto(urlPhoto);
			user.setEtat(etat);
			
			/*
			 * Village village = new Village(); village = villagedao.getOne(idVillage);
			 */
			//List<Roles> roles = new ArrayList<Roles>();
			//Roles role = new Roles();
			//roles = roledao.getOne(idRole);
			List<Roles> role = roledao.findAll();
			role.get(idRole);
			
			user.setRoles(role);
			try {
				userdao.save(user);
				//modelandview.addObject("resultat", new String("user ajouté"));
			} catch (Exception e) {
				//modelandview.addObject("resultat", new String("Erreur user non ajouté"));
				e.printStackTrace();
			}
			
			//modelandview.addObject(new String("village/liste"));
			return "redirect:/User/listeUser";
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
		
	//===========================================================================Editer user======================================================
		
		@RequestMapping(value="/User/editer", method = RequestMethod.GET)
		public String editer(int id, ModelMap model) {
			
			//le controller recupère la liste des villages avec le model (villagedao)
			List<User> users = userdao.findAll();
			List<Roles> roles = roledao.findAll();
			//et passe la liste au view
			/* return new ModelAndView("village/liste", "liste_village", villages); */
			//pour retourner plusieur élément de sources différentes on utilise ModeMap
			model.put("liste_users", users);
			model.put("liste_roles", roles);
			
			User user = userdao.getOne(id);
			model.put("user", user);
			
			return "user/liste";
		}

}
