package it.alecata.sagra.repository;

import it.alecata.sagra.domain.TavoloReale;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TavoloReale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TavoloRealeRepository extends JpaRepository<TavoloReale,Long> {
    
}
