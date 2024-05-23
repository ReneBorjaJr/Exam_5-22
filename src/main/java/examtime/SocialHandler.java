package examtime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SocialHandler {

    private Set<String> uniqueHandles;

    public SocialHandler() {
        this.uniqueHandles = new HashSet<>();
    }

    public String checkHandle(String handle) {
        if (handle == null) {
            throw new NullPointerException("Handle cannot be null!!");
        }
        if (handle.isBlank()) {
            throw new IllegalArgumentException("Handle cannot be blank");
        }
        if (handle.contains(" ")) {
            throw new IllegalArgumentException("No spaces!!");
        }
        handle = handle.toLowerCase();
        if (handle.length() > 9) {
            handle = handle.substring(0, 9);
        }
        return "@" + handle;
    }

    public void addHandle(String handle) {
        String validatedHandle = checkHandle(handle);
        uniqueHandles.add(validatedHandle);
    }

    public void removeHandle(String handle)  {
        String validatedHandle = checkHandle(handle);
        if (!uniqueHandles.contains(validatedHandle)) {
            throw new IllegalArgumentException("No such handle!");
        } else {
            uniqueHandles.remove(validatedHandle);
        }
    }

    public void updateHandle(String oldHandle, String newHandle) {
        String validatedOldHandle = checkHandle(oldHandle);
        String validatedNewHandle = checkHandle(newHandle);

        if (uniqueHandles.contains(validatedOldHandle) && !uniqueHandles.contains(validatedNewHandle)) {
            uniqueHandles.remove(validatedOldHandle);
            uniqueHandles.add(validatedNewHandle);
        }
    }

    public void addHandleToFile(String handle) {
        try {
            String validatedHandle = checkHandle(handle);
            File file = new File("C:\\Users\\reneb\\IdeaProjects\\Exam_05-22\\src\\main\\resources\\result.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            Scanner fileReader = new Scanner(file);
            boolean handleExists = false;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.equals(validatedHandle)) {
                    handleExists = true;
                    break;
                }
            }
            if (!handleExists) {
                fileWriter.write(validatedHandle + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printHandlesFromFile() {
        try {
            File file = new File("C:\\Users\\reneb\\IdeaProjects\\Exam_05-22\\src\\main\\resources\\result.txt");
            Scanner fileScanner = new Scanner(file);
            System.out.print("Results from file:" + "\n" + "Handles:\n");
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getUniqueHandles() {
        return uniqueHandles;
    }

}
