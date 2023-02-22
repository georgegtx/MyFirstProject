package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
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
}
