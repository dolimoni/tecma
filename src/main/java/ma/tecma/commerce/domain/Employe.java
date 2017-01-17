package ma.tecma.commerce.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Employe {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "EMPLOYE_ID")
	private Long id;
	private String name;
	private String password;
	@OneToMany
	private List<Commande> commandes = new ArrayList<Commande>();
	public Employe() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Employe(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}


	public List<Commande> getCommandes() {
		return commandes;
	}


	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
}
