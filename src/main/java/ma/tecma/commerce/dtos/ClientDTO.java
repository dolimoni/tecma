package ma.tecma.commerce.dtos;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

	private Long id;
	private String nom;
	private String password;
	private String secteur;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private List<CommandeDTO> commandes = new ArrayList<CommandeDTO>();
	
	public List<CommandeDTO> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<CommandeDTO> commandes) {
		this.commandes = commandes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecteur() {
		return secteur;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	
	
}
