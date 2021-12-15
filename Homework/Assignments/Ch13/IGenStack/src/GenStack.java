public class GenStack <T> implements IGenStack<T>
{
    private final T[] stack;
    private int top;    // top of stack

    GenStack(T[] stackArray) { stack = stackArray; top = 0; }
    GenStack(T[] stackArray, GenStack o) {
        top = o.top;
        stack = stackArray;
        try {
            if(stack.length < o.stack.length)
                throw new StackFullException(stack.length);
        }
        catch (StackFullException e) { e.printStackTrace(); }
        // copy stack elements
        for(int x = 0; x < top; ++x)
            stack[x] = (T) o.stack[x];
    }

    GenStack(T[] stckArray, T[] list) {
        stack = stckArray;
        for (T t : list) {
            try { push(t);}
            catch (StackFullException e) { System.out.println(e);}
        }
    }

    @Override
    public void push(T obj) throws StackFullException {
        if(top == stack.length)
            throw new StackFullException(stack.length);
        stack[top] = obj;
        top++;
    }

    @Override
    public T pop() throws StackEmptyException {
        if(top == 0)
            throw new StackEmptyException();
        top--;
        return stack[top];
    }

}
