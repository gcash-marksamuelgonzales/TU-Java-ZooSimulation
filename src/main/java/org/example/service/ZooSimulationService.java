package org.example.service;

import org.example.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.repository.LoginRepository;

import java.util.Scanner;

@Service
public class ZooSimulationService {

    @Autowired
    private AdminModuleService adminModuleService;

    @Autowired
    private LoginRepository loginRepository;

    static Scanner scanner = new Scanner(System.in);
    String user = "";
    String pass = "";
    boolean isOpen = false;

    public void execute(){
        boolean isRegistered = true;

        while(isRegistered){
            System.out.println("=== Welcome to the Zoo Admin Console ===");
            System.out.println("Please login.");
            System.out.println("Enter Username: ");
            user = scanner.nextLine();
            System.out.println("Enter Password: ");
            pass = scanner.nextLine();

            LoginVO loginVO = new LoginVO();
            loginVO.setUser(user);
            loginVO.setPass(pass);

            // Validate if user is registered
            if(loginRepository.isRegistered(loginVO)){
                System.out.printf("Login Successful! Welcome User: %s%n",user);
                switch(loginVO.getUserType()){
                    case 1:
                        adminModuleService.mainMenu(isOpen);
                        break;
                }
            } else{
                System.out.println("Login Invalid! Username/Password is incorrect..");
            }
        }

    }

}
