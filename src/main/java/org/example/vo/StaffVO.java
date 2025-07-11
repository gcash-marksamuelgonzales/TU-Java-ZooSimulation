package org.example.vo;

import lombok.Data;

@Data
public class StaffVO {
    private String staffName;
    private Integer assignedRole;

    public StaffVO(){

    }

    public StaffVO(String staffName, Integer assignedRole){
        this.staffName = staffName;
        this.assignedRole = assignedRole;
    }

    public void reset(){
        this.staffName = null;
        this.assignedRole = 0;
    }
}
