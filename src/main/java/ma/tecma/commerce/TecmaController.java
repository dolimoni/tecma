package ma.tecma.commerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;
import ma.tecma.commerce.dtos.ClientDTO;
import ma.tecma.commerce.dtos.CommandeDTO;
import ma.tecma.commerce.service.CommandeService;
import ma.tecma.commerce.service.DirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		
		Long id = (Long)request.getSession().getAttribute("commercialId");
		if(id==null){
			return new ModelAndView("commercial/authentification");
		}
		ModelAndView modelAndView = new ModelAndView("commercial/creerCommande","commercial",new Commercial());
		modelAndView.addObject("commandeDTO", new CommandeDTO());
		modelAndView.addObject("clientDTOs", getClientByCommercial(id));
		return modelAndView;
	}
	@RequestMapping(value="/tecma/createCommande",method = RequestMethod.POST)
	public ModelAndView createCommande(HttpServletRequest request, @ModelAttribute("commande")CommandeDTO commandeDTO) {
		Long id = (Long)request.getSession().getAttribute("commercialId");
		if(id==null){
			return new ModelAndView("commerical/index");
		}
		Long idCommercial = (Long)request.getSession().getAttribute("idCommercial"); 
		commandeService.AjouterUneCommande(commandeDTO, idCommercial);// 
		return new ModelAndView("commercial/index");
	}
	

	@RequestMapping("/tecma/getCommandeByCommercial")
	public ModelAndView getCommandeByCommercial(HttpServletRequest request) {
		Long commercialId = (Long)request.getSession().getAttribute("commercialId");
		if(commercialId==null){
			return new ModelAndView("commercial/authentification","commercial",new Commercial());
		}
		Long id = (Long) request.getSession().getAttribute("commercialId");
		List<Commande> commandes = commandeService.getCommandeByCommercial(id);
		
		List<CommandeDTO> commandeDTOs = new ArrayList<CommandeDTO>();
		for (Commande commande : commandes) {
			CommandeDTO commandeDTO = new CommandeDTO(commande);
			commandeDTOs.add(commandeDTO);
		}
		
		return new ModelAndView("commercial/mesCommandes","commandesDTO",commandeDTOs);
	}

	private List<ClientDTO> getClientByCommercial(Long id) {
		List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
		List<Client> clients = commandeService.getCommandeByClient(id);
		for (Client client : clients) {
			ClientDTO clientDTO = new ClientDTO();
			clientDTO.setId(client.getId());
			clientDTO.setNom(client.getNom());
			clientDTO.setPassword(client.getPassword());
			clientDTO.setSecteur(client.getSecteur());
			clientDTOs.add(clientDTO);
		}
		return clientDTOs;
		
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
		Long id = (Long) request.getSession().getAttribute("clientId");
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
		return new ModelAndView("client/authentification","client",client);
	}
	
	@RequestMapping(value="/tecma/authenticate",method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("client")Client client, 
			   ModelMap model,HttpServletRequest request) {
		if(directionService.authenticate(client))
		{
			request.getSession().setAttribute("clientId", directionService.getIdClient(client));
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
			request.getSession().setAttribute("commercialId", directionService.getIdCommercial(commercial));
			return new ModelAndView("commercial/index");
		}else{
			ModelAndView modelAndView = new ModelAndView("commercial/authentification","commercial",new Commercial());
			modelAndView.addObject("commercial", commercial);
			modelAndView.addObject("invalidUsernameOrPassword", true);
			return modelAndView;
		}
		
	}

	@RequestMapping("/tecma/logout")
	public ModelAndView logout(@RequestParam("from") String from, HttpServletRequest request) {
		request.getSession().invalidate();
		if("0".equals(from)){
			return new ModelAndView("client/authentification","client",new Client());
		}
		else if("1".equals(from)){
			return new ModelAndView("commercial/authentification","commercial",new Commercial());
		}else{
			return new ModelAndView("client/authentification","client",new Client());
		}
		
	}
}
