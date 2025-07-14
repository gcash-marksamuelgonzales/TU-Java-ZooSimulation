package org.example.repository;

import org.example.vo.AnimalVO;
import org.example.vo.StaffVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
public class HandlerRepository extends StaffVO {

    static Scanner scanner = new Scanner(System.in);

    String filePath = "src/main/java/org/example/resources";

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

    public List<AnimalVO> retrieveAnimalList(String animalType){
        List<AnimalVO> animalVOs = new ArrayList<>();
        try{
            String fileName = String.format("%s/%s.csv",filePath,animalType);
            try(BufferedReader read = new BufferedReader(new FileReader(fileName))){
                String line;
                while((line = read.readLine()) != null){
                    String[] values = line.split(",");
                    AnimalVO animalVO = new AnimalVO();
                    animalVO.setAnimalName(values[0]);
                    animalVO.setAnimalType(values[1]);
                    animalVO.setAnimalSpecies(values[2]);
                    animalVO.setHealthStatus(Integer.parseInt(values[3]));
                    animalVO.setAnimalStatus(Integer.parseInt(values[4]));
                    animalVOs.add(animalVO);
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return animalVOs;
    }

    public void pendingAnimals(String animalSpecies, List<AnimalVO> animalVOs, StaffVO staffVO){
        long pendingList = animalVOs.stream()
                .filter(animal -> animal.getAnimalStatus() == 0 && animal.getAnimalSpecies().equals(animalSpecies))
                .count();

        while(pendingList > 0){
            Integer lineCount = 1;
            System.out.println("--- Animal Duty Menu ---");
            System.out.println("Animals assigned to you:");
            for(AnimalVO animalVO : animalVOs){
                animalVO.setAnimalId(lineCount);
                if(animalVO.getAnimalSpecies().equals(animalSpecies) && animalVO.getAnimalStatus() == 0){
                    System.out.printf("%s. %s%n",animalVO.getAnimalId(),animalVO.getAnimalName());
                    lineCount++;
                }
            }

            Optional<AnimalVO> result = Optional.empty();

            while(!result.isPresent()){
                System.out.println("\n Choose animal number to interact with (0 to exit): ");
                Integer selectedAnimal = scanner.nextInt();
                result = animalVOs.stream()
                        .filter(animal -> animal.getAnimalId() == selectedAnimal && animal.getAnimalSpecies().equals(animalSpecies) && animal.getAnimalStatus() == 0)
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

    public void updateList(String animalType, List<AnimalVO> animalVOs){
        try{
            String fileName = String.format("%s/%s.csv",filePath,animalType);
            File file = new File(fileName);
            Path path = file.toPath();
            List<String> newValues = new ArrayList<>();

            for(AnimalVO animalVO : animalVOs){
                String line = String.join(",",
                        animalVO.getAnimalName(),
                        animalVO.getAnimalType(),
                        animalVO.getAnimalSpecies(),
                        animalVO.getHealthStatus().toString(),
                        animalVO.getAnimalStatus().toString(),
                        animalVO.getPerformedAction().toString());
                newValues.add(line);
            }

            if(newValues.size() > 0){
                Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING).close();
                Files.write(path, newValues);
            }

        } catch (IOException ex){
            System.out.printf("Error encountered while processing update for Animal List (CSV), with error: %s",ex.toString());
            ex.printStackTrace();
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
            if(animal.getAnimalId() == animalVO.getAnimalId() && animal.getAnimalName().equals(animalVO.getAnimalName())){
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
                animal.setPerformedAction(selectAction);
                animal.setAnimalStatus(1);
            }
        }

        return actionPerformed;
    }

}
