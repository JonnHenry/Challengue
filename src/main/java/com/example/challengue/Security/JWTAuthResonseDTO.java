package com.example.challengue.Security;


import lombok.Data;

@Data
public class JWTAuthResonseDTO {

    private String token;
    private String typeToken = "Bearer";

    public JWTAuthResonseDTO(String token) {
        super();
        this.token = token;
    }

    public JWTAuthResonseDTO(String token, String typeToken) {
        super();
        this.token = token;
        this.typeToken = typeToken;
    }



}