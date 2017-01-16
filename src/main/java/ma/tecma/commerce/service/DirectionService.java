package ma.tecma.commerce.service;

import java.util.List;

import javax.transaction.Transactional;

import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.repository.CommandeRepository;
import ma.tecma.commerce.repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DirectionService {

	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	CommandeRepository commandeRepository;
	
	public DirectionService() {
		// TODO Auto-generated constructor stub
	}
	
	public Produit addProduct(Produit produit){
		return produitRepository.saveAndFlush(produit);
	}

	public List<Produit> getAllProducts() {
		
		return produitRepository.findAll();
	}

	public List<Commande> getAllOrders() {
		return commandeRepository.findAll();
		
	}
}
