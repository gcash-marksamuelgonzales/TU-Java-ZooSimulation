package org.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class ZooSetupVO {
    private String managerName;
    private String veterinarianName;
    private List<StaffVO> handlerList;
    private List<StaffVO> vendorList;

    public ZooSetupVO(){

    }

    public ZooSetupVO(String managerName, String veterinarianName, List<StaffVO> handlerList, List<StaffVO> vendorList){
        this.managerName = managerName;
        this.veterinarianName = veterinarianName;
        this.handlerList = handlerList;
        this.vendorList = vendorList;
    }

}
