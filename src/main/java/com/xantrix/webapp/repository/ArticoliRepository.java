package com.xantrix.webapp.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.repository.*;

import com.xantrix.webapp.entities.Articoli;;;

public interface ArticoliRepository extends PagingAndSortingRepository<Articoli, Long>{

    List<Articoli> findByDescrizioneLike(String descrizione);

    List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);

    Articoli findByCodArt(String codArt);
    
}
