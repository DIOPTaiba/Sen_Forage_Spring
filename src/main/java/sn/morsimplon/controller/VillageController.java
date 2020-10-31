package sn.morsimplon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.morsimplon.dao.IUser;
import sn.morsimplon.dao.IVillage;
import sn.morsimplon.entities.User;
import sn.morsimplon.entities.Village;

@Controller
public class VillageController {

	//pour l"accès des données
	@Autowired
	private IVillage villagedao;
	@Autowired
	private IUser userdao;
	
//===========================================================================Lister village=================================================
	
	//ModelAndView pour retourner les données et la vue
	//requete pour accéder à la page
	@RequestMapping(value="/Village/listeVillage")
	public String listeVillage(ModelMap model) {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		List<Village> villages = villagedao.findAll();
		List<User> users = userdao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		model.put("liste_village", villages);
		model.put("liste_users", users);
		//on declare village à null pour le formulaire lorsqu'on ne click pas sur edit
		//car à chaque on vérifie si village est null ou pas sinon on remplie le formulaire avec les données reçues
		model.put("village", new Village());
		
		return "village/liste";
	}

//===========================================================================Ajout village=====================================================
	
	@RequestMapping(value="/Village/addVillage", method = RequestMethod.POST)
	public String addVillage(String idVillage, String nom, int idUser) {
		
		//ModelAndView modelandview = new ModelAndView();
		Village village = new Village();
		village.setIdVillage(idVillage);
		village.setNom(nom);
		
		User user = new User();
		user = userdao.getOne(idUser);
		
		village.setUser(user);
		try {
			villagedao.save(village);
			//modelandview.addObject("resultat", new String("Village ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Village non ajouté"));
			e.printStackTrace();
		}
		
		//modelandview.addObject(new String("village/liste"));
		return "redirect:/Village/listeVillage";
	}
	
//===========================================================================Supprimer village=================================================
	
	@RequestMapping(value="/Village/supprimer", method = RequestMethod.GET)
	public String supprimer(String id) {
		
		try {
			villagedao.delete(villagedao.getOne(id));
			//modelandview.addObject("resultat", new String("Village ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Village non ajouté"));
			e.printStackTrace();
		}
		
		return "redirect:/Village/listeVillage";
	}
	
//===========================================================================Editer village======================================================
	
	@RequestMapping(value="/Village/editer", method = RequestMethod.GET)
	public String editer(String id, ModelMap model) {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		List<Village> villages = villagedao.findAll();
		List<User> users = userdao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		model.put("liste_village", villages);
		model.put("liste_users", users);
		
		Village village = villagedao.getOne(id);
		model.put("village", village);
		
		return "village/liste";
	}
	
	
}
