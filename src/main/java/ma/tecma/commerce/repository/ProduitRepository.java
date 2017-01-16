package ma.tecma.commerce.repository;
import ma.tecma.commerce.domain.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProduitRepository
		extends JpaRepository<Produit, Long>,
		JpaSpecificationExecutor<Produit> {
	
}
