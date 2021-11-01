enum Tools
{
    SCREWDRIVER, WRENCH, HAMMERS, PLIERS
}

public class Main
{
    public static void main(String args[])
    {
        for(Tools t: Tools.values())
            System.out.print(t + " contains ordinal value of " +
                    t.ordinal() + "\n");
    }
}
