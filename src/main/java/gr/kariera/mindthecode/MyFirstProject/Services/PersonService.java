package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Person;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface  PersonService {
    public abstract Person createOrUpdatePerson(Integer id, Person person) throws Exception;
    public abstract void deletePerson(Integer id);
    public abstract Page<Person> getPersons(
            String lastName,
            int page,
            int size,
            String sort
    );

    public abstract Person getById(Integer id);
}
