package it.alecata.sagra.repository;

import it.alecata.sagra.domain.TavoloAccomodato;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TavoloAccomodato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TavoloAccomodatoRepository extends JpaRepository<TavoloAccomodato,Long> {
    
}
