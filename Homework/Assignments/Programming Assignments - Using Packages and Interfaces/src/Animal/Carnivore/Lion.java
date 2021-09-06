package Animal.Carnivore;
import Animal.Animal;

/**
 * The Lion class implements one method called speak() which returns a string.
 * <p>
 *     Furthermore, it implements the interface Animal.
 * </p>
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_821
 */

public class Lion implements Animal
{

    /** The speak() method simply returns a string of what the lion says.
     *
     * @return The lion roars!
     */
    public String speak() {return "The lion roars!";}
}
