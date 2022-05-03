package com.example.challengue.DTO.Request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateRolDTO {

    private String username;
    private Integer idRol;

}
