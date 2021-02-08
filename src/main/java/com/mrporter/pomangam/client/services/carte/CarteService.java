package com.mrporter.pomangam.client.services.carte;

import com.mrporter.pomangam.client.domains.carte.CarteDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarteService {
    List<CarteDto> findAll(Pageable pageable);
    List<CarteDto> findIndexCartes(Pageable pageable);
    CarteDto today();
}
