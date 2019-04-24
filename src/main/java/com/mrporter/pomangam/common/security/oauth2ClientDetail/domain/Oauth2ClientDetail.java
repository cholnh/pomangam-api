package com.mrporter.pomangam.common.security.oauth2ClientDetail.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "oauth_client_details")
@NoArgsConstructor
@Data
@Entity
public class Oauth2ClientDetail implements Serializable {

    @Id
    private String client_id;

    private String resource_ids;

    private String client_secret;

    private String scope;

    private String authorized_grant_types;

    private String web_server_redirect_uri;

    private String authorities;

    private Integer access_token_validity;

    private Integer refresh_token_validity;

    private String additional_information;

    private String autoapprove;

    @Builder
    public Oauth2ClientDetail(String client_id, String resource_ids, String client_secret, String scope, String authorized_grant_types, String web_server_redirect_uri, String authorities, Integer access_token_validity, Integer refresh_token_validity, String additional_information, String autoapprove) {
        this.client_id = client_id;
        this.resource_ids = resource_ids;
        this.client_secret = client_secret;
        this.scope = scope;
        this.authorized_grant_types = authorized_grant_types;
        this.web_server_redirect_uri = web_server_redirect_uri;
        this.authorities = authorities;
        this.access_token_validity = access_token_validity;
        this.refresh_token_validity = refresh_token_validity;
        this.additional_information = additional_information;
        this.autoapprove = autoapprove;
    }
}
