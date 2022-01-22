import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class Game {

    public static void main(String[] args) throws IOException {


        Map<String, String> map = readFileToMap("src/Files/five_letters_ukr.txt");
        String randomWord = generateRandom(map);

        Scanner scanner = new Scanner(System.in);
        //String randomWord = map.get(random.nextInt(arr.size() - 1)).toLowerCase(Locale.ROOT); //выбор рандомного слова и переводим в нижний регистр
        System.out.println(randomWord);
        for(int i = 0; i < 6; i++) { // 6 попыток
            System.out.println("Спроба №" + (i + 1));
            String str = scanner.nextLine().toLowerCase(Locale.ROOT); // сканируем слово и переводив в нижний регистр
            if (map.get(str) == null) { // если введенное слово не из словаря
                System.out.println("Слова немає в словнику");
                i--;
            }
            else if (str.equals(randomWord)) { //если введенное слово совпалоо с рандомным:
                System.out.println("Ви перемогли! Слава Україні!");
                break;
            }
            else { // проверяем, совпали ли какие-то буквы
                String[] scannedStringArray = str.split("");  // делаем массив из символов из введенного слова типа ["k", "u", "r", "w", "a"]
                String[] randomWordArray = randomWord.split("");

                for (int k = 0; k < 5; k++) {
                    if (scannedStringArray[k].equals(randomWordArray[k])) { // если буква стоит на своем месте, делаем её большой
                        scannedStringArray[k] = scannedStringArray[k].toUpperCase(Locale.ROOT);
                    }
                    for (int j = 0; j < 5; j++) {
                        if (scannedStringArray[k].equals(randomWordArray[j])) { //если буква есть, но не своем месте, выделяем палками
                            scannedStringArray[k] = "|" + scannedStringArray[k] + "|";
                        }
                    }
                }

                Arrays.stream(scannedStringArray).forEach(System.out::print); // выводим стримом все символы, на самом деле оно печатает каждый символ отдельно без проелов
                System.out.println();

                if (i == 5) {
                    System.out.println("Ви програли, загадане слово: " + randomWord.toUpperCase(Locale.ROOT));
                }
            }
        }
    scanner.close();

    }

    public static String generateRandom(Map<String, String> map){
        Random random = new Random(); //енератор рандомных чисел
        return map.values().stream().collect(Collectors.toList()).get(random.nextInt(map.size()));
    }

    public static Map<String, String> readFileToMap(String path) throws IOException {
        Map<String, String> map = new HashMap<>(); //список
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) // пробуем читать файл(если файла нет, отлавливаем ексепшн)
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) { //читаем по 1-й строке
                map.put(sCurrentLine, sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map; //создаем стримом мапу из списка, где ключ это слово, а значение то же самое слово из ключа
    }

    public String check(String randomWord, String scannedWord){
        return null;
    }
}
