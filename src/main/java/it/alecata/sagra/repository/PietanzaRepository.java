package it.alecata.sagra.repository;

import it.alecata.sagra.domain.Pietanza;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Pietanza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PietanzaRepository extends JpaRepository<Pietanza,Long> {
    
}
