package org.example.repository;

import org.example.vo.AnimalVO;
import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class HandlerRepository extends StaffVO {

    public StaffVO validateView(List<StaffVO> staffVOs){
        System.out.println("Enter your name (Handler): ");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();

        StaffVO staffVO = new StaffVO();
        for(StaffVO staff : staffVOs){
            if(name.equals(staff.getStaffName()) && staff.getAssignedRole() == 2){
                staffVO.setStaffName(staff.getStaffName());
                staffVO.setAssignedRole(staff.getAssignedRole());
                staffVO.setAssignedPosition(staff.getAssignedPosition());
            }
        }

        return staffVO;
    }

    public AnimalVO assignedAnimal(List<AnimalVO> animalVOs, Integer animalId){
        for(AnimalVO animalVO : animalVOs){
            if(animalVO.getAnimalId() == animalId){
                return animalVO;
            }
        }
        return null;
    }
}
