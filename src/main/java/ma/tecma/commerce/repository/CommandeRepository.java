package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Commande;
import ma.tecma.commerce.domain.Commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommandeRepository
		extends JpaRepository<Commande, Long>,
		JpaSpecificationExecutor<Commande> {
	
	public List<Commande> findByCommercial(Commercial commercial);
}
