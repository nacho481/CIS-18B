import java.util.NoSuchElementException;

public class CircularQueue
{
    /* Front refers to front elements and rear refers to rear elements*/
    private int front, rear;
    private int[] nums;

    public CircularQueue(int initialSize)
    {
        front = rear = -1;
        nums = new int[initialSize];
    }

    public void enqueue(int data)
    {
        /* 1st case: Check if queue is full because if it is, you will have to resize it */
        if(isFull())
            resize();
        /* Check if the queue is empty, because if it is, then that means front = -1
        * and you need to change front = 0 to refer to the first index of the queue */
        else if(isEmpty())
            front++;
        rear = (rear + 1) % nums.length;    // goes to next index in circular queue
        nums[rear] = data;  // Insert data at REAR INDEX
    }
    private void resize()
    {
        /* Used to hold elements from old queue */
        int[] tempArr = new int[nums.length*2]; // Resize array
        /* i = 0, used to traverse through new queue
        * j = front, used to traverse old queue, set to front of old queue */
        int i = 0, j = front;
        /* Good idea to use do-while loop to traverse through old queue */
        do {
            tempArr[i++] = nums[j];
            j = (j+1) % nums.length;
        }while(j != front);

        front = 0;
        rear = nums.length - 1;
        nums = tempArr;
    }
    public int dequeue()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        int temp = nums[front];
        /* If the FRONT index EQUALS the REAR index then that means there is only
        * 1 element in the circular queue, then you would set the indices equal
        * to - 1 to delete the circular queue. If the FRONT index and the REAR index
        * are not equal, then proceed to access the very FRONT index
        * TERNARY operator used
        * We remove the front element because remember we use the FIFO method */
        front = (front == rear) ? front = rear = -1
                : (front + 1) % nums.length;
        return temp;
    }



    public int peek()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return nums[front];
    }



    public boolean isEmpty()
    {
        /* If front equals -1, that means the queue is empty,
        * if it is not equal to -1, then the circular queue is
        * holding data */
        return front == -1;
    }

    public boolean isFull()
    {

    }


}
