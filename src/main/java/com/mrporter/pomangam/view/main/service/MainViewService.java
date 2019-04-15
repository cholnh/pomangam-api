package com.mrporter.pomangam.view.main.service;

import com.mrporter.pomangam.view.main.domain.MainFirstViewDto;
import com.mrporter.pomangam.view.main.domain.MainSecondViewDto;
import com.mrporter.pomangam.view.main.domain.MainViewDto;

public interface MainViewService {
    MainViewDto getMainDto(Integer delivery_site_idx);
    MainFirstViewDto getMainFirstDto(Integer delivery_site_idx);
    MainSecondViewDto getMainSecondDto(Integer delivery_site_idx);
}
