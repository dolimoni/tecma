package ma.tecma.commerce.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
import ma.tecma.commerce.domain.Produit;
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
	
	public Commande AjouterUneCommande(Commande commande, Long idCommercial, Client client){
		
		

		
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

		return commandeRepository.saveAndFlush(commande);
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
	
}
