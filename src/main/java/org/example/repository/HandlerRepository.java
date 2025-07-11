package org.example.repository;

import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class HandlerRepository extends StaffVO {

    public StaffVO validateView(List<StaffVO> staffVOs){
        System.out.println("Enter your name: ");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();

        StaffVO staffVO = new StaffVO();
        for(StaffVO staff : staffVOs){
            if(name.equals(staff.getStaffName())){
                staffVO.setStaffName(staff.getStaffName());
                staffVO.setAssignedRole(staff.getAssignedRole());
            }
        }

        return staffVO;
    }
}
