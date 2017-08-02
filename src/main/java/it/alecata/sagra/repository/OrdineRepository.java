package it.alecata.sagra.repository;

import it.alecata.sagra.domain.Ordine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Ordine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdineRepository extends JpaRepository<Ordine,Long> {
    
}
