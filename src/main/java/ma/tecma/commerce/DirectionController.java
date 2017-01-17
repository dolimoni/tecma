package ma.tecma.commerce;

import java.util.List;

import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.service.CommandeService;
import ma.tecma.commerce.service.DirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
		return new ModelAndView("AjouterProduit","produit", new Produit());
	}
	
	@RequestMapping(value="/direction/addProduct",method = RequestMethod.POST)
	public ModelAndView addProduct() {
		Produit produit = new Produit();
		produit.setNom("céréales");
		produit.setPrix(7);
		produit.setQuantiteStock(1000);
		directionService.addProduct(produit);
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/getProduct")
	public ModelAndView getProduct() {
		Produit produit = commandeService.getProduct(1L);
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/editProduct")
	public ModelAndView editProduct() {
		Produit produit = commandeService.getProduct(1L);
		produit.setNom("céréales");
		produit.setPrix(7);
		produit.setQuantiteStock(1000);
		directionService.addProduct(produit);
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/getAllProducts")
	public ModelAndView getAllProducts() {
		List<Produit> produits= directionService.getAllProducts();
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/listeDesProduits")
	public ModelAndView listeDesProduits(@RequestParam("from") String from) {
		String vue = "";
		if("0".equals(from)){
			vue="client/";
		}
		List<Produit> produits= directionService.getAllProducts();
		System.out.println("Nombre de produits "+produits.size()+" Affichage dans "+vue+"listeProduits");
		return new ModelAndView(vue+"listeProduits","produits",produits);
	}
	
	
	@RequestMapping("/direction/getAllOrders")
	public ModelAndView getAllOrders() {
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("create");
	}
	
	@RequestMapping("/direction/listeDesCommandes")
	public ModelAndView listeDesCommandes() {
		List<Commande> commandes= directionService.getAllOrders();
		return new ModelAndView("listeCommande","commandes",commandes);
	}
	
	
	@RequestMapping("/direction/approval")
	public ModelAndView approval() {
		Commande commande=commandeService.getCommandeById(1L);
		commandeService.approval(commande,true);
		return new ModelAndView("create");
	}
}
