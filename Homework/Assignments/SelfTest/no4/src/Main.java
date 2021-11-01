
enum TrafficLightColor
{
    RED(12000), GREEN(10000), YELLOW(2000); // Modify the enum values from the beginning
    private int dly;

    TrafficLightColor(int d){dly = d;}
    int getDly(){return dly;}
}

class TrafficLightSimulator implements Runnable
{
    private Thread thrd_smltr;          // variable that holds thread to execute the simulation
    private TrafficLightColor tlc;      // Holds traffic light color
    private boolean stop = false;       // set to true to stop the simulation
    private boolean changed = false;    // true when the light has been changed
    TrafficLightSimulator()
    {
        tlc = TrafficLightColor.RED;
        thrd_smltr = new Thread(this);
        thrd_smltr.start();
    }
    TrafficLightSimulator(TrafficLightColor init)
    {
        tlc = init;
        thrd_smltr = new Thread(this);
        thrd_smltr.start();
    }

    // start up the light
    public void run()
    {
        while(!stop)
        {
            try{
                Thread.sleep(tlc.getDly());
                /*switch (tlc) {
                    case GREEN -> Thread.sleep(10000);    // green for 10 seconds
                    case YELLOW -> Thread.sleep(2000);     // yellow for 2 seconds
                    case RED -> Thread.sleep(12000);    // Red for 12 seconds
                    default -> throw new IllegalStateException("Unexpected value: " + tlc);
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            changeColor();
        }
    }

    synchronized void changeColor()
    {
        switch (tlc) {
            case RED -> tlc = TrafficLightColor.GREEN;
            case YELLOW -> tlc = TrafficLightColor.RED;
            case GREEN -> tlc = TrafficLightColor.YELLOW;
        }
        changed = true;
        notify();       // Signal that the  light has changed
    }

    synchronized void waitForChange()
    {
        try{
            while(!changed)
            {
                wait();     // Wait for light to change
                changed = false;
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }

    }
    synchronized TrafficLightColor getColor(){return tlc;}
    synchronized void cancel(){stop = true;}
}

public class Main
{
    public static void main(String args[])
    {
        TrafficLightSimulator t1 = new TrafficLightSimulator(TrafficLightColor.GREEN);
        Thread third = new Thread(t1);
        third.start();

        for(int x = 0; x < 9; ++x)
        {
            System.out.println(t1.getColor());
            t1.waitForChange();
        }
        t1.cancel();
    }
}
