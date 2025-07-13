package org.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class ZooSetupVO {
    private Integer staffId;
    private String managerName;
    private String veterinarianName;
    private List<StaffVO> handlerList;
    private List<StaffVO> vendorList;

    public ZooSetupVO(){

    }

    public ZooSetupVO(Integer staffId, String managerName, String veterinarianName, List<StaffVO> handlerList, List<StaffVO> vendorList){
        this.staffId = staffId;
        this.managerName = managerName;
        this.veterinarianName = veterinarianName;
        this.handlerList = handlerList;
        this.vendorList = vendorList;
    }

}
