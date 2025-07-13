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

    static Scanner scanner = new Scanner(System.in);

    @Autowired
    private ZooModuleService zooModuleService;

    @Autowired
    private HandlerRepository handlerRepository;

    public void accessHandlerView(ZooSetupVO zooSetupVO){
        System.out.println("=== Welcome to Zoo Handler Module ===");
        System.out.println("1. Access My Module");
        System.out.println("2. Exit");
        System.out.println("===");
        Integer handlerInput = 0;
        while(true){
            System.out.println("\n == Handler Menu ==");
            System.out.println("Choose an option: ");
            if(scanner.hasNextInt()){
                handlerInput = scanner.nextInt();
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
                scanner.next();
            }
        }
    }

    public void handlerView(StaffVO staffVO){
        String animalType = switch(staffVO.getAssignedPosition()){
            case 1 -> "Pachyderm";
            case 2 -> "Feline";
            case 3 -> "Bird";
            default -> "none";
        };

        List<AnimalVO> animalVOs = handlerRepository.retrieveAnimalList(animalType);
        System.out.printf("Welcome, Handler %s!%n",staffVO.getStaffName());
        if(animalVOs.size() > 0){
            handlerRepository.pendingAnimals(animalType,animalVOs,staffVO);
            handlerRepository.updateList(animalType,animalVOs);
        } else{
            System.out.println("No animals assigned to you!");
        }
    }

}
