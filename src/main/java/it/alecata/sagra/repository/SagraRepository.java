package it.alecata.sagra.repository;

import it.alecata.sagra.domain.Sagra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Sagra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SagraRepository extends JpaRepository<Sagra,Long> {
    
}
