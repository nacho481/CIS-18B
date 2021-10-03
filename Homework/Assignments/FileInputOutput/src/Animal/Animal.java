package Animal;

/**
 * The animal interface is a blueprint on how other interfaces should implement an animal.
 * <p>
 *     There's is one a default implemented method called speak().
 * </p>
 * <p>
 *     Each animal should implement a speak method.
 * </p>
 * @author Ignacio-Manuel Atilano
 * @version %I% %G%
 * @since 1.8.0_281
 */
public interface Animal
{
    /** Default implementation for speak method. It simply returns a string.
     *
     * @return This animal doesn't speak
     */
    default String speak() {return "This animal doesn't speak";}
    default String getType(){return "This animal has no type"; }
    default String getName(){return "No name";}

}
