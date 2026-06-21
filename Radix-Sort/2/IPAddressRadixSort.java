import java.util.Arrays;

/**
 * PROBLEM: IP Address Radix Sort
 * * Sort an array of standard IPv4 dot-decimal text strings alphabetically without using comparison operators.
 * * Strategy: Base-256 Network Octet Traversal
 * Parse each IP string into a standard array of 4 integer components (octets), each bounded within [0, 255]. 
 * Run an LSD radix sort pass on each octet column from right to left (processing octet 4, then 3, then 2, 
 * and finally octet 1). Use a fixed bucket size of 256 for each pass to preserve standard network sorting orders.
 */
public class IPAddressRadixSort {
    public static void sortIPs(String[] arr) {
        int n = arr.length;
        int[][] parsedIPs = new int[n][4];

        for (int i = 0; i < n; i++) {
            String[] tokens = arr[i].split("\\.");
            for (int j = 0; j < 4; j++) {
                parsedIPs[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        String[] output = new String[n];
        int[][] auxParsed = new int[n][4];

        // LSD Loop: Process octets backwards from column index 3 down to 0
        for (int octetIndex = 3; octetIndex >= 0; octetIndex--) {
            int[] count = new int[256];

            for (int i = 0; i < n; i++) {
                count[parsedIPs[i][octetIndex]]++;
            }

            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int octetVal = parsedIPs[i][octetIndex];
                int targetIndex = count[octetVal] - 1;
                
                output[targetIndex] = arr[i];
                auxParsed[targetIndex] = parsedIPs[i];
                count[octetVal]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
            for (int i = 0; i < n; i++) {
                parsedIPs[i] = auxParsed[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] ips = {"192.168.1.5", "10.0.0.1", "192.168.0.100", "10.0.0.2", "127.0.0.1"};
        sortIPs(ips);
        System.out.println("Network Octet Radix Sorted:\n" + Arrays.toString(ips));
    }
}