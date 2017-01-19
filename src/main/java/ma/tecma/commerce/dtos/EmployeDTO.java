package ma.tecma.commerce.dtos;

import java.util.ArrayList;
import java.util.List;

public class EmployeDTO {

	
	private Long id;
	private String name;
	private String password;
	private List<CommandeDTO> commandes = new ArrayList<CommandeDTO>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<CommandeDTO> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<CommandeDTO> commandes) {
		this.commandes = commandes;
	}
	
	
	
	
}
