package com.example.mono_postmortem;

import lombok.Data;

@Data
public class DataDubu {
    private String authorization_url;
    private String access_code;
    private String reference;
    private String payer_id;

    public String getAuthorization_url() {
        return authorization_url;
    }

    public String getAccess_code() {
        return access_code;
    }

    public String getReference() {
        return reference;
    }

    public String getPayer_id() {
        return payer_id;
    }

    public void setAuthorization_url(String authorization_url) {
        this.authorization_url = authorization_url;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }
}
