package ma.tecma.commerce.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID")
	private Long id;
	private String nom;
	private String password;
	private String secteur;
	
	
	@OneToMany
	private List<Commande> commandes = new ArrayList<Commande>();

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public Client(String nom, String password, String secteur,
			List<Commande> commandes) {
		super();
		this.nom = nom;
		this.password = password;
		this.secteur = secteur;
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

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Long getId() {
		return id;
	}
	
	
}
