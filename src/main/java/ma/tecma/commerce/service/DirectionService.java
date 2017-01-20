package ma.tecma.commerce.service;

import java.util.List;

import javax.transaction.Transactional;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
import ma.tecma.commerce.domain.Directeur;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.dtos.ProduitDTO;
import ma.tecma.commerce.repository.ClientRepository;
import ma.tecma.commerce.repository.CommandeRepository;
import ma.tecma.commerce.repository.CommercialRepository;
import ma.tecma.commerce.repository.DirecteurRepository;
import ma.tecma.commerce.repository.EmployeRepository;
import ma.tecma.commerce.repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DirectionService {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EmployeRepository employeRepository;
	
	@Autowired
	private CommercialRepository commercialRepository;

	@Autowired
	private DirecteurRepository directeurRepository;
	
	public DirectionService() {
		// TODO Auto-generated constructor stub
	}
	
	public Produit addProduct(ProduitDTO produitDTO){
		Produit produit = new Produit();
		produit.setNom(produitDTO.getNom());
		produit.setPrix(new Long(produitDTO.getPrix()));
		produit.setQuantiteStock(produitDTO.getQuantiteStock());
		produit.setSecteur(produitDTO.getSecteur());
		return produitRepository.saveAndFlush(produit);
	}

	public List<Produit> getAllProducts() {
		
		return produitRepository.findAll();
	}

	public List<Commande> getAllOrders() {
		return commandeRepository.findAll();
		
	}

	public boolean inscription(Client client) {
		if("".equals(client.getNom()) || "".equals(client.getPassword()))
			return false;
		else{
			clientRepository.saveAndFlush(client);
			return true;
		}
		
	}

	public boolean authenticate(Client client) {
		List<Client> clients = clientRepository.findByNomAndPassword(client.getNom(), client.getPassword());
		if(clients.size()>0){
			return true;
		}else{
			return false;
		}
	
	}

	public Long getIdClient(Client client) {
		List<Client> clients = clientRepository.findByNomAndPassword(client.getNom(), client.getPassword());
		Long id = clients.get(0).getId();
		return id;
	}

	public boolean authenticateCommercial(Commercial commercial) {
		List<Commercial> commercials = commercialRepository.findByNameAndPassword(commercial.getName(), commercial.getPassword());
		
		if(commercials.size()>0){
			return true;
		}else{
			commercialRepository.findAll().size();
			return false;
		}
	}

	public Long getIdCommercial(Commercial commercial) {
		List<Commercial> commercials = commercialRepository.findByNameAndPassword(commercial.getName(), commercial.getPassword());
		return commercials.get(0).getId();
	}

	public Client getClient(Long id) {
		return clientRepository.findOne(id);
	}

	public Commercial getCommercial(Long id) {
		return commercialRepository.findOne(id);
	}

	public boolean authenticateDirecteur(Directeur directeur) {
		
		List<Directeur> directeurs = directeurRepository.findByNameAndPassword(directeur.getName(), directeur.getPassword());
		
		if(directeurs.size()>0){
			return true;
		}else{
			commercialRepository.findAll().size();
			return false;
		}
	}

	public Object getIdDirecteur(Directeur directeur) {
		List<Directeur> directeurs = directeurRepository.findByNameAndPassword(directeur.getName(), directeur.getPassword());
		return directeurs.get(0).getId();
	}

	
}
