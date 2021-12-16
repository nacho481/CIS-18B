package Animal.AtilanoIgnacioManuel;

/**
 * The Main class will test out the speak() method implemented
 * in the Animal interface and the Lion class
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_821
 */

public class Main
{
    // private static ArrayList<Animal> animalList = new ArrayList<Animal>();

    public static void main(String[] args)
    {
        new Frame();
    }

    /*
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
    public static void startThreads(int input)
    {
        ArrayList<Run> threadList = new ArrayList<Run>();
        for(int i = 0; i < input; i++)
        {
            Run run = Run.createAndStart("thread" + i, "output" + i + ".txt", animalList);
            threadList.add(run);
        }

        for(int i = 0; i < input; i++)
        {
            try {
                threadList.get(i).getThread().join();
            } catch (Exception e) {
                System.out.println("Error creating, running or finishing threads!");
            }
        }
    }*/
    /*int noThreads = 0;
        Scanner scan = new Scanner(System.in);
        final File outputFile = new File("output.txt");
        BufferedWriter fileWriter;

        System.out.println("How many Giraffes?");
        int gif = scan.nextInt();
        createAnimal("Giraffe", gif);

        System.out.println("How many Lions?");
        int lion = scan.nextInt();
        createAnimal("Lion", lion);

        System.out.println("How many threads: ");
        noThreads = scan.nextInt();

        try {
            fileWriter = new BufferedWriter(new FileWriter(outputFile));
            for(Animal currentAnimal : animalList)
            {
                fileWriter.write(currentAnimal.getName() + " is a " + currentAnimal.getType() + "\n");
                System.out.println(currentAnimal.getName() + " is a " + currentAnimal.getType());
            }
            fileWriter.close();
            System.out.println("\nSuccessfully outputted to file!\n" + outputFile.getAbsolutePath());
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace();}
        catch(Exception e) {System.err.println("Error!");}*/
}
