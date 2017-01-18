package ma.tecma.commerce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
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

	@RequestMapping("/tecma/creerCommande")
	public ModelAndView creerCommande(HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("idCommercial");
		ModelAndView modelAndView = new ModelAndView("commercial/creerCommande");
		modelAndView.addObject("commande", new Commande());
		modelAndView.addObject("clients", getClientByCommercial(id));
		return modelAndView;
	}
	@RequestMapping(value="/tecma/createCommande",method = RequestMethod.POST)
	public ModelAndView createCommande(HttpServletRequest request, @ModelAttribute("commande")Commande commande) {
		
		Long idCommercial = (Long)request.getSession().getAttribute("idCommercial"); 
		
		commandeService.AjouterUneCommande(commande, idCommercial,commande.getDestinataire());// 
		return new ModelAndView("commercial/index");
	}
	

	@RequestMapping("/tecma/getCommandeByCommercial")
	public ModelAndView getCommandeByCommercial() {

		Long id = 2L;// Bouchon
		List<Commande> commandes = commandeService.getCommandeByCommercial(id);
		return new ModelAndView("create");
	}

	private List<Client> getClientByCommercial(Long id) {

		return commandeService.getCommandeByClient(id);
		
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
	
	@RequestMapping("/tecma/authentificationCommercial")
	public ModelAndView authentificationCommercial() {
		Commercial commercial = new Commercial();
		return new ModelAndView("commercial/authentification","commercial",commercial);
	}
	
	
	@RequestMapping(value="/tecma/authenticateCommercial",method = RequestMethod.POST)
	public ModelAndView authenticateCommercial(@ModelAttribute("commercial")Commercial commercial, 
			   ModelMap model,HttpServletRequest request) {
		if(directionService.authenticateCommercial(commercial))
		{
			request.getSession().setAttribute("idCommercial", directionService.getIdCommercial(commercial));
			return new ModelAndView("commercial/index");
		}else{
			ModelAndView modelAndView = new ModelAndView("commercial/authentification");
			modelAndView.addObject("commercial", commercial);
			modelAndView.addObject("invalidUsernameOrPassword", true);
			return modelAndView;
		}
		
	}

}
