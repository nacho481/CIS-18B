import java.lang.*;

public interface IGenStack<T>
{
    void push(T obj) throws StackFullException;
    T pop() throws StackEmptyException;
}
