package org.example.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.example.vo.LoginVO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepository extends LoginVO {

    private final List<LoginVO> loginVOs = new ArrayList<>(List.of(
            new LoginVO("admin","admin",1),
            new LoginVO("visitor","pass123",2)
    ));

    public boolean isRegistered(LoginVO loginVO){
        boolean isRegistered = false;
        for(LoginVO login : loginVOs){
            if (loginVO.getUser().equals(login.getUser()) && loginVO.getPass().equals(login.getPass())) {
                loginVO.setUserType(login.getUserType());
                isRegistered = true;
                break;
            }
        }
        return isRegistered;
    }

}
