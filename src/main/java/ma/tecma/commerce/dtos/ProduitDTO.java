package ma.tecma.commerce.dtos;

import ma.tecma.commerce.domain.Produit;

public class ProduitDTO {

	
	private Long id;
	private String nom;
	private String prix;
	private String secteur;
	private int quantiteStock;
	public ProduitDTO() {
		// TODO Auto-generated constructor stub
	}
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
	public String getSecteur() {
		return secteur;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public int getQuantiteStock() {
		return quantiteStock;
	}
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	
	
	
	
}
