package ma.tecma.commerce.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.dtos.ClientDTO;
import ma.tecma.commerce.dtos.CommandeDTO;
import ma.tecma.commerce.repository.ClientRepository;
import ma.tecma.commerce.repository.CommandeRepository;
import ma.tecma.commerce.repository.EmployeRepository;
import ma.tecma.commerce.repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CommandeService {

	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private EmployeRepository employeRepsitory;
	
	@Autowired
	private ProduitRepository produitRepository;
	
	public CommandeService() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, Object> AjouterUneCommande(CommandeDTO commandeDTO, Long idCommercial){
		
		Map<String, Object> response = new HashMap<String, Object>();
		Commande commande = new Commande();
		Commercial commercial = (Commercial)employeRepsitory.findOne(idCommercial);
		Produit produit = produitRepository.findOne(new Long(commandeDTO.getProduitId()));
		
		if(produit.getQuantiteStock()<commandeDTO.getQuantite()){
			response.put("insufficientQuantity", true);
			return response;
		}else{
			response.put("insufficientQuantity", false);
			produit.setQuantiteStock(produit.getQuantiteStock()-(int)commandeDTO.getQuantite());
		}
		
		Client client = clientRepository.findOne(new Long(commandeDTO.getClientId()));
		
		
		commande.setCommercial(commercial);
		commande.setTransport(commandeDTO.getTransport());
		commande.setSent(false);
		commande.setQuantite(commandeDTO.getQuantite());
		commande.setDestinataire(client);
		commande.setProduit(produit);
		
		
		//ajout de la date de livraison
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateLivraison=null;
		try {
			dateLivraison = sdf.parse("28/01/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commande.setDateLivraison(dateLivraison);
		
		//ajout de la date de validité
		commande.setDateValidite(dateLivraison);

		//modifier la quantité du produit dans le stock
		produitRepository.saveAndFlush(produit);
		commandeRepository.saveAndFlush(commande);
		response.put("commande", commande);
		return response;
	}

	public List<Commande> getCommandeByCommercial(Long id) {
		Commercial commercial = (Commercial) employeRepsitory.findOne(id);
		return commandeRepository.findByCommercial(commercial);
	}

	public List<Client> getCommandeByClient(Long id) {
		Commercial commercial = (Commercial) employeRepsitory.findOne(id);
		 List<Client> clients = clientRepository.findBySecteur(commercial.getSecteur());
		return clients;
	}

	public Produit getProduct(Long id){
		return produitRepository.findOne(id);
	}

	public Commande getCommandeById(Long id) {
		return commandeRepository.findOne(id);
	}

	public void approval(Commande commande,boolean approval) {
		if(approval){
			commande.setSent(true);
			commande.setStatus("Approuved");
		}else{
			commande.setStatus("Refused");
		}
		commandeRepository.saveAndFlush(commande);
	}

	public List<Commande> getCommandesByClient(Long id) {
		Client client = clientRepository.findOne(id);
		return commandeRepository.findByDestinataire(client);
	}

	public List<Produit> getProductsByDomain(String secteur) {
		// TODO Auto-generated method stub
		return produitRepository.findBySecteur(secteur);
	}

	public List<Produit> getProductsByDomainForOrders(String secteur) {
		// TODO Auto-generated method stub
		return produitRepository.findBySecteurAndQuantiteStockGreaterThan(secteur,0);
	}
	
}
