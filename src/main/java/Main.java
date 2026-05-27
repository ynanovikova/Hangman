import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int MAX_MISTAKES = 6;
    private static final String[] HANGMAN = {
            "  -----\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  -----\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" ВИСЕЛИЦА");
            System.out.println("1. Новая игра");
            System.out.println("2. Выйти");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();

            if (choice.equals("2")) {
                System.out.println("До свидания!");
                break;
            }

            if (choice.equals("1")) {
                playGame(scanner);
            }
        }
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        ArrayList<String> dictionary = loadDictionary("src/словарь висилица.txt");
        String secretWord = getRandomWord(dictionary);
        ArrayList<Character> guessedLetters = new ArrayList<>();
        ArrayList<Character> wrongLetters = new ArrayList<>();
        StringBuilder display = new StringBuilder();
        int mistakes = 0;

        for (int i = 0; i < secretWord.length(); i++) {
            display.append('_');
        }

        openRandomLetter(secretWord, display, guessedLetters);

        while (mistakes < MAX_MISTAKES && display.indexOf("_") != -1) {
            System.out.println("\n" + HANGMAN[mistakes]);

            System.out.print("Слово: ");
            for (int i = 0; i < display.length(); i++) {
                System.out.print(display.charAt(i) + " ");
            }
            System.out.println("\nОшибки: " + mistakes + "/" + MAX_MISTAKES);

            if (!wrongLetters.isEmpty()) {
                System.out.print("Неверные буквы: ");
                for (int i = 0; i < wrongLetters.size(); i++) {
                    System.out.print(wrongLetters.get(i));
                    if (i < wrongLetters.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() == 0) {
                continue;
            }

            char letter = input.charAt(0);

            if (letter < 'а' || letter > 'я') {
                System.out.println("Нужна русская буква (а-я)");
                continue;
            }

            boolean letterAlreadyUsed = false;
            for (int i = 0; i < guessedLetters.size(); i++) {
                if (guessedLetters.get(i) == letter) {
                    letterAlreadyUsed = true;
                }
            }
            for (int i = 0; i < wrongLetters.size(); i++) {
                if (wrongLetters.get(i) == letter) {
                    letterAlreadyUsed = true;
                }
            }

            if (letterAlreadyUsed) {
                System.out.println("Вы уже вводили эту букву");
                continue;
            }

            boolean letterFound = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    display.setCharAt(i, letter);
                    letterFound = true;
                }
            }

            if (letterFound) {
                guessedLetters.add(letter);
            } else {
                wrongLetters.add(letter);
                mistakes++;
            }
        }

        System.out.println("\n" + HANGMAN[mistakes]);
        System.out.println("\nЗагаданное слово: " + secretWord);

        if (display.indexOf("_") == -1) {
            System.out.println("Результат: ПОБЕДА");
        } else {
            System.out.println("Результат: ПОРАЖЕНИЕ");
        }
    }

    private static void openRandomLetter(String word, StringBuilder display, ArrayList<Character> guessedLetters) {
        Random random = new Random();
        int randomPosition = random.nextInt(word.length());
        char letterToOpen = word.charAt(randomPosition);

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letterToOpen) {
                display.setCharAt(i, letterToOpen);
            }
        }
        guessedLetters.add(letterToOpen);
    }

    private static ArrayList<String> loadDictionary(String path) {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (word.length() >= 3) {
                    words.add(word);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка загрузки словаря");
        }
        return words;
    }

    private static String getRandomWord(ArrayList<String> dictionary) {
        Random random = new Random();
        int randomIndex = random.nextInt(dictionary.size());
        return dictionary.get(randomIndex);
    }
}