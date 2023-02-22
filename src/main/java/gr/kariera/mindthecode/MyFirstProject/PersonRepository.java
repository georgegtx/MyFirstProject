package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersonRepository extends JpaRepository<Person, Integer> {
}
