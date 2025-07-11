package org.example.service;

import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AdminModuleService {

    List<ZooSetupVO> zooSetupVOs = new ArrayList<>();

    @Autowired
    private ZooModuleService zooModuleService;

    public void mainMenu(){
        System.out.println("========== ZOO ADMIN MAIN MENU ==========");
        System.out.println("1. Setup Zoo Staff");
        System.out.println("2. Access Handler Module");
        System.out.println("3. Open Zoo to Visitors");
        System.out.println("4. Close Zoo to Visitors");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        boolean isComplete = true;
        do{
            Scanner optionScaner = new Scanner(System.in);
            Integer adminInput = 0;
            System.out.println("== Main Menu ==");
            System.out.println("Choose an option: ");

            while(true){
                if(optionScaner.hasNextInt()){
                    adminInput = optionScaner.nextInt();
                    if(adminInput >= 1 && adminInput <= 6){
                        break;
                    } else{
                        System.out.println("Invalid input. Kindly select correct option..");
                    }
                } else{
                    System.out.println("Invalid input. Kindly retry..");
                    optionScaner.next();
                }
            }

            switch (adminInput) {
                case 1: // Setup Zoo Staff

                    break;
                case 2: // Access Handler Module
                    break;
                case 3: // Open Zoo to Visitors
                    break;
                case 4: // Close Zoo to Visitors
                    break;
                case 5: // Logout
                    break;
                default:
            }

        } while(!isComplete);

    }
}
