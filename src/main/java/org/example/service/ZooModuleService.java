package org.example.service;

import org.example.repository.ZooSetupRepository;
import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ZooModuleService {

    @Autowired
    private ZooSetupRepository zooSetupRepository;

    public ZooSetupVO zooSetup(ZooSetupVO zooSetupVO){
        // Manager
        Scanner managerScanner = new Scanner(System.in);
        System.out.println("Enter Manager Name: ");
        String managerName = managerScanner.nextLine();

        // Veterinarian
        Scanner vetScanner = new Scanner(System.in);
        System.out.println("Enter Veterinarian's Name: ");
        String vetName = vetScanner.nextLine();

        // Handler
        List<StaffVO> handlerList = generateStaffList();

        // Shop
        List<StaffVO> vendorList = generateVendorList();

        Integer addZooSetup = zooSetupRepository.addZooSetup(zooSetupVO, managerName, vetName, handlerList, vendorList);
        if(addZooSetup > 0){
            System.out.println("Zoo staff setup complete.");
        }

        return zooSetupVO;
    }

    public List<StaffVO> generateStaffList(){
        List<StaffVO> handlerList = new ArrayList<>();

        // Pachyderm
        Scanner pachydermScanner = new Scanner(System.in);
        System.out.println("Enter Handler for Pachyderm Enclosure: ");
        String pachydermStaff = pachydermScanner.nextLine();
        StaffVO pachyStaffVO = new StaffVO(pachydermStaff,2,1);
        handlerList.add(pachyStaffVO);

        // Feline
        Scanner felineScanner = new Scanner(System.in);
        System.out.println("Enter Handler for Feline Enclosure: ");
        String felineStaff = felineScanner.nextLine();
        StaffVO felineStaffVO = new StaffVO(felineStaff,2,2);
        handlerList.add(felineStaffVO);

        // Bird
        Scanner birdScanner = new Scanner(System.in);
        System.out.println("Enter Handler for Bird Enclosure: ");
        String birdStaff = birdScanner.nextLine();
        StaffVO birdStaffVO = new StaffVO(birdStaff,2,3);
        handlerList.add(birdStaffVO);

        return handlerList;
    }

    public List<StaffVO> generateVendorList(){
        List<StaffVO> vendorList = new ArrayList<>();

        // Ticket Shop
        Scanner ticketShopScanner = new Scanner(System.in);
        System.out.println("Enter Vendor for Ticket Shop: ");
        String ticketShopName = ticketShopScanner.nextLine();
        StaffVO ticketShopStaffVO = new StaffVO(ticketShopName, 3, 1);
        vendorList.add(ticketShopStaffVO);

        // Shop
        Scanner shopScanner = new Scanner(System.in);
        System.out.println("Enter Vendor for Shop: ");
        String shopName = shopScanner.nextLine();
        StaffVO shopStaffVO = new StaffVO(shopName,3,2);
        vendorList.add(shopStaffVO);

        return vendorList;
    }

    public boolean isOpen(Integer status, boolean isOpen){
        return isOpen = zooSetupRepository.setZooStatus(status, isOpen);
    }

}
