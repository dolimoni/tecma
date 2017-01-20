package ma.tecma.commerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.dtos.ProduitDTO;
import ma.tecma.commerce.service.CommandeService;
import ma.tecma.commerce.service.DirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DirectionController  {

	@Autowired
	private CommandeService commandeService;
	
	@Autowired
	private DirectionService directionService;

	
	@RequestMapping("/direction/AjouterProduit")
	public ModelAndView AjouterProduit() {
		
		return new ModelAndView("direction/AjouterProduit","produit", new Produit());
	}
	
	@RequestMapping(value="/direction/addProduct",method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("produit")ProduitDTO produitDTO) {
		
		directionService.addProduct(produitDTO);
		List<Produit> produits= directionService.getAllProducts();
		return new ModelAndView("direction/listeProduits","produits",produits);
	}
	
	@RequestMapping("/direction/getProduct")
	public ModelAndView getProduct() {
		Produit produit = commandeService.getProduct(1L);
		return new ModelAndView("create");
	}
	
//	@RequestMapping("/direction/editProduct")
//	public ModelAndView editProduct() {
//		Produit produit = commandeService.getProduct(1L);
//		produit.setNom("céréales");
//		produit.setPrix(7);
//		produit.setQuantiteStock(1000);
//		directionService.addProduct(produit);
//		return new ModelAndView("create");
//	}
	
	@RequestMapping("/direction/getAllProducts")
	public ModelAndView getAllProducts() {
		List<Produit> produits= directionService.getAllProducts();
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/listeDesProduits")
	public ModelAndView listeDesProduits(@RequestParam("from") String from, HttpServletRequest request) {
		
		String vue = "";
		List<Produit> produits = new ArrayList<Produit>();
		
		if("0".equals(from)){
			Long id = (Long)request.getSession().getAttribute("clientId");
			if(id==null){
				return new ModelAndView("client/authentification","commercial",new Commercial());
			}
			Client client = directionService.getClient(id);
			String secteur = client.getSecteur();
			produits=commandeService.getProductsByDomain(secteur);
			vue="client/";
		}else if("1".equals(from)){
			 vue = "commercial/";
			 Long id = (Long)request.getSession().getAttribute("commercialId");
			 if(id==null){
					return new ModelAndView("commercial/authentification","commercial",new Commercial());
				}
			 Commercial commercial = directionService.getCommercial(id);
			 produits=commandeService.getProductsByDomain(commercial.getSecteur());
		}else if("2".equals(from)){
			 vue = "direction/";
			 produits=directionService.getAllProducts();
		}
		System.out.println("Nombre de produits "+produits.size()+" Affichage dans "+vue+"listeProduits");
		return new ModelAndView(vue+"listeProduits","produits",produits);
	}
	
	
	@RequestMapping("/direction/getAllOrders")
	public ModelAndView getAllOrders() {
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("direction/listeCommande","commandes",commandes);
	}
	
	@RequestMapping("/direction/listeDesCommandes")
	public ModelAndView listeDesCommandes() {
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("listeCommande","commandes",commandes);
	}
	
	
	@RequestMapping("/direction/approval")
	public ModelAndView approval(@RequestParam("commande_id") String commande_id) {
		Long id = new Long(commande_id);
		Commande commande=commandeService.getCommandeById(id);
		commandeService.approval(commande,true);
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("direction/listeCommande","commandes",commandes);
	}
	
	@RequestMapping("/direction/disapproval")
	public ModelAndView disapproval(@RequestParam("commande_id") String commande_id) {
		Long id = new Long(commande_id);
		Commande commande=commandeService.getCommandeById(id);
		commandeService.approval(commande,false);
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("direction/listeCommande","commandes",commandes);
	}
}
