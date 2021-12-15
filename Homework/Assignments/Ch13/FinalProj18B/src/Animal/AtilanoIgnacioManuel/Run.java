package Animal.AtilanoIgnacioManuel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import Animal.Animal;

public class Run implements Runnable
{

    //Run class variables
    public String threadName;
    private String filePathWithName;
    private ArrayList<Animal> animalList;
    private Thread thread;

    //File variables
    private File outputFile;
    private BufferedWriter fileWriter;


    //Constructor
    Run(String threadName, String filePathWithName, ArrayList<Animal> animalList)
    {
        this.threadName = threadName;
        this.filePathWithName = filePathWithName;
        this.animalList = animalList;
        thread = new Thread(this, threadName);
    }

    //Static factory method
    public static Run createAndStart(String threadName, String filePathWithName, ArrayList<Animal> animalList)
    {
        Run runObj = new Run(threadName, filePathWithName, animalList);
        runObj.thread.start();
        return runObj;
    }
    @Override
    public void run() { writeToFile(); }
    public Thread getThread() {return thread; }

    private void writeToFile()
    {
        try {
            outputFile = new File("" + filePathWithName);
            fileWriter = new BufferedWriter(new FileWriter(outputFile));
            for(Animal tempAnimal : animalList)
                fileWriter.write("" + tempAnimal.getName() + " is a " + tempAnimal.getType() + "\n");
            fileWriter.close();
            System.out.println("Successfully outputted to file!\n" + outputFile.getAbsolutePath());
        }catch(Exception e) {
            System.out.println("Error writing to file!");
        }
    }

}
