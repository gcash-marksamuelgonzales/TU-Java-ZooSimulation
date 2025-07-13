package org.example.vo;

import lombok.Data;

@Data
public class AnimalVO {
    private Integer animalId;
    private String animalName;
    private String animalType;
    private String animalSpecies;
    private Integer animalStatus;

    public AnimalVO(){

    }

    public AnimalVO(Integer animalId, String animalName, String animalType, String animalSpecies, Integer animalStatus){
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalSpecies = animalSpecies;
        this.animalStatus = animalStatus;
    }
}
