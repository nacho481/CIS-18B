import java.util.NoSuchElementException;

public class LinearQueue
{
    /*  Refers to first and last elements in the queue */
    private int front, rear;
    private int[] nums;

    public LinearQueue(int size)
    {
        front = rear = -1;
        nums = new int[size];   // specify queue size
    }
    /* Time Complexity and space complexity  O(1) */
    public void enqueue(int data)
    {
        /* First case, if the queue is full, throw an exception */
        if(isFull())
            throw new IllegalStateException();
        /* Second case, if the queue is empty, increment the front index by
        * 1 so you can refer to the first element in the queue */
        if(isEmpty())
            front++;    // refers to index 0
        nums[++rear] = data;
    }

    /* Time Complexity and space complexity O(1) */
    public int dequeue()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        int temp = nums[front];
        /* If the front and rear equal the SAME INDEX that means
        * that there is only ONE ELEMENT in the queue and you are
        * deleting that last element! */
        front = (front == rear) ? front = rear = -1: front++;
        return temp;
    }

    /* Time Complexity and space complexity O(1) */
    public int peek()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return nums[front];
    }

    /* Time Complexity and space complexity O(1) */
    public boolean isFull()
    {
        /* If rear is EQUAL to the length of the array
        * -1, then the queue is full. This will return
        * true if that's the case, the opposite applies */
        return rear == nums.length - 1;
    }

    /* Time Complexity O(1) and space complexity */
    public boolean isEmpty()
    {
        /* Check if the FRONT INDEX is equal to - 1
        * which means it is empty. If the FRONT INDEX
        * is equal to - 1, then it is empty, if not,
        * then it is not empty */
        return front == -1;
    }
}
