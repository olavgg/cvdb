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
            dbPerson.departments = person.departments;
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

        Person janni = new Person("Janni Somme", "Tech. Support", 23);
        janni.departments = List.of("Support");
        addPerson(janni);

        Person tom = new Person("Tom Bone", "Java developer", 44);
        tom.departments = List.of("Engineering");
        addPerson(tom);

        Person james = new Person("James Eastwood", "Planning Manager", 39);
        james.departments = List.of("Engineering", "Management", "Support");
        addPerson(james);

        Person vincent = new Person("Vincent Cassels", "Lead Engineer", 51);
        vincent.departments = List.of("Engineering", "Management");
        addPerson(vincent);

        Person bruce = new Person("Bruce Wayne", "Account Supervisor", 27);
        bruce.departments = List.of("Finance");
        addPerson(bruce);
    }

}
