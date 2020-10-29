package sn.morsimplon.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomFamille;
    private String adresse;
    private String numTel;
    @ManyToOne
    @JoinColumn(name="idVillage")//Colonne qui sera créée dans la table
    private Village village = new Village();
    
    
	public Client() {
		super();
	}


	public Client(int id, String nomFamille, String adresse, String numTel, Village village) {
		super();
		this.id = id;
		this.nomFamille = nomFamille;
		this.adresse = adresse;
		this.numTel = numTel;
		this.village = village;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomFamille() {
		return nomFamille;
	}


	public void setNomFamille(String nomFamille) {
		this.nomFamille = nomFamille;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}


	public Village getVillage() {
		return village;
	}


	public void setVillage(Village village) {
		this.village = village;
	}
	
	
	

    
}
