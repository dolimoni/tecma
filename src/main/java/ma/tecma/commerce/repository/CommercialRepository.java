package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommercialRepository
		extends JpaRepository<Commercial, Long>,
		JpaSpecificationExecutor<Commercial> {

	List<Commercial> findByNameAndPassword(String name, String password);
	
}
