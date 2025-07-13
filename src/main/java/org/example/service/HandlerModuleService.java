package org.example.service;

import org.example.repository.HandlerRepository;
import org.example.vo.AnimalVO;
import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class HandlerModuleService {

    @Autowired
    private ZooModuleService zooModuleService;

    @Autowired
    private HandlerRepository handlerRepository;

    public void accessHandlerView(ZooSetupVO zooSetupVO){
        System.out.println("=== Welcome to Zoo Handler Module ===");
        System.out.println("1. Access My Module");
        System.out.println("2. Exit");
        System.out.println("===");
        Scanner handlerInputScanner = new Scanner(System.in);
        Integer handlerInput = 0;
        while(true){
            System.out.println("== Handler Menu ==");
            System.out.println("Choose an option: ");
            if(handlerInputScanner.hasNextInt()){
                handlerInput = handlerInputScanner.nextInt();
                if(handlerInput >= 1 && handlerInput <= 2){
                    switch(handlerInput){
                        case 1:
                            StaffVO staffVO = handlerRepository.validateView(zooSetupVO.getHandlerList());
                            if(staffVO.getAssignedRole() != null){
                                handlerView(staffVO);
                            } else{
                                System.out.println("User is not registered under Handlers..");
                            }
                            break;

                        case 2:
                            System.out.println("Thank you!");
                            System.exit(0);
                            break;
                    }
                } else{
                    System.out.println("Invalid input. Kindly retry..");
                }
            } else{
                System.out.println("Invalid input. Kindly retry..");
                handlerInputScanner.next();
            }
        }
    }

    public void handlerView(StaffVO staffVO){
        System.out.printf("Welcome, Handler %s%n!",staffVO.getStaffName());
        System.out.println("--- Animal Duty Menu ---");

        // Retrieve animals per type (staffVO.getAssignedRole())
        List<AnimalVO> animalVOs = new ArrayList<>();
        if(animalVOs.size() > 0){
            System.out.println("Animals assigned to you:");
            for(AnimalVO animalVO : animalVOs){
                System.out.printf("%s. %s%n",animalVO.getAnimalId(),animalVO.getAnimalName());
            }

            // Choose Animal
            while(true){
                System.out.println("Choose animal number to interact with (0 to exit): ");
            }

        } else{
            System.out.println("No animals assigned to you!");
        }

        // Temporary
//        List<AnimalVO> animalVOs = new ArrayList<>();
//        AnimalVO animalVO = new AnimalVO("Cat", "Feline");
//        AnimalVO animalVO2 = new AnimalVO("Cat2", "Feline");
//        AnimalVO animalVO3 = new AnimalVO("Pachyderm", "Pachyderm");
//        animalVOs.add(animalVO);
//        animalVOs.add(animalVO2);
//        animalVOs.add(animalVO3);
//
//        if(animalVOs.size() > 0){
//            System.out.println("Animals assigned to you:");
//            for(AnimalVO animalVO1 : animalVOs){
//                Integer lineCount = 1;
//                switch(staffVO.getAssignedPosition()){
//                    case 1: // Pachyderm
//                        if(animalVO1.getAnimalType().equals("Pachyderm")){
//                            System.out.printf("%s. %s%n",lineCount,animalVO1.getAnimalName());
//                            lineCount++;
//                        }
//                        break;
//                    case 2: // Feline
//                        if(animalVO1.getAnimalType().equals("Feline")){
//                            System.out.printf("%s. %s%n",lineCount,animalVO1.getAnimalName());
//                            lineCount++;
//                        }
//                        break;
//                    case 3: // Bird
//                        if(animalVO1.getAnimalType().equals("Bird")){
//                            System.out.printf("%s. %s%n",lineCount,animalVO1.getAnimalName());
//                            lineCount++;
//                        }
//                        break;
//                }
//            }
//        } else{
//            System.out.println("No animals assigned to you!");
//        }
    }
}
