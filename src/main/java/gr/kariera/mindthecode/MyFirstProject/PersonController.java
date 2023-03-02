package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Person> all(
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {

        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("lastName").ascending() :
                        Sort.by("lastName").descending());

        Page<Person> res;
        if (lastName == null) {
            res = repo.findAll(paging);
        } else {
            res = repo.findByLastNameContainingIgnoreCase(lastName, paging);
        }

        return res;
    }

    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable Integer id) {
        Person match = repo.findById(id)
                .orElseThrow();
        repo.delete(match);
    }
}
