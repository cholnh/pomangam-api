package com.mrporter.pomangam.home.service;

import com.mrporter.pomangam.home.domain.HomeTbl;

public interface HomeService {
    HomeTbl testService();
    HomeTbl testService(String name);

    HomeTbl get();
    HomeTbl get(int idx);
    HomeTbl post(HomeTbl bean);
    HomeTbl delete(int idx);
    HomeTbl put(HomeTbl bean);
    HomeTbl patch(HomeTbl bean);
}
