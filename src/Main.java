import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(Arrays.asList(
                "календарь", "мольберт", "солома", "рыба",
                "глаза", "морковь", "зима", "лето", "Китай",
                "сок", "рыба", "ПК", "лист", "таблица", "волонтер",
                "лето", "таблица", "Испания", "глаза", "ПК"
        ));
        System.out.println("Массив из слов:\n" + words);

        Set<String> unigueWords = new HashSet<>(words);
        System.out.println("\nМассив уникальных слов из первого массива:\n" + unigueWords);


        Map<String, Integer> numberOfRepetitions = new HashMap<>();

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            int number = numberOfRepetitions.getOrDefault(word, 0) + 1;
            numberOfRepetitions.put(word, number);
        }

        System.out.println("\nКоличество повторений для каждого слова:\n" + numberOfRepetitions);

        Phonebook ph = new Phonebook();
        ph.add("Петров", 89631563347l);
        ph.add("Сидоров", 80515545547l);
        ph.add("Остапов", 87458963245l);
        ph.add("Иванов", 89541234567l);
        ph.add("Иванов", 81111234447l);

        ph.get("Петров");
        ph.get("Иванов");

    }
}
