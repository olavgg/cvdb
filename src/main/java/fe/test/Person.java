package fe.test;

import io.micronaut.core.annotation.Introspected;

import java.util.ArrayList;
import java.util.List;

@Introspected
public class Person {

    public long id;

    public String name;

    public String title;

    public Integer age;

    public List<String> buzzWords = new ArrayList<>();

    public Person(long id) {
        this.id = id;
    }

    public Person(String name, String title, int age) {
        this.name = name;
        this.title = title;
        this.age = age;
    }
}
