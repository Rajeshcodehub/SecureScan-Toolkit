import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Nmap{
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter an IP address to perform vulnerability check: ");
            String ipAddress = reader.readLine().trim(); // Read user input for the IP address

            String command = "nmap -T4 -A " + ipAddress; // Example Nmap command for aggressive scan and OS detection

            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Read the output of the command
            BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = processReader.readLine()) != null) {
                System.out.println(line); // Output the scan results
            }

            process.waitFor();
            process.destroy();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close(); // Close the BufferedReader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}