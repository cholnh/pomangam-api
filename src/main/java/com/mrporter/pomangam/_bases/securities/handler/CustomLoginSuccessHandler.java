package com.mrporter.pomangam._bases.securities.handler;

import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserJpaRepository userJpaRepository;

    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

//        User user = userJpaRepository.findById(authentication.getName());
//        request.getSession().setAttribute("user", user);
//        request.getSession().setAttribute("authentication", authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
