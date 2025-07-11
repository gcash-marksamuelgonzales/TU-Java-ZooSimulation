package org.example.vo;

import lombok.Data;

@Data
public class LoginVO {
     private String user;
     private String pass;
     private Integer userType;

     public LoginVO(){

     }

     public LoginVO (String user, String pass, Integer userType){
         this.user = user;
         this.pass = pass;
         this.userType = userType;
     }
}
