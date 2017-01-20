package ma.tecma.commerce.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COMMANDE_ID")
	private Long id;
	
	@OneToOne
	private Produit produit;
	private double quantite;
	private String transport;
	private Boolean sent=false;
	private String status="none";
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client destinataire;
	private Date dateLivraison;
	private Date dateValidite;
	@ManyToOne
	@JoinColumn(name = "EMPLOYE_ID")
	private Commercial commercial;
	

	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public Client getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(Client destinataire) {
		this.destinataire = destinataire;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public Date getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}
	public Long getId() {
		return id;
	}
	public Commercial getCommercial() {
		return commercial;
	}
	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}
	public Boolean getSent() {
		return sent;
	}
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
