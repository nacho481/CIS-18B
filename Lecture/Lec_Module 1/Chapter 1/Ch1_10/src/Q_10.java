public class Q_10
{
    public static void main(String [] args)
    {
        double metric_inch, metric_meter;
        int sum = 0;

        for(metric_inch = 1; metric_inch <= 144; metric_inch++)
        {
            metric_meter = metric_inch/39.37;
            System.out.println(metric_inch + " inch is equal to " + metric_meter + "meter.");
            sum++;

            if(sum == 12)
            {
                System.out.println();
                sum = 0;
            }
        }
    }
}
