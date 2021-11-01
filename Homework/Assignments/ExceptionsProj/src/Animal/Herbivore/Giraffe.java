package Animal.Herbivore;
import Animal.Animal;

/** The Giraffe class is simply a container class.
 * <p>
 *     Nothing is implemented to show how Animal's interface default method will be implemented instead.
 * </p>
 *
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_821
 */
public class Giraffe implements Animal
{
    private String name, type = "Giraffee";

    public Giraffe() {}
    public Giraffe(String name) {this.name = name;}

    @Override
    public String getName() { return this.name; }

    @Override
    public String getType(){ return this.type; }
}
