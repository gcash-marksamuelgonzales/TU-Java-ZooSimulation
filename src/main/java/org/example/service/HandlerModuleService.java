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
        // Temporary List for Pending Animals
        List<AnimalVO> animalVOs = new ArrayList<>();
        AnimalVO animalVO = new AnimalVO(1,"Tiger", "Feline", 0);
        AnimalVO animalVO2 = new AnimalVO(2,"Lion", "Feline", 0);
        AnimalVO animalVO3 = new AnimalVO(3,"Cat", "Feline", 0);
        AnimalVO animalVO4 = new AnimalVO(4,"Elephants", "Pachyderm", 0);
        AnimalVO animalVO5 = new AnimalVO(5,"Rhinoceroses", "Pachyderm", 0);
        animalVOs.add(animalVO);
        animalVOs.add(animalVO2);
        animalVOs.add(animalVO3);
        animalVOs.add(animalVO4);
        animalVOs.add(animalVO5);

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
                                handlerView(staffVO,animalVOs);
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

    public void handlerView(StaffVO staffVO, List<AnimalVO> animalVOs){
        System.out.printf("Welcome, Handler %s!%n",staffVO.getStaffName());

        if(animalVOs.size() > 0){
            handlerRepository.pendingAnimals(animalVOs,staffVO);
        } else{
            System.out.println("No animals assigned to you!");
        }

//        // If data is available for reference
//        // Retrieve animals per type (staffVO.getAssignedRole())
//        List<AnimalVO> animalVOs = new ArrayList<>();
//        if(animalVOs.size() > 0){
//            System.out.println("Animals assigned to you:");
//            for(AnimalVO animalVO : animalVOs){
//                System.out.printf("%s. %s%n",animalVO.getAnimalId(),animalVO.getAnimalName());
//            }
//
//            // Choose Animal
//            while(true){
//                System.out.println("Choose animal number to interact with (0 to exit): ");
//            }
//
//        } else{
//            System.out.println("No animals assigned to you!");
//        }

    }

}
