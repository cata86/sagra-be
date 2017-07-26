package it.alecata.sagra.repository;

import it.alecata.sagra.domain.Serata;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Serata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SerataRepository extends JpaRepository<Serata,Long> {
	
	
	Serata findByDataChiusuraIsNull();
}
