import java.util.Arrays;
import java.util.Comparator;

// enums, class Person
enum Sex{
    M,F
}
enum Size{
    XS,S,M,L,XL
}
enum Country{
    PL("Polska"),NL("Nederland"),DE("Deutschland");

    public final String countryName;

    Country(String countryName){
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return countryName;
    }
}

class Person{
    String name;
    Sex sex;
    Size size;
    Country country;

    public Person(String name, Sex sex, Size size, Country country) {
        this.name = name;
        this.sex = sex;
        this.size = size;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + "(" + sex + ", " + size + ", " + country + ")";
    }
}


public class EnumsLambdas {

    // printArray static function
    static <T> void printArray(String message, T[] people){
        System.out.println(message);
        for (T person: people) {
            System.out.println(person);
        }
    }


    public static void main(String[] args) {
        Person[] people = {
            new Person("Max",  Sex.M, Size.XL, Country.NL),
            new Person("Jan",  Sex.M, Size.S,  Country.PL),
            new Person("Eva",  Sex.F, Size.XS, Country.NL),
            new Person("Lina", Sex.F, Size.L,  Country.DE),
            new Person("Mila", Sex.F, Size.S,  Country.DE),
            new Person("Ola",  Sex.F, Size.M,  Country.PL),
        };

        Comparator<Person> sexThenSize = (Person compWith, Person compTo) ->{
            int comparison = compTo.sex.compareTo(compWith.sex);
            if (comparison == 0) comparison = compWith.size.compareTo(compTo.size);
            return comparison;
        };
        Arrays.sort(people, sexThenSize);
        printArray("\nPeople by sex and then size", people);

        Comparator<Person> sizeThenName = Comparator.comparing((Person compWith) -> compWith.size).thenComparing(compWith -> compWith.name);

        Arrays.sort(people, sizeThenName);
        printArray("\nPeople by size and then name", people);

        Country[] countries = Country.values();
        Comparator<Country> countriesByName = Comparator.comparing((Country compWith) -> compWith.countryName);
        Arrays.sort(countries, countriesByName);
        printArray("\nCountries by name", countries);
    }
}
