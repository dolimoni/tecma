package ma.tecma.commerce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.service.CommandeService;
import ma.tecma.commerce.service.DirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
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
	public ModelAndView getCommandeById(HttpServletRequest request) {
		request.getSession().setAttribute("id", 1);
		Long id = (Long) request.getSession().getAttribute("id");
		Commande commande = commandeService.getCommandeById(id);
		return new ModelAndView("create");
	}
	
	@RequestMapping("/tecma/getCommandesByClient")
	public ModelAndView getCommandesByClient(HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("id");
		List<Commande> commandes = commandeService.getCommandesByClient(id);
		return new ModelAndView("client/listeCommande","commandes",commandes);
	}
	
	@RequestMapping("/tecma/inscription")
	public ModelAndView inscription() {
		Client client = new Client();
		return new ModelAndView("inscription","client",client);
	}
	
	@RequestMapping(value="/tecma/register",method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("client")Client client, 
			   ModelMap model) {
		if(directionService.inscription(client))
		{
			return new ModelAndView("index");
		}else{
			ModelAndView modelAndView = new ModelAndView("inscription");
			modelAndView.addObject("client", client);
			modelAndView.addObject("emptyUsernameOrPassword", true);
			return modelAndView;
		}
		
	}
	
	@RequestMapping("/tecma/authentification")
	public ModelAndView authentification() {
		Client client = new Client();
		return new ModelAndView("authentification","client",client);
	}
	
	@RequestMapping(value="/tecma/authenticate",method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("client")Client client, 
			   ModelMap model,HttpServletRequest request) {
		if(directionService.authenticate(client))
		{
			request.getSession().setAttribute("id", directionService.getIdClient(client));
			return new ModelAndView("client/index");
		}else{
			ModelAndView modelAndView = new ModelAndView("authentification");
			modelAndView.addObject("client", client);
			modelAndView.addObject("invalidUsernameOrPassword", true);
			return modelAndView;
		}
		
	}

}
