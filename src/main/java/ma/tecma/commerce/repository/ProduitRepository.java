package ma.tecma.commerce.repository;
import java.util.List;

import ma.tecma.commerce.domain.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProduitRepository
		extends JpaRepository<Produit, Long>,
		JpaSpecificationExecutor<Produit> {

	List<Produit> findBySecteur(String secteur);

	List<Produit> findBySecteurAndQuantiteStockGreaterThan(String secteur, int i);
	
}
