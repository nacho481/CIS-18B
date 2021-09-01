public class Ch3_9
{
    /*
    * 	9. The iteration expression in a for loop need not always alter the loop control variable by a fixed amount.
    * Instead, the loop control variable can change in any arbitrary way. Using this concept, write a program that uses
    * a for loop to generate and display the progression, 1, 2, 4, 8, 16, 32, and so on
     */
    public static void main(String []args)
    {
        System.out.println("The progress output is\n");

        for(int i = 1; i < 200; i+=i)
            System.out.print(i + " ");
    }
}
