

public class IQDemo {

}

// A fixed-size queue class for characters
class FixedQueue implements ICharQ
{
    private char q[];   // This array holds the queue
    private int putloc, getloc; // the put and get indicies

    public FixedQueue(int size)
    {
        q = new char[size]; // Allocate memory for queue
        putloc = getloc = 0;
    }

    // Put a character into the queue
    public void put(char ch)
    {
        if(putloc == q.length)
        {
            System.out.println(" - Queue is full.");
            return;
        }
        q[putloc++] = ch;
    }

    // Get character from the queue
    public char get()
    {
        if(putloc == getloc)
        {
            System.out.println(" - Queue is empty.");
            return (char) 0;
        }
        return q[getloc++];
    }
}

class CircularQueue implements ICharQ
{
    private char q[];   // This array holds a queue
    private int putloc, getloc; // The put and get indicies

    public CircularQueue(int size)
    {
        q = new char[size + 1];
        putloc = getloc = 0;
    }

    public void put(char ch)
    {
        /*
        * Queue is full if either putloc is one less than getloc, or if
        * putloc is at the end of the array and getloc is at the beginning.
        * */
        if(putloc+1 == getloc | ((putloc == q.length - 1) & (getloc == 0)))
        {
            System.out.println(" - Queue is full.");
            return;
        }
        q[putloc++] = ch;
        if(putloc ==q.length) putloc = 0; // loop back
    }

    public char get()
    {
        if(getloc == putloc)
        {
            System.out.println(" - Queue is empty");
        }

        char ch = q[getloc++];
        if(getloc == q.length) getloc = 0;  // loop back
        return ch;
    }



}