package com.example.lab01.config;


import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@ConfigurationProperties("lab01.features")
@Validated
public class Lab01Properties {

    private CryptoProperties cryptoProperties;

    public void setCrypto(CryptoProperties cryptoProperties) {
        this.cryptoProperties = cryptoProperties;
    }

    public CryptoProperties getCrypto() {
        return cryptoProperties;
    }

    private boolean panicEnabled;

    public boolean isPanicEnabled() {
        return panicEnabled;
    }

    public void setPanicEnabled(boolean panicEnabled) {
        this.panicEnabled = panicEnabled;
    }

    @NotNull(message = "smtp not null")
    private MailProperties smtp1;

    @NotNull(message = "smtp not null")
    private MailProperties smtp2;

    public MailProperties getSmtp1() {
        return smtp1;
    }

    public void setSmtp1(MailProperties smtp1) {
        this.smtp1 = smtp1;
    }

    public MailProperties getSmtp2() {
        return smtp2;
    }

    public void setSmtp2(MailProperties smtp2) {
        this.smtp2 = smtp2;
    }

    private List<Api> apis;

    public List<Api> getApis() {
        return apis;
    }

    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

    public static class CryptoProperties{

        @NotEmpty(message = "Crypto service key no puede ser nulo")
        @Length(min = 10, max = 30)
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }



    public static class MailProperties{

        @NotEmpty(message = "host no puede ser nulo")
        private String host;

        @NotEmpty(message = "cc no puede ser vacío")
        private List<String> cc;

        @Max(9090)
        @Min(8080)
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public List<String> getCc() {
            return cc;
        }

        public void setCc(List<String> cc) {
            this.cc = cc;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public static class Api{
        private String url;
        private ApiCredentials credential;
        private HashMap<String,String> headers;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ApiCredentials getCredential() {
            return credential;
        }

        public void setCredential(ApiCredentials credential) {
            this.credential = credential;
        }

        public HashMap<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(HashMap<String, String> headers) {
            this.headers = headers;
        }
    }

    public static class ApiCredentials{
        private String user;
        private String password;

        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
