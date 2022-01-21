import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public static void main(String[] args) throws FileNotFoundException {

        Random random = new Random();
        List<String> arr = new ArrayList<>();
        File file = new File("src/Files/five_letters_ukr.txt");
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
        Map<String, String> map = arr.stream().collect(Collectors.toMap(x -> x, x -> x));
        Scanner scanner = new Scanner(System.in);
        String randomWord = arr.get(random.nextInt(arr.size() - 1)).toLowerCase(Locale.ROOT);
        System.out.println(randomWord);
        System.out.println("----------------");
        for(int i = 0; i < 6; i++){
            String str = scanner.nextLine().toLowerCase(Locale.ROOT);
            if(map.get(str) == null){
                System.out.println("Слова немає в словнику");
                i--;
                continue;
            }
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
            if(i == 5){
                System.out.println("Лошара, ты проиграл, загаданное слово: " + randomWord.toUpperCase(Locale.ROOT));
            }
        }
    }
}
