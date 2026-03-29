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

        String bestName = "";
        String bestCategory = "";
        double bestScore = -1;

        System.out.println();
        System.out.println("Ranked results:");

        while ((line = inputFile.readLine()) != null) {
            if (line.trim().isEmpty() || line.startsWith("Name")) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 3) {
                continue;
            }

            String name = parts[0].trim();
            String category = parts[1].trim();
            double value = Double.parseDouble(parts[2].trim());

            double score = value * 2;

            if (category.equalsIgnoreCase("Exercise")) {
                score = score + 1;
            } else if (category.equalsIgnoreCase("NaturalCompound")) {
                score = score + 2;
            }

            System.out.println(name + " | " + category + " | impact score: " + score);

            if (score > bestScore) {
                bestScore = score;
                bestName = name;
                bestCategory = category;
            }
        }

        inputFile.close();
        keyboard.close();

        System.out.println();
        System.out.println("Top-ranked item: " + bestName + " (" + bestCategory + ")");
        System.out.println("Highest impact score: " + bestScore);
    }
}
