package org.example.repository;

import org.example.vo.StaffVO;
import org.example.vo.ZooSetupVO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ZooSetupRepository extends ZooSetupVO {

    public Integer addZooSetup(ZooSetupVO zooSetupVO, String managerName, String veterinarianName, List<StaffVO> handlerList, List<StaffVO> vendorList){
        zooSetupVO.setManagerName(managerName);
        zooSetupVO.setVeterinarianName(veterinarianName);
        zooSetupVO.setHandlerList(handlerList);
        zooSetupVO.setVendorList(vendorList);
        return (zooSetupVO.getManagerName() != null) ? 1 : 0;
    }

    public boolean setZooStatus(Integer status,boolean isOpen){
        if(status == 1){
            return true;
        } else{
            return false;
        }
    }
}
