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

import sn.morsimplon.dao.IClient;
import sn.morsimplon.dao.IRoles;
import sn.morsimplon.dao.IVillage;
import sn.morsimplon.entities.Client;
import sn.morsimplon.entities.Roles;
import sn.morsimplon.entities.Village;

@Controller
public class RoleController {
	
	//pour l"accès des données
	@Autowired
	private IRoles roledao;
	
	
//===========================================================================Lister roles=================================================
	
	//ModelAndView pour retourner les données et la vue
	//requete pour accéder à la page
	@RequestMapping(value="/Role/listeRole")
	public String listeRole(ModelMap model,
		// affiche la liste par page et par taille 
		@RequestParam(name = "page", defaultValue = "0")int page,
		@RequestParam(name = "size", defaultValue = "6")int size)
		/*@RequestParam(name = "nomFamille", defaultValue = "")String mc)*/ {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		Page<Roles> roles = roledao.findAll(PageRequest.of(page, size));
			//List<Village> villages = villagedao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		model.put("liste_roles", roles.getContent());
			//model.put("liste_villages", villages);
		//on declare village à null pour le formulaire lorsqu'on ne click pas sur edit
		//car à chaque on vérifie si village est null ou pas sinon on remplie le formulaire avec les données reçues
		model.put("role", new Roles());
		// On cré les pages de pagination avec le nombre total de pages
			model.addAttribute("pages", new int[roles.getTotalPages()]);
		// On recupèere la page courante
			model.addAttribute("currentPage", page);
			model.addAttribute("mode", "ajout");
		
		return "role/liste";
	}

//===========================================================================Ajouter user=====================================================
	
	@RequestMapping(value="/Role/addRole", method = RequestMethod.POST)
	public String addRole(int id, String nom) {
		
		//ModelAndView modelandview = new ModelAndView();
		Roles role = new Roles();
		role.setId(id);
		role.setNom(nom);
			//Village village = new Village();
			//village = villagedao.getOne(idVillage);
			//user.setVillage(village);
		
		try {
			roledao.save(role);
			//modelandview.addObject("resultat", new String("Client ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Client non ajouté"));
			e.printStackTrace();
		}
		
		//modelandview.addObject(new String("village/liste"));
		return "redirect:/Role/listeRole";
	}
	
//===========================================================================Supprimer user=================================================
	
	@RequestMapping(value="/Role/supprimer", method = RequestMethod.GET)
	public String supprimer(int id) {
		
		try {
			roledao.delete(roledao.getOne(id));
			//modelandview.addObject("resultat", new String("Client ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Client non ajouté"));
			e.printStackTrace();
		}
		
		return "redirect:/Role/listeRole";
	}
	
//===========================================================================Editer user======================================================
	
	@RequestMapping(value="/Role/editer", method = RequestMethod.GET)
	public String editer(int id, ModelMap model) {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		List<Roles> roles = roledao.findAll();
			//List<Village> villages = villagedao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		model.put("liste_roles", roles);
			//model.put("liste_villages", villages);
		
		Roles role = roledao.getOne(id);
		model.put("role", role);
		
		return "role/liste";
	}

}
