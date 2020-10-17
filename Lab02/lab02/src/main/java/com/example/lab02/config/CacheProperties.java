package com.example.lab02.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("lab02.cache")
public class CacheProperties {

    private String host;
    private Integer port;
    private Boolean enableSsl;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getEnableSsl() {
        return enableSsl;
    }

    public void setEnableSsl(Boolean enableSsl) {
        this.enableSsl = enableSsl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
