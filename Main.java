import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter the file name: ");
        String filename = keyboard.nextLine();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found.");
            keyboard.close();
            return;
        }

        BufferedReader inputFile = new BufferedReader(new FileReader(file));
        String line;
        String topName = "";
        double topScore = -1;

        while ((line = inputFile.readLine()) != null) {
            if (line.trim().isEmpty() || line.startsWith("Name")) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 2) {
                continue;
            }

            String name = parts[0].trim();
            double value = Double.parseDouble(parts[1].trim());
            double score = value + 5;

            System.out.println(name + " -> score: " + score);

            if (score > topScore) {
                topScore = score;
                topName = name;
            }
        }

        inputFile.close();
        keyboard.close();

        System.out.println();
        System.out.println("Top-ranked item: " + topName);
        System.out.println("Top score: " + topScore);
    }
}
