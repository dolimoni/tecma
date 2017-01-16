package ma.tecma.commerce;

import java.util.List;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Produit;
import ma.tecma.commerce.service.CommandeService;
import ma.tecma.commerce.service.DirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TecmaController {

	@Autowired
	private CommandeService commandeService;

	@Autowired
	private DirectionService directionService;

	@RequestMapping("/tecma/index")
	public ModelAndView index() {

		return new ModelAndView("index");
	}

	@RequestMapping("/tecma/create")
	public ModelAndView create() {
		commandeService.AjouterUneCommande();// Bouchon
		return new ModelAndView("create");
	}

	@RequestMapping("/tecma/getCommandeByCommercial")
	public ModelAndView getCommandeByCommercial() {

		Long id = 2L;// Bouchon
		List<Commande> commandes = commandeService.getCommandeByCommercial(id);
		return new ModelAndView("create");
	}

	@RequestMapping("/tecma/getClientByCommercial")
	public ModelAndView getClientByCommercial() {

		Long id = 2L;// Bouchon
		List<Client> clients = commandeService.getCommandeByClient(id);
		System.out.println(clients.size());
		return new ModelAndView("create");
	}

	@RequestMapping("/tecma/getCommandeById")
	public ModelAndView getCommandeById() {
		Long id = 1L;
		Commande commande = commandeService.getCommandeById(id);
		return new ModelAndView("create");
	}

}
