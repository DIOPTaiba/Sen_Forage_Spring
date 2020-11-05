package sn.morsimplon.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sn.morsimplon.dao.IClient;
import sn.morsimplon.dao.IVillage;
import sn.morsimplon.entities.Client;
import sn.morsimplon.entities.Village;

@Controller
public class ClientController {

	//pour l"accès des données
	@Autowired
	private IClient clientdao;
	@Autowired
	private IVillage villagedao;
	
	
//===========================================================================Lister clients=================================================
	
	//ModelAndView pour retourner les données et la vue
	//requete pour accéder à la page
	@RequestMapping(value="/Client/listeClient")
	public String listeClient(ModelMap model,
		// affiche la liste par page et par taille 
		@RequestParam(name = "page", defaultValue = "0")int page,
		@RequestParam(name = "size", defaultValue = "6")int size)
		/*@RequestParam(name = "nomFamille", defaultValue = "")String mc)*/ {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		Page<Client> clients = clientdao.findAll(PageRequest.of(page, size));
		List<Village> villages = villagedao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		model.put("liste_clients", clients.getContent());
		model.put("liste_villages", villages);
		//on declare village à null pour le formulaire lorsqu'on ne click pas sur edit
		//car à chaque on vérifie si village est null ou pas sinon on remplie le formulaire avec les données reçues
		model.put("client", new Client());
		// On cré les pages de pagination avec le nombre total de pages
		model.addAttribute("pages", new int[clients.getTotalPages()]);
	// On recupèere la page courante
		model.addAttribute("currentPage", page);
		model.addAttribute("mode", "ajout");
		
		return "client/liste";
	}

//===========================================================================Ajouter client=====================================================
	
	@RequestMapping(value="/Client/addClient", method = RequestMethod.POST)
	public String addClient(Model model, int id, String nomFamille, String numTel, String adresse, String idVillage) {
		
		//ModelAndView modelandview = new ModelAndView();
		Client client = new Client();
		client.setId(id);
		client.setNomFamille(nomFamille);
		client.setNumTel(numTel);
		client.setAdresse(adresse);
		
		Village village = new Village();
		village = villagedao.getOne(idVillage);
		
		client.setVillage(village);
		try {
			clientdao.save(client);
			//modelandview.addObject("resultat", new String("Client ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Client non ajouté"));
			e.printStackTrace();
		}
		model.addAttribute("mode", "ajout");
		//modelandview.addObject(new String("village/liste"));
		return "redirect:/Client/listeClient";
	}
	
//===========================================================================Supprimer client=================================================
	
	@RequestMapping(value="/Client/supprimer", method = RequestMethod.GET)
	public String supprimer(int id) {
		
		try {
			clientdao.delete(clientdao.getOne(id));
			//modelandview.addObject("resultat", new String("Client ajouté"));
		} catch (Exception e) {
			//modelandview.addObject("resultat", new String("Erreur Client non ajouté"));
			e.printStackTrace();
		}
		
		return "redirect:/Client/listeClient";
	}
	
//===========================================================================Editer client======================================================
	
	@RequestMapping(value="/Client/editer", method = RequestMethod.GET)
	public String editer(int id, ModelMap model,
			// affiche la liste par page et par taille 
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size)
			/*@RequestParam(name = "nomFamille", defaultValue = "")String mc)*/ {
		
		//le controller recupère la liste des villages avec le model (villagedao)
		/* List<Client> clients = clientdao.findAll(); */
		//le controller recupère la liste des villages avec le model (villagedao)
		Page<Client> clients = clientdao.findAll(PageRequest.of(page, size));
		List<Village> villages = villagedao.findAll();
		//et passe la liste au view
		/* return new ModelAndView("village/liste", "liste_village", villages); */
		//pour retourner plusieur élément de sources différentes on utilise ModeMap
		/* model.put("liste_clients", clients); */
		model.put("liste_clients", clients.getContent());
		model.put("liste_villages", villages);
		
		// On cré les pages de pagination avec le nombre total de pages
		model.addAttribute("pages", new int[clients.getTotalPages()]);
	// On recupèere la page courante
		model.addAttribute("currentPage", page);
		
		Client client = clientdao.getOne(id);
		model.put("client", client);
		model.addAttribute("mode", "edit");
		
		return "client/liste";
	}
}
