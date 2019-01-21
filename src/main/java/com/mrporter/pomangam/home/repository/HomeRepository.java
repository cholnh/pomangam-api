package com.mrporter.pomangam.home.repository;

import com.mrporter.pomangam.home.domain.HomeTbl;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface HomeRepository {
    HomeTbl get();
    HomeTbl get(int idx);
    HomeTbl post(HomeTbl bean);
    HomeTbl delete(int idx);
    HomeTbl put(HomeTbl bean);
    HomeTbl patch(HomeTbl bean);
}
