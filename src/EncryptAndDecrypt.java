import java.util.Scanner;

public class EncryptAndDecrypt {

    public static String encryptCaesar(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) (((c - 'A' + key) % 26) + 'A'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) (((c - 'a' + key) % 26) + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static String decryptCaesar(String text, int key) {
        return encryptCaesar(text, 26 - (key % 26));
    }

    public static String straightPBox(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String compressionPBox(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public static String expansionPBox(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(c).append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key (integer shift): ");
        int key = sc.nextInt();
        sc.nextLine(); 

      
        String encrypted = encryptCaesar(plaintext, key);
        System.out.println("Encrypted (Caesar): " + encrypted);

     
        boolean repeat;
        do {
            int choice;

            while (true) {
                System.out.println("\nChoose P-Box method:");
                System.out.println("1. Straight P-Box");
                System.out.println("2. Compression P-Box");
                System.out.println("3. Expansion P-Box");
                System.out.print("Enter choice (1-3): ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); 
                    if (choice >= 1 && choice <= 3) {
                        break;
                    }
                } else {
                    sc.nextLine(); 
                }
                System.out.println("Invalid choice! Please try again.\n");
            }

            String pboxResult = "";
            switch (choice) {
                case 1: pboxResult = straightPBox(encrypted); break;
                case 2: pboxResult = compressionPBox(encrypted); break;
                case 3: pboxResult = expansionPBox(encrypted); break;
            }

            System.out.println("After P-Box Transformation: " + pboxResult);
        
            System.out.print("\nDo you want to try another P-Box? (1 = Yes, 0 = No): ");
            int again = sc.nextInt();
            sc.nextLine();
            repeat = (again == 1);

        } while (repeat);

        String decrypted = decryptCaesar(encrypted, key);
        System.out.println("\nDecrypted (Caesar): " + decrypted);

        sc.close();
    }
}
