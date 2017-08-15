package it.alecata.sagra.repository;

import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.domain.TavoloAccomodato;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Ordine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdineRepository extends JpaRepository<Ordine,Long> {
	
	List<Ordine> findByTavoloAccomodato(TavoloAccomodato tavoloAccomodato);
    
}
