package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Client;
import ma.tecma.commerce.domain.Employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeRepository
		extends JpaRepository<Employe, Long>,
		JpaSpecificationExecutor<Employe> {

	List<Employe> findByNameAndPassword(String name, String password);
	
}
