import java.net.InetAddress;
import java.util.Scanner;

public class PingService {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter host (domain or IP): ");
            String host = sc.nextLine();

            InetAddress inet = InetAddress.getByName(host);

            System.out.println("Pinging " + host + " ...");

            for (int i = 1; i <= 4; i++) {
                long start = System.currentTimeMillis();

                boolean reachable = inet.isReachable(3000);  // timeout 3 seconds

                long end = System.currentTimeMillis();
                long rtt = end - start;

                if (reachable) {
                    System.out.println("Reply from " + inet.getHostAddress() +
                            ": time=" + rtt + "ms");
                } else {
                    System.out.println("Request timed out.");
                }

                Thread.sleep(1000);  // wait 1 sec between pings
            }

        } catch (Exception e) {
            System.out.println("Error: Unable to ping host.");
        }
    }
}

