package fe.test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.multipart.MultipartBody;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

@Controller
public class BaseController {

    @Inject
    PersonDatabase personDatabase;

    @Get(
            uri = "/list",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
    )
    public Collection<Person> list(){
        return personDatabase.persons.values();
    }

    @Get(
            uri = "/get/{id}",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_FORM_URLENCODED
            },
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
    )
    public HttpResponse<Person> get(Long id){
        Person p = personDatabase.persons.get(id);
        if(p == null){
            return HttpResponse.notFound();
        }
        return HttpResponse.ok().body(p);
    }

    @Post(
            uri = "/add",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_FORM_URLENCODED
            },
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
    )
    public Person add(Person person){
        personDatabase.addPerson(person);
        return person;
    }

    @Post(
            uri = "/update",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_FORM_URLENCODED
            },
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
    )
    public Person update(Person person){
        personDatabase.updatePerson(person);
        return person;
    }

    @Post(
            uri = "/delete",
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_FORM_URLENCODED
            },
            produces = {MediaType.TEXT_PLAIN}
    )
    public HttpResponse<String> delete(Person person){
        personDatabase.deletePerson(person);
        return HttpResponse.noContent();
    }

    @Post(
            uri = "/delete",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA
            },
            produces = {MediaType.TEXT_PLAIN}
    )
    public HttpResponse<String> delete(@Body Map<String, String> data){
        Person p = new Person(Long.parseLong(data.get("id")));
        return delete(p);
    }

}

