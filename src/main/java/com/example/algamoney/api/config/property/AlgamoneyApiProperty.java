package com.example.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by victor on 05/10/2017.
 */


@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

    private final Seguranca seguranca = new Seguranca();
    private  String origemPermitida = "http://localhost:8000";

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOrigemPermitida() {
        return origemPermitida;
    }

    public void setOrigemPermitida(String origemPermitida) {
        this.origemPermitida = origemPermitida;
    }

    public static class Seguranca{

        private Boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }
    }



}
