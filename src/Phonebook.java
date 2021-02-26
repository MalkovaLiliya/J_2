import java.util.*;
public class Phonebook {
    private  Map<String, List<Long>> phonebook  = new HashMap<>();

    void add(String surname, long phone){
        List<Long> phNumbers = phonebook.getOrDefault(surname, new ArrayList<>());
        phNumbers.add(phone);
        phonebook.getOrDefault(surname, phNumbers);
        phonebook.put(surname, phNumbers);
    }

    void get(String surname){
        System.out.println(surname + " " + phonebook.getOrDefault(surname, new ArrayList<>()));

    }
}
