package org.example.vo;

import lombok.Data;

@Data
public class AnimalVO {
    private Integer animalId;
    private String animalName;
    private String animalType;
    private Integer animalStatus;

    public AnimalVO(){

    }

    public AnimalVO(Integer animalId, String animalName, String animalType, Integer animalStatus){
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalStatus = animalStatus;
    }
}
