package com.mrporter.pomangam._bases.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bank")
public class VBankConfig {

    private String account;
    private String id;
    private String pw;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getAccount() {
        return account;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
