package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Directeur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirecteurRepository
		extends JpaRepository<Directeur, Long>,
		JpaSpecificationExecutor<Directeur> {

	List<Directeur> findByNameAndPassword(String name, String password);
	
}
