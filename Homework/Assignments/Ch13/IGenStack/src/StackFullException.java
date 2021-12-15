public class StackFullException extends Exception
{
    int size;
    StackFullException(int s ) {size = s;}
    public String toString(){return "\nStack is full";}
    // StackEmptyException
}
