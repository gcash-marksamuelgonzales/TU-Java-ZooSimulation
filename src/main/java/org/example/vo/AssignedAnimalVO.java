package org.example.vo;

import lombok.Data;

@Data
public class AssignedAnimalVO {
    private String animalName;
    private String animalType;

    public AssignedAnimalVO(){

    }

    public AssignedAnimalVO(String animalName, String animalType){
        this.animalName = animalName;
        this.animalType = animalType;
    }
}
