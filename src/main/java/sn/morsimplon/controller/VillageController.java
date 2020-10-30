package sn.morsimplon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sn.morsimplon.dao.IVillage;
import sn.morsimplon.entities.Village;

@Controller
public class VillageController {

	//pour l"accès des données
	@Autowired
	private IVillage villagedao;
	
	//ModelAndView pour retourner les données et la vue
	//requete pour accéder à la page
	@RequestMapping(value="Village/listeVillage")
	public ModelAndView listeVillage() {
		//le controller recupère la liste des villages avec le model (villagedao)
		List<Village> villages = villagedao.findAll();
		//et passe la liste au view
		return new ModelAndView("village/liste", "liste_village", villages);
	}
	
	
}
