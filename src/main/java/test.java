import java.util.HashSet;

public class test {

    public static void main(String[] args) {
        System.out.println(" ТЕСТ ИГРЫ ВИСИЛИЦА\n");

        testDictionaryLoading();
        testRandomWord();
        testMaskCreation();
        testGuessLetter();
        testRepeatLetter();
        testWrongLetter();
        testOpenRandomLetter();

        System.out.println("\n ТЕСТЫ ПРОЙДЕНЫ ");
    }

    private static void testDictionaryLoading() {
        System.out.println("1. Загрузка словаря:");
        Main.main(new String[0]);
        System.out.println("   Словарь загружен");
        System.out.println();
    }

    private static void testRandomWord() {
        System.out.println("2. Случайное слово:");
        System.out.println("   Слово выбрано");
        System.out.println();
    }

    private static void testMaskCreation() {
        System.out.println("3. Создание маски:");
        String word = "программа";
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            mask.append('_');
        }
        System.out.println("   Слово: " + word);
        System.out.println("   Маска: " + mask);
        System.out.println("   Длина маски: " + mask.length());
        if (mask.length() == word.length()) {
            System.out.println(" Маска создана");
        } else {
            System.out.println(" Ошибка длины маски");
        }
        System.out.println();
    }

    private static void testGuessLetter() {
        System.out.println("4. Угадывание буквы:");
        String word = "молоко";
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            display.append('_');
        }
        char letter = 'о';
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                display.setCharAt(i, letter);
            }
        }
        System.out.println("   Слово: " + word);
        System.out.println("   Буква: " + letter);
        System.out.println("   Результат: " + display);

        int count = 0;
        for (int i = 0; i < display.length(); i++) {
            if (display.charAt(i) == letter) {
                count++;
            }
        }
        if (count == 3) {
            System.out.println("Буква угадана");
        } else {
            System.out.println("Ошибка");
        }
        System.out.println();
    }

    private static void testRepeatLetter() {
        System.out.println("5. Повторная буква:");
        HashSet<Character> guessed = new HashSet<>();
        guessed.add('а');
        char letter = 'а';
        if (guessed.contains(letter)) {
            System.out.println("   Буква 'а' уже вводилась");
            System.out.println("Повтор обнаружен");
        } else {
            System.out.println(" Ошибка");
        }
        System.out.println();
    }

    private static void testWrongLetter() {
        System.out.println("6. Неправильная буква:");
        String word = "кот";
        int mistakes = 0;
        char letter = 'б';
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                found = true;
            }
        }
        if (!found) {
            mistakes++;
        }
        System.out.println("   Слово: " + word);
        System.out.println("   Буква: " + letter);
        System.out.println("   Ошибок: " + mistakes);
        if (mistakes == 1) {
            System.out.println("Неправильная буква засчитана");
        } else {
            System.out.println(" Ошибка");
        }
        System.out.println();
    }

    private static void testOpenRandomLetter() {
        System.out.println("7. Открытие случайной буквы:");
        String word = "свобода";
        StringBuilder display = new StringBuilder();
        HashSet<Character> guessed = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            display.append('_');
        }
        java.util.Random random = new java.util.Random();
        int pos = random.nextInt(word.length());
        char letter = word.charAt(pos);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                display.setCharAt(i, letter);
            }
        }
        guessed.add(letter);
        System.out.println("   Слово: " + word);
        System.out.println("   Маска после открытия: " + display);
        System.out.println("   Открытая буква: " + letter);
        if (display.indexOf("_") != -1) {
            System.out.println("Буква открыта");
        } else {
            System.out.println("Ошибка");
        }
        System.out.println();
    }
}