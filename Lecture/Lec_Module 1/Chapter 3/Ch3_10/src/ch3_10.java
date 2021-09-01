import java.util.Scanner;
public class ch3_10
{
    /*
    1. ASCII values
        capital letters: 65-90
        lowercase letters: 97-122
    2. Subtract LOWERCASE letters by 32 to convert to UPPERCASE
    3. Add UPPERCASE letters by 32 to convert to LOWERCASE
    4. Get user string value
    5. Stop when user enters a period
    6. Display no. of case changes



    */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a '.' at the end to terminate the program");
        System.out.print("Enter a string: ");
        String input =  scan.nextLine();
        char[] inputToCharArray = input.toCharArray();
        int counter = 0;
        for(int x = 0; inputToCharArray[x] != '.'; ++x)
        {
            // 'a' = 97, 'z' = 122 in ASCII
            // Test Case: Lowercase letters
            if(inputToCharArray[x] >= 'a' & inputToCharArray[x]<='z')
            {
                char temp = inputToCharArray[x];
                temp -= 32;
                inputToCharArray[x] = temp;
                System.out.print(inputToCharArray[x] + " ");
                ++counter;
            }
            else if(inputToCharArray[x] >= 'A' & inputToCharArray[x]<='Z')
            {
                char temp = inputToCharArray[x];
                temp += 32;
                inputToCharArray[x] = temp;
                System.out.print(inputToCharArray[x] + " ");
                ++counter;
            }
        }
        System.out.println("\nNo. of changes: " + counter + "\n");
    }
}
