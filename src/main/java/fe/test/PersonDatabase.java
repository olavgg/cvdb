package fe.test;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class PersonDatabase {

    public final
    ConcurrentHashMap<Long, Person> persons = new ConcurrentHashMap<>();

    void addPerson(Person person){
        long maxValue = 0;
        for(Long value : persons.keySet()){
            if(value > maxValue){
                maxValue = value;
            }
        }
        person.id = maxValue + 1;
        persons.put(person.id, person);
    }

    void updatePerson(Person person){
        Person dbPerson = persons.get(person.id);
        if(dbPerson != null){
            dbPerson.name = person.name;
            dbPerson.age = person.age;
            dbPerson.title = person.title;
            dbPerson.buzzWords = person.buzzWords;
        }
    }

    void deletePerson(Person person){
        deletePerson(person.id);
    }

    void deletePerson(long id){
        Person dbPerson = persons.get(id);
        if(dbPerson != null){
            persons.remove(id);
        }
    }

    @PostConstruct
    public void init() {

        Person janni = new Person("Janni Somme", "Backend developer", 23);
        janni.buzzWords = List.of("Spring", "Java", "Linux");
        addPerson(janni);

        Person tom = new Person("Tom Bone", "Java developer", 44);
        tom.buzzWords = List.of("J2EE", "Java", "Swing", "Maven", "Websphere");
        addPerson(tom);

        Person james = new Person("James Eastwood", "Full stack developer", 39);
        james.buzzWords = List.of(
                "HTML", "CSS", "C#", "Windows", ".Net Core",
                "MS SQL Server", "Visual Basic", "React");
        addPerson(james);

        Person vincent = new Person("Vincent Cassels", "Rock Star", 31);
        vincent.buzzWords = List.of(
                "Preact", "Functional Programming", "AWS", "Puppy Linux",
                "MongoDB", "Machine Learning", "Rust");
        addPerson(vincent);
    }

}
