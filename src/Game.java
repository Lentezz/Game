import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public static void main(String[] args) throws FileNotFoundException {

        Random random = new Random(); //енератор рандомных чисел
        List<String> arr = new ArrayList<>(); //список
        File file = new File("src/Files/five_letters_ukr.txt"); // создание объекта "Файл" из текстово файла
        FileReader fr = new FileReader(file); // читалка для файла

        try (BufferedReader br = new BufferedReader(fr)) // пробуем читать файл(если файла нет, отлавливаем ексепшн)
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) { //читаем по 1-й строке
                arr.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = arr.stream().collect(Collectors.toMap(x -> x, x -> x)); //создаем стримом мапу из списка, где ключ это слово, а значение то же самое слово из ключа
        Scanner scanner = new Scanner(System.in);
        String randomWord = arr.get(random.nextInt(arr.size() - 1)).toLowerCase(Locale.ROOT); //выбор рандомного слова и переводим в нижний регистр

        for(int i = 0; i < 6; i++) { // 6 попыток
            String str = scanner.nextLine().toLowerCase(Locale.ROOT); // сканируем слово и переводив в нижний регистр
            if (map.get(str) == null) { // если введенное слово не из словаря
                System.out.println("Слова немає в словнику");
                i--;
            }
            else if (str.equals(randomWord)) { //если введенное слово совпалоо с рандомным:
                System.out.println("You win!");
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
                    System.out.println("Лошара, ты проиграл, загаданное слово: " + randomWord.toUpperCase(Locale.ROOT));
                }
            }
        }
    }
}
