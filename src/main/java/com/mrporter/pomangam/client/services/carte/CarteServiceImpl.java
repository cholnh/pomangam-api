package com.mrporter.pomangam.client.services.carte;

import com.mrporter.pomangam.client.domains.carte.CarteDto;
import com.mrporter.pomangam.client.repositories.carte.CarteJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CarteServiceImpl implements CarteService {

    CarteJpaRepository carteRepo;

    @Override
    public List<CarteDto> findAll(Pageable pageable) {
        return CarteDto.fromEntities(carteRepo.findAllByIsActiveIsTrue(pageable).getContent());
    }

    @Override
    public List<CarteDto> findIndexCartes(Pageable pageable) {
        return CarteDto.fromEntities(carteRepo.findIndexCartes(pageable));
    }

    public CarteDto today() {
        return CarteDto.fromEntity(carteRepo.findToday());
    }
}
