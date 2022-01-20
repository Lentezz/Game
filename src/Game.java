import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Game {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random();
        List<String> arr = new ArrayList<>();
        File file = new File("src/Files/five_words_nouns.txt");
        FileReader fr = new FileReader(file);

        try (BufferedReader br = new BufferedReader(fr))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List<String> list = List.of("Hello", "Kurva", "Matka");
        Scanner scanner = new Scanner(System.in);
        String randomWord = arr.get(random.nextInt(arr.size() - 1)).toLowerCase(Locale.ROOT);
        System.out.println(randomWord);
        System.out.println("----------------");
        for(int i = 0; i < 6; i++){
            String str = scanner.nextLine().toLowerCase(Locale.ROOT);

            if(str.equals(randomWord)){
                System.out.println("You win!");
                break;
            }

            String[] scannedStringArray = str.split("");
            String[] randomWordArray = randomWord.split("");

            for(int k = 0; k < 5; k++){
                if(scannedStringArray[k].equals(randomWordArray[k])){
                    continue;
                }
                for(int j = 0; j < 5; j++){
                    if(scannedStringArray[k].equals(randomWordArray[j])){
                        scannedStringArray[k] = "|" + scannedStringArray[k] + "|";
                    }
                }
            }
            for (int k = 0; k < 5; k++){
                if (scannedStringArray[k].equals(randomWordArray[k])){
                    scannedStringArray[k] = scannedStringArray[k].toUpperCase(Locale.ROOT);
                }
            }
            Arrays.stream(scannedStringArray).forEach(System.out::print);
            System.out.println();
        }
        System.out.println("Лошара, ты проиграл, загаданное слово: " + randomWord.toUpperCase(Locale.ROOT));
    }
}
