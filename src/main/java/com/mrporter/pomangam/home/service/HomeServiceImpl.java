package com.mrporter.pomangam.home.service;

import com.mrporter.pomangam.home.repository.HomeRepositoryImpl;
import com.mrporter.pomangam.home.domain.HomeTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final HomeRepositoryImpl homeRepository;

    @Autowired
    public HomeServiceImpl(HomeRepositoryImpl homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public HomeTbl testService() {
        HomeTbl homeBean = homeRepository.get();
        return homeBean;
    }

    @Override
    public HomeTbl testService(String name) {
        HomeTbl homeBean = homeRepository.get();
        homeBean.setName(name);
        return homeBean;
    }

    @Override
    public HomeTbl get() {
        return null;
    }

    @Override
    public HomeTbl get(int idx) {
        return null;
    }

    @Override
    public HomeTbl post(HomeTbl bean) {
        return null;
    }

    @Override
    public HomeTbl delete(int idx) {
        return null;
    }

    @Override
    public HomeTbl put(HomeTbl bean) {
        return null;
    }

    @Override
    public HomeTbl patch(HomeTbl bean) {
        return null;
    }
}
