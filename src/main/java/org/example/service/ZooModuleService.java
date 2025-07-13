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

    static Scanner scanner = new Scanner(System.in);

    @Autowired
    private ZooSetupRepository zooSetupRepository;

    public ZooSetupVO zooSetup(ZooSetupVO zooSetupVO){
        zooSetupVO = zooSetupRepository.retrieveZooSetup();
        if(zooSetupVO != null){
            String modify = "";
            while(true){
                System.out.println("Retrieved Zoo Setup. Do you want to modify? [Y/N]");
                if(scanner.hasNextLine()){
                    modify = scanner.nextLine();
                    if(modify.equalsIgnoreCase("y") || modify.equalsIgnoreCase("n")){
                        break;
                    } else{
                        System.out.println("Invalid input. Kindly retry..");
                    }
                }
            }

            if(modify.equalsIgnoreCase("y")){
                zooSetupVO = generateZooSetup();
            }

        } else{

        }

        return zooSetupVO;
    }

    public ZooSetupVO generateZooSetup(){
        ZooSetupVO zooSetupVO = new ZooSetupVO();

        // Manager
        System.out.println("Enter Manager Name: ");
        String managerName = scanner.nextLine();

        // Veterinarian
        System.out.println("Enter Veterinarian's Name: ");
        String vetName = scanner.nextLine();

        // Handler
        List<StaffVO> handlerList = generateStaffList();

        // Shop
        List<StaffVO> vendorList = generateVendorList();

        Integer addZooSetup = zooSetupRepository.addZooSetup(zooSetupVO, managerName, vetName, handlerList, vendorList);
        if(addZooSetup > 0){
            zooSetupRepository.updateZooSetup(zooSetupVO);
        }

        return zooSetupVO;
    }

    public List<StaffVO> generateStaffList(){
        List<StaffVO> handlerList = new ArrayList<>();

        // Pachyderm
        System.out.println("Enter Handler for Pachyderm Enclosure: ");
        String pachydermStaff = scanner.nextLine();
        StaffVO pachyStaffVO = new StaffVO(pachydermStaff,2,1);
        handlerList.add(pachyStaffVO);

        // Feline
        System.out.println("Enter Handler for Feline Enclosure: ");
        String felineStaff = scanner.nextLine();
        StaffVO felineStaffVO = new StaffVO(felineStaff,2,2);
        handlerList.add(felineStaffVO);

        // Bird
        System.out.println("Enter Handler for Bird Enclosure: ");
        String birdStaff = scanner.nextLine();
        StaffVO birdStaffVO = new StaffVO(birdStaff,2,3);
        handlerList.add(birdStaffVO);

        return handlerList;
    }

    public List<StaffVO> generateVendorList(){
        List<StaffVO> vendorList = new ArrayList<>();

        // Ticket Shop
        System.out.println("Enter Vendor for Ticket Shop: ");
        String ticketShopName = scanner.nextLine();
        StaffVO ticketShopStaffVO = new StaffVO(ticketShopName, 3, 1);
        vendorList.add(ticketShopStaffVO);

        // Shop
        System.out.println("Enter Vendor for Shop: ");
        String shopName = scanner.nextLine();
        StaffVO shopStaffVO = new StaffVO(shopName,3,2);
        vendorList.add(shopStaffVO);

        return vendorList;
    }

    public boolean isOpen(Integer status, boolean isOpen){
        return isOpen = zooSetupRepository.setZooStatus(status, isOpen);
    }

}
