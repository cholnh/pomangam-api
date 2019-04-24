package com.mrporter.pomangam.common.security.oauth2ClientDetail.service;

import com.mrporter.pomangam.common.security.oauth2ClientDetail.repository.Oauth2ClientDetailRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Oauth2ClientDetailServiceImpl implements Oauth2ClientDetailService {

    Oauth2ClientDetailRepositoryImpl countSearchDeliverySiteRepository;

}
