package hibJpaApp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import hibJpaApp.models.Person;



public interface PersonRepository extends JpaRepository<Person, Integer>{

}
