import java.net.InetAddress;
import java.util.Scanner;

public class DNSImplementation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a domain name or IP address: ");
            String input = sc.nextLine();

            // Check if input contains alphabets → domain name
            if (input.matches(".*[a-zA-Z].*")) {

                // Forward Lookup (Domain → IP)
                InetAddress address = InetAddress.getByName(input);
                System.out.println("Hostname : " + address.getHostName());
                System.out.println("IP Address : " + address.getHostAddress());

            } else {

                // Reverse Lookup (IP → Domain)
                InetAddress address = InetAddress.getByName(input);
                System.out.println("IP Address : " + input);
                System.out.println("Hostname : " + address.getHostName());
            }

        } catch (Exception e) {
            System.out.println("Invalid input or DNS lookup failed.");
        }
    }
}

