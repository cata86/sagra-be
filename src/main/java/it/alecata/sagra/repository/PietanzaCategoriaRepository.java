package it.alecata.sagra.repository;

import it.alecata.sagra.domain.PietanzaCategoria;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PietanzaCategoria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PietanzaCategoriaRepository extends JpaRepository<PietanzaCategoria,Long> {
	
	List<PietanzaCategoria> findAllByOrderByCodiceAsc();
    
}
