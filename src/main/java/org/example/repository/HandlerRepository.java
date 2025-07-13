package org.example.repository;

import org.example.vo.AnimalVO;
import org.example.vo.StaffVO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
public class HandlerRepository extends StaffVO {

    static Scanner scanner = new Scanner(System.in);

    public StaffVO validateView(List<StaffVO> staffVOs){
        System.out.println("Enter your name (Handler): ");
        String name = scanner.nextLine();

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

    public void pendingAnimals(List<AnimalVO> animalVOs, StaffVO staffVO){
        String animalType = (staffVO.getAssignedPosition() == 1) ? "Pachyderm" :
                (staffVO.getAssignedPosition() == 2) ? "Feline" :
                (staffVO.getAssignedPosition() == 3) ? "Bird" :
                "";

        long pendingList = animalVOs.stream()
                .filter(animal -> animal.getAnimalStatus() == 0 && animal.getAnimalType().equals(animalType))
                .count();

        while(pendingList > 0){
            System.out.println("--- Animal Duty Menu ---");
            System.out.println("Animals assigned to you:");
            for(AnimalVO animalVO : animalVOs){
                if(animalVO.getAnimalType().equals(animalType) && animalVO.getAnimalStatus() == 0){
                    System.out.printf("%s. %s%n",animalVO.getAnimalId(),animalVO.getAnimalName());
                }
            }

            Optional<AnimalVO> result = Optional.empty();

            while(!result.isPresent()){
                System.out.println("\n Choose animal number to interact with (0 to exit): ");
                Integer selectedAnimal = scanner.nextInt();
                result = animalVOs.stream()
                        .filter(animal -> animal.getAnimalId() == selectedAnimal && animal.getAnimalType().equals(animalType) && animal.getAnimalStatus() == 0)
                        .findFirst();

                if(result.isPresent()){
                    AnimalVO animalVO = result.get();
                    String performAction = performAction(animalVOs, animalVO);
                    if(performAction != ""){
                        System.out.println(performAction);
                        pendingList--;
                    }

                } else{
                    System.out.println("Invalid Id.. No such animal exists");
                }
            }
        }

        if(pendingList == 0){
            System.out.println("--- Animal Duty Menu ---");
            System.out.println("Animals assigned to you:");
            System.out.println("No assigned animals..");
        }

    }

    public Integer selectAction(AnimalVO animalVO){
        Integer selectedAction = 0;
        while(true){
            System.out.println("Choose Action:");
            System.out.printf("1. Feed %s%n",animalVO.getAnimalName());
            System.out.printf("2. Exercise %s%n",animalVO.getAnimalName());
            System.out.printf("3. Examine %s to Vet%n",animalVO.getAnimalName());
            System.out.println("===");
            System.out.println("\n Choose an option: ");

            if(scanner.hasNextInt()){
                selectedAction = scanner.nextInt();
                if(selectedAction>= 1 && selectedAction <= 3){
                    break;
                } else{
                    System.out.println("Invalid input for action.. Kindly retry");
                }
            }

        }
        return selectedAction;
    }

    public String performAction(List<AnimalVO> animalVOs, AnimalVO animalVO){
        String actionPerformed = "";
        Integer selectAction = selectAction(animalVO);

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = localDateTime.format(dtf);

        for(AnimalVO animal : animalVOs){
            if(animal.getAnimalId() == animalVO.getAnimalId()){
                switch(selectAction){
                    case 1: // Feed
                        actionPerformed = String.format("%s successfully fed at %s",animalVO.getAnimalName(),currentDate);
                        break;
                    case 2: // Exercise
                        actionPerformed = String.format("%s successfully provided exercise at %s",animalVO.getAnimalName(),currentDate);
                        break;
                    case 3: // Examine
                        actionPerformed = String.format("%s admitted at %s",animalVO.getAnimalName(),currentDate);
                        break;
                }
                animal.setAnimalStatus(1);
            }
        }

        return actionPerformed;
    }

}
