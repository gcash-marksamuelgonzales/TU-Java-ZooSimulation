package org.example.service;

import org.example.repository.HandlerRepository;
import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                                switch(staffVO.getAssignedRole()){
                                    case 1:
                                        veterinarianView();
                                        break;
                                    case 2:
                                        handlerView();
                                        break;
                                }
                            } else{
                                System.out.println("User is not registered for Handlers..");
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

    public void veterinarianView(){
        System.out.println("Veterinarian");
    }

    public void handlerView(){
        System.out.println("Handler");
    }
}
