package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientRepository
		extends JpaRepository<Client, Long>,
		JpaSpecificationExecutor<Client> {
	
	public List<Client> findBySecteur(String secteur); 
	
}
