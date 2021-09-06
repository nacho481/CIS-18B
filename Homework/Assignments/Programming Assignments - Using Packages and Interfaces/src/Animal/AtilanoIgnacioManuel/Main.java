package Animal.AtilanoIgnacioManuel;
import Animal.Carnivore.Lion;
import Animal.Herbivore.Giraffe;

/**
 * The Main class will test out the speak() method implemented
 * in the Animal interface and the Lion class
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_821
 */

public class Main
{
    public static void main(String [] args)
    {
        output();
    }

    public static void output()
    {
        Lion lionObj = new Lion();
        Giraffe giraffeObj = new Giraffe();
        System.out.print(lionObj.speak() + "\n\n" + giraffeObj.speak());
    }
/*OUTPUT
"C:\Program Files\Java\jdk-15.0.1\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\lib\idea_rt.jar=63650:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\Nacho\OneDrive\School\College different versions\College\4th year\Fall 2021\Classes\CIS-18B\Code\CIS-18B\Homework\Assignments\Programming Assignments - Using Packages and Interfaces\out\production\Programming Assignments - Using Packages and Interfaces;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-stdlib.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-reflect.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.1\plugins\Kotlin\kotlinc\lib\kotlin-test.jar" Animal.AtilanoIgnacioManuel.Main
The lion roars!

This animal doesn't speak
Process finished with exit code 0
*/
}
