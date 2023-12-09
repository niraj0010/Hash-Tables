import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello There");
        String fileName = getUserInput("Enter the name of the text file: ");
        String[] names = readNamesFromFile(fileName);
        if (names != null) {
            HashThat myTable;

            // Hash table size 200
            myTable = new HashThat(200, names);
            System.out.println("Hash Table Size 200:");
            myTable.showTable();

            // Hash table size 400
            myTable.reHash(400, names);
            System.out.println("Hash Table Size 400:");
            myTable.showTable();

            // Hash table size 700
            myTable.reHash(700, names);
            System.out.println("Hash Table Size 700:");
            myTable.showTable();
        }

        System.out.println("Bye-bye! See you in Operating Systems!");
    }

    private static String[] readNamesFromFile(String fileName) {
        List<String> namesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/" + fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                namesList.add(line);
            }
            return namesList.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
