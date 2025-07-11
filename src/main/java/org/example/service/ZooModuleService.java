package org.example.service;

import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ZooModuleService {

    public List<ZooSetupVO> zooSetup(List<ZooSetupVO> zooSetupVOs){
        // Manager
        Scanner managerScanner = new Scanner(System.in);
        System.out.println("Enter Manager Name: ");
        String managerName = managerScanner.nextLine();

        // Veterinarian
        Scanner vetScanner = new Scanner(System.in);
        System.out.println("Enter Veterinarian's Name: ");
        String vetName = vetScanner.nextLine();

        // Handler
//        List<StaffVO> handlerList = retrieveHandlerList();

        ZooSetupVO zooSetupVO = new ZooSetupVO();
        zooSetupVO.setManagerName(managerName);
        zooSetupVO.setVeterinarianName(vetName);

        // Handlers
//        StaffVO staffVO = new StaffVO();
//        staffVO.setStaffName();
//        zooSetupVO.setHandlerList();

        return zooSetupVOs;
    }

//    public List<StaffVO> retrieveHandlerList(){
//        List<StaffVO> handlerList = new ArrayList<>();
//        StaffVO staffVO = new StaffVO();
//
//        // Pachyderm
//        Scanner pachydermScanner = new Scanner(System.in);
//        System.out.println("Enter Handler for Pachyderm Enclosure: ");
//        String pachydermStaff = pachydermScanner.nextLine();
//        staffVO.setStaffName(pachydermStaff);
//        staffVO.setAssignedRole(1);
//        handlerList.add(staffVO);
//        staffVO.
//
//        // Feline
//        Scanner felineScanner = new Scanner(System.in);
//        System.out.println("Enter Handler for Feline Enclosure: ");
//        String felineStaff = felineScanner.nextLine();
//
//        // Bird
//        Scanner birdScanner = new Scanner(System.in);
//        System.out.println("Enter Handler for Bird Enclosure: ");
//        String birdStaff = birdScanner.nextLine();
//
//        return handlerList;
//    }
}
