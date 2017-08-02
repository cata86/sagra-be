package it.alecata.sagra.repository;

import it.alecata.sagra.domain.PietanzaOrdinata;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PietanzaOrdinata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PietanzaOrdinataRepository extends JpaRepository<PietanzaOrdinata,Long> {
    
}
