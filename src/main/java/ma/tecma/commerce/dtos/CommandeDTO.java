package ma.tecma.commerce.dtos;

import ma.tecma.commerce.domain.Commande;


public class CommandeDTO {

	
	private Long id;
	
	
	private ProduitDTO produitDTO;
	private String produitId;
	private double quantite;
	private String transport;
	private Boolean sent=false;
	private String status;
	
	
	private ClientDTO destinataire;
	private String clientId;
	private String dateLivraison;
	private String dateValidite;
	
	private CommercialDTO commercial;

	public CommandeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CommandeDTO(Commande commande) {
		this.produitDTO= new ProduitDTO(commande.getProduit());
		this.produitId=String.valueOf(commande.getProduit().getId());
		this.quantite=commande.getQuantite();
		this.transport=commande.getTransport();
		this.sent=commande.getSent();
		this.status=commande.getStatus();
	}

	public String getProduitId() {
		return produitId;
	}

	public void setProduitId(String produitId) {
		this.produitId = produitId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProduitDTO getProduitDTO() {
		return produitDTO;
	}

	public void setProduitDTO(ProduitDTO produit) {
		this.produitDTO = produit;
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

	public ClientDTO getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(ClientDTO destinataire) {
		this.destinataire = destinataire;
	}

	public String getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getDateValidite() {
		return dateValidite;
	}

	public void setDateValidite(String dateValidite) {
		this.dateValidite = dateValidite;
	}

	public CommercialDTO getCommercial() {
		return commercial;
	}

	public void setCommercial(CommercialDTO commercial) {
		this.commercial = commercial;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
	
}
