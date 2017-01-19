package ma.tecma.commerce.dtos;

import ma.tecma.commerce.domain.Produit;

public class ProduitDTO {

	
	private Long id;
	private String nom;
	private String password;
	private String secteur;
	public ProduitDTO(Produit produit) {
		this.id=produit.getId();
		this.nom=produit.getNom();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
