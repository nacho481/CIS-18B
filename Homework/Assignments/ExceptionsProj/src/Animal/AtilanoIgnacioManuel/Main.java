package Animal.AtilanoIgnacioManuel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Animal.Animal;
import Animal.Herbivore.Giraffe;
import Animal.Carnivore.Lion;

/**
 * The Main class will test out the speak() method implemented
 * in the Animal interface and the Lion class
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_821
 */

public class Main
{
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        final File outputFile = new File("output.txt");
        BufferedWriter fileWriter;

        //System.out.println("How many Giraffes?");
        //int gif = scan.nextInt();
        createAnimal("Giraffe", 1);

        //System.out.println("How many Lions?");
        //int lion = scan.nextInt();
        createAnimal("Lion", 1);

        try {
            fileWriter = new BufferedWriter(new FileWriter(outputFile));
            for(Animal currentAnimal : animalList)
            {
                fileWriter.write(currentAnimal.getName() + " is a " + currentAnimal.getType() + "\n");
                //System.out.println(currentAnimal.getName() + " is a " + currentAnimal.getType());
            }
            fileWriter.close();
            // System.out.println("\nSuccessfully outputted to file!\n" + outputFile.getAbsolutePath());
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(Exception e) {e.printStackTrace();}

        userPrompt();

    }

    public static void userPrompt()
    {
        System.out.println("There are " + animalList.size() + " in the array\n"
         + "Enter a number to find the animal at that location!\n");

        try{
            Scanner scan = new Scanner(System.in);
            int temp = scan.nextInt();
            //String animalName = animalList.get(temp).getName();
            System.out.println("This animal is a " +
                    animalList.get(temp).getType() + "!");
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("You entered an invalid number!");
        }
        finally{
            System.out.println("Goodbye!");
        }
    }

    //7. Using a loop, create a Lion for each lion the user asked for, using the names from the file to create the name.  Do the same thing for the giraffes (you can use the same name list for both).  I recommend using an ArrayList.
    public static void createAnimal(String animal, int noAnimals)
    {
        try {
            final File namesFile = new File("names.txt");
            Scanner fileReader = new Scanner(namesFile);
            if(animal.equalsIgnoreCase("Giraffe"))
            {
                for(int x = noAnimals; x > 0; --x)
                    animalList.add(new Giraffe(fileReader.nextLine()));
            }
            else if(animal.equalsIgnoreCase("Lion"))
            {
                for(int x = noAnimals; x > 0; --x)
                    animalList.add(new Lion(fileReader.nextLine()));
            }
            fileReader.close();
        }catch(Exception e) {
            System.err.println("Error!");
        }
    }
}

/* OUTPUT
"C:\Program Files\Java\jdk-15.0.1\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\lib\idea_rt.jar=49841:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\Nacho\OneDrive\School\College different versions\College\4th year\Fall 2021\Classes\CIS-18B\Code\CIS-18B\Homework\Assignments\ExceptionsProj\out\production\ExceptionsProj;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-stdlib.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-reflect.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-test.jar" Animal.AtilanoIgnacioManuel.Main
There are 2 in the array
Enter a number to find the animal at that location!

2
You entered an invalid number!
Goodbye!

Process finished with exit code 0
*/