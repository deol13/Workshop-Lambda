package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.data.DataStorageImpl;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = s -> s.getFirstName().equals("Erik");
        List<Person> findManyResult = instant.findMany(filter);

        System.out.println(findManyResult);

        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = s -> s.getGender() == Gender.FEMALE ;
        List<Person> findManyResult = instant.findMany(filter);

        System.out.println(findManyResult);

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = s -> s.getBirthDate().isAfter(LocalDate.of(1999, 12, 31));
        List<Person> findManyResult = instant.findMany(filter);

        System.out.println(findManyResult);

        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getId() == 123;
        Person person = instant.findOne(filter);

        System.out.println(person);

        System.out.println("----------------------");
    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getId() == 456;
        Function<Person, String> convert = p -> "Name: "+ p.getFirstName() + " " + p.getLastName() + " born "+ p.getBirthDate();
        String stringOfPerson = instant.findOneAndMapToString(filter, convert);

        System.out.println(stringOfPerson);

        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getGender() == Gender.MALE && p.getFirstName().startsWith("E");
        Function<Person, String> convert = p -> "Name: "+ p.getFirstName() + " " + p.getLastName() + " Gender " + p.getGender() + " born "+ p.getBirthDate();
        List<String> listOfStrings = instant.findManyAndMapEachToString(filter, convert);

        System.out.println(listOfStrings);

        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getBirthDate().isAfter(LocalDate.now().minusYears(9));
        Function<Person, String> convert = p -> p.getFirstName() + " " + p.getLastName() + " " + (LocalDate.now().getYear() - p.getBirthDate().getYear()) + " years";
        List<String> listOfStrings = instant.findManyAndMapEachToString(filter, convert);

        System.out.println(listOfStrings);

        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getFirstName().equals("Ulf");
        Consumer<Person> consumer = System.out::println;
        instant.findAndDo(filter, consumer);

        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getLastName().contains(p.getFirstName());
        Consumer<Person> consumer = System.out::println;
        instant.findAndDo(filter, consumer);

        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> {
            StringBuilder rev = new StringBuilder(p.getFirstName());
            rev.reverse();
            return p.getFirstName().equalsIgnoreCase(rev.toString());
        };
        Consumer<Person> consumer = p -> System.out.println(p.getFirstName() + " " + p.getLastName());
        instant.findAndDo(filter, consumer);

        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;
        Predicate<Person> filter = p -> p.getFirstName().startsWith("A");
        Comparator<Person> comp = Comparator.comparing(Person::getBirthDate); //(p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate());
        System.out.println(instant.findAndSort(filter, comp));

        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Predicate<Person> filter = p -> p.getBirthDate().isBefore(LocalDate.of(1950, 1, 1));
        Comparator<Person> comp = Comparator.comparing(Person::getBirthDate).reversed();
        System.out.println(instant.findAndSort(filter, comp));

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        DataStorage instant = DataStorage.INSTANCE;

        Comparator<Person> comp = (p1, p2) -> {
            if(p1.getLastName().compareTo(p2.getLastName()) < 0) return -1;
            else if (p1.getLastName().compareTo(p2.getLastName()) > 0) return 1;

            if(p1.getFirstName().compareTo(p2.getFirstName()) < 0) return -1;
            else if (p1.getFirstName().compareTo(p2.getFirstName()) > 0) return 1;

            if(p1.getBirthDate().isBefore(p2.getBirthDate())) return -1;
            if(p1.getBirthDate().isAfter(p2.getBirthDate())) return 1;
            return 0;
        };
        System.out.println(instant.findAndSort(comp));

        System.out.println("----------------------");
    }

}