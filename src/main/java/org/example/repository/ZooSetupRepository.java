package org.example.repository;

import org.example.vo.AnimalVO;
import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZooSetupRepository extends ZooSetupVO {

    String filePath = "src/main/java/org/example/resources";

    public ZooSetupVO retrieveZooSetup(){
        ZooSetupVO zooSetupVO = new ZooSetupVO();
        List<StaffVO> handlerList = new ArrayList<>();
        List<StaffVO> shopList = new ArrayList<>();

        try{
            String fileName = String.format("%s/staff.csv",filePath);
            try(BufferedReader read = new BufferedReader(new FileReader(fileName))){
                String line;
                while((line = read.readLine()) != null){
                    String[] values = line.split(",");
                    StaffVO staffVO = new StaffVO();
                    zooSetupVO.setStaffId(Integer.parseInt(values[0]));
                    switch(values[2]){
                        case "Manager":
                            String managerName = (!values[1].equals(null) && !values[1].equals("")) ? values[1] : "";
                            zooSetupVO.setManagerName(managerName);
                            break;
                        case "Veterinarian":
                            String veterinarianName = (!values[1].equals(null) && !values[1].equals("")) ? values[1] : "";
                            zooSetupVO.setManagerName(veterinarianName);
                            break;
                        case "Handler":
                            staffVO.setStaffName(values[1]);
                            staffVO.setAssignedRole(2);
                            Integer handlerPosition = values[3].equals("Pachyderm") ? 1 :
                                    values[3].equals("Feline") ? 2 :
                                            values[3].equals("Bird") ? 3 :
                                                    0;
                            staffVO.setAssignedPosition(handlerPosition);
                            handlerList.add(staffVO);
                            break;
                        case "Shop":
                            staffVO.setStaffName(values[1]);
                            staffVO.setAssignedRole(2);
                            Integer shopPosition = values[3].equals("Ticket Shop") ? 1 :
                                    values[3].equals("Shop") ? 2 :
                                            0;
                            staffVO.setAssignedPosition(shopPosition);
                            shopList.add(staffVO);
                            break;
                    }
                }
                zooSetupVO.setHandlerList(handlerList);
                zooSetupVO.setVendorList(shopList);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return zooSetupVO;
    }

    public Integer addZooSetup(ZooSetupVO zooSetupVO, String managerName, String veterinarianName, List<StaffVO> handlerList, List<StaffVO> vendorList){
        zooSetupVO.setManagerName(managerName);
        zooSetupVO.setVeterinarianName(veterinarianName);
        zooSetupVO.setHandlerList(handlerList);
        zooSetupVO.setVendorList(vendorList);
        return (zooSetupVO.getManagerName() != null) ? 1 : 0;
    }

    public void updateZooSetup(ZooSetupVO zooSetupVO){
        try{
            String fileName = String.format("%s/staff.csv",filePath);
            File file = new File(fileName);
            Path path = file.toPath();
            List<String> newValues = new ArrayList<>();

            String line = "";

            // Manager
            if(!zooSetupVO.getManagerName().equals(null) && !zooSetupVO.getManagerName().equals("")){
                line = String.join(",",
                        zooSetupVO.getStaffId().toString(),
                        zooSetupVO.getManagerName(),
                        "Manager",
                        "");
                newValues.add(line);
            }

            // Veterinarian
            if(!zooSetupVO.getVeterinarianName().equals(null) && !zooSetupVO.getVeterinarianName().equals("")){
                line = String.join(",",
                        zooSetupVO.getStaffId().toString(),
                        zooSetupVO.getVeterinarianName(),
                        "Veterinarian",
                        "");
                newValues.add(line);
            }

            // Handler
            for(StaffVO handler : zooSetupVO.getHandlerList()){
                String assignedPosition = switch(handler.getAssignedPosition()){
                    case 1 -> "Pachyderm";
                    case 2 -> "Feline";
                    case 3 -> "Bird";
                    default -> "";
                };
                line = String.join(",",
                        zooSetupVO.getStaffId().toString(),
                        handler.getStaffName(),
                        "Handler",
                        assignedPosition);
                newValues.add(line);
            }

            // Vendor
            for(StaffVO vendor : zooSetupVO.getVendorList()){
                String assignedPosition = switch(vendor.getAssignedPosition()){
                    case 1 -> "Ticket Shop";
                    case 2 -> "Shop";
                    default -> "";
                };
                line = String.join(",",
                        zooSetupVO.getStaffId().toString(),
                        vendor.getStaffName(),
                        "Shop",
                        assignedPosition);
                newValues.add(line);
            }

            if(newValues.size() > 0){
                Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING).close();
                Files.write(path, newValues);
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public boolean setZooStatus(Integer status,boolean isOpen){
        if(status == 1){
            return true;
        } else{
            return false;
        }
    }
}
