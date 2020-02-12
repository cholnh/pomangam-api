package com.mrporter.pomangam._bases.securities.oauth2clientdetail.service;

import com.mrporter.pomangam._bases.securities.oauth2clientdetail.repository.Oauth2ClientDetailRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Oauth2ClientDetailServiceImpl implements Oauth2ClientDetailService {

    Oauth2ClientDetailRepositoryImpl countSearchDeliverySiteRepository;

}
