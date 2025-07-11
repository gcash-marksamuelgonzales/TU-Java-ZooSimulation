package org.example.vo;

import lombok.Data;

@Data
public class StaffVO {
    private String staffName;
    private Integer assignedRole;
    private Integer assignedPosition;

    public StaffVO(){

    }

    public StaffVO(String staffName, Integer assignedRole){
        this.staffName = staffName;
        this.assignedRole = assignedRole;
    }

    public StaffVO(String staffName, Integer assignedRole, Integer assignedPosition){
        this.staffName = staffName;
        this.assignedRole = assignedRole;
        this.assignedPosition = assignedPosition;
    }

    public void reset(){
        this.staffName = null;
        this.assignedRole = 0;
    }
}
