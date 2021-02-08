package com.mrporter.pomangam.client.repositories.carte;

import com.mrporter.pomangam.client.domains.carte.Carte;
import com.mrporter.pomangam.client.domains.carte.QCarte;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface CarteJpaRepository extends JpaRepository<Carte, Long>, CarteCustomRepository {
    Page<Carte> findAllByIsActiveIsTrue(Pageable pageable);
}

interface CarteCustomRepository {
    List<Carte> findIndexCartes(Pageable pageable);
    Carte findToday();
}

@Transactional(readOnly = true)
class CarteCustomRepositoryImpl extends QuerydslRepositorySupport implements CarteCustomRepository {

    public CarteCustomRepositoryImpl() {
        super(Carte.class);
    }

    public List<Carte> findIndexCartes(Pageable pageable) {
        QCarte carte = QCarte.carte;
        return from(carte)
                .select(carte)
                .where(carte.date.before(LocalDate.now())
                        .and(carte.images.isNotEmpty())
                        .and(carte.isActive.isTrue()))
                .fetch();
    }

    @Override
    public Carte findToday() {
        QCarte carte = QCarte.carte;
        return from(carte)
                .select(carte)
                .where(isToday(carte.date)
                        .and(carte.isActive.isTrue()))
                .fetchFirst();
    }

    private BooleanExpression isToday(DatePath<LocalDate> dt) {
        LocalDateTime now = LocalDateTime.now();
        return dt.year().eq(now.getYear())
                .and(dt.month().eq(now.getMonthValue())
                        .and(dt.dayOfMonth().eq(now.getDayOfMonth())));
    }
}
