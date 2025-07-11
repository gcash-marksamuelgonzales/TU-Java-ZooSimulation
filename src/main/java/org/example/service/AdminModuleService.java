package org.example.service;

import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class AdminModuleService {

    ZooSetupVO zooSetupVO = new ZooSetupVO();
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String currentDate = localDate.format(dtf);

    @Autowired
    private ZooModuleService zooModuleService;

    @Autowired
    private HandlerModuleService handlerModuleService;

    public void mainMenu(boolean isOpen){
        System.out.println("========== ZOO ADMIN MAIN MENU ==========");
        System.out.println("1. Setup Zoo Staff");
        System.out.println("2. Access Handler Module");
        System.out.println("3. Open Zoo to Visitors");
        System.out.println("4. Close Zoo to Visitors");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.println("==========");
        boolean isComplete = true;
        while(isComplete){
            Scanner optionScaner = new Scanner(System.in);
            Integer adminInput = 0;

            while(true){
                System.out.println("== Admin Menu ==");
                System.out.println("Choose an option: ");
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
                    if(zooSetupVO.getManagerName() == null || zooSetupVO.getManagerName().equals("")){
                        zooSetupVO = zooModuleService.zooSetup(zooSetupVO);
                    } else{
                        System.out.println("Zoo Setup already configured!");
                    }
                    break;
                case 2: // Access Handler Module
                    if(zooSetupVO.getManagerName() == null || zooSetupVO.getManagerName().equals("")){
                        System.out.println("No assigned roles in Zoo.. Kindly process Zoo Setup first.");
                    } else{
                        handlerModuleService.accessHandlerView(zooSetupVO);
                    }
                    break;
                case 3: // Open Zoo to Visitors
                    isOpen = zooModuleService.isOpen(1,isOpen);
                    if(isOpen){
                        System.out.printf("Process Complete. Zoo is now open! [Process Date: %s%n]",currentDate);
                    }
                    break;
                case 4: // Close Zoo to Visitors
                    isOpen = zooModuleService.isOpen(0,isOpen);
                    if(isOpen){
                        System.out.printf("Process Complete. Zoo is now closed! [Process Date: %s%n]",currentDate);
                    }
                    break;
                case 5: // Logout
                    while(true){
                        System.out.println("Are you sure you want to logout? [Y/N]");
                        Scanner logScanner = new Scanner(System.in);
                        String input = "";
                        if(logScanner.hasNextLine()){
                            input = logScanner.nextLine();
                            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")){
                                isComplete = false;
                                break;
                            } else{
                                System.out.println("Invalid input. Kindly retry..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            logScanner.next();
                        }
                    }

                    break;
                default:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }

    }
}
