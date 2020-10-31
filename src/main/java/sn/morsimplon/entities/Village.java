package sn.morsimplon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Village implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String idVillage;
    private String nom;
    @OneToMany(mappedBy = "village", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<Client>();
    @ManyToOne
    @JoinColumn(name="idUser")//Colonne qui sera créée dans la table
    private User user = new User();
    
	public Village() {
		super();
	}

	public Village(String idVillage, String nom) {
		super();
		this.idVillage = idVillage;
		this.nom = nom;
	}


	public String getIdVillage() {
		return idVillage;
	}

	public void setIdVillage(String idVillage) {
		this.idVillage = idVillage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	
    
    
    
}
