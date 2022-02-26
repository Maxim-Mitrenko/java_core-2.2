import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> people = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            people.add(new Person(
                    names.get(random.nextInt(names.size())),
                    surnames.get(random.nextInt(surnames.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)]
            ));
        }
        long underage = people.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        List<String> conscripts = people.stream()
                .filter(person -> person.getSex() == Sex.Man)
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getSurname())
                .collect(Collectors.toList());
        List<Person> employablePeopleWithHighEducation = people.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.Man && person.getAge() < 65) || (person.getSex() == Sex.Woman && person.getAge() < 60))
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(person -> person.getSurname()))
                .collect(Collectors.toList());
    }
}