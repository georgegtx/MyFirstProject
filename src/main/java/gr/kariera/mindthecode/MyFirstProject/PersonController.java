package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @PutMapping("/persons/{id}")
    public Person update(@PathVariable Integer id, @RequestBody Person person) {
        if (!id.equals(person.getId())) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "id in path does not patch id in body");
        }
        return repo.save(person);
    }

    @PostMapping("/persons")
    public Person newPerson(@RequestBody Person person) {
        return repo.save(person);
    }

    @GetMapping("/persons/{id}")
    public Person one(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow();
    }

    @GetMapping("/persons")
    public List<Person> all() {
        return repo.findAll();
    }

    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable Integer id) {
        Person match = repo.findById(id)
                .orElseThrow();
        repo.delete(match);
    }
}
