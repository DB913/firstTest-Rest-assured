package models.lombok;

import lombok.Data;

@Data
public class LoginBodyLombokModel {
    //"{\"email\": \"eve.holt@reqres.in\",\n" + "\"password\":\"cityslicka\"}";
    private String email, password;

}
