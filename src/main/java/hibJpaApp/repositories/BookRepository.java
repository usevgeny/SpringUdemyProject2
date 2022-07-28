package hibJpaApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hibJpaApp.models.Book;
import hibJpaApp.models.Person;

public interface BookRepository extends JpaRepository<Book, Integer>{
    
    
    
    public List<Book> findByOwner(Person owner);

    

    public List<Book> findByTitleStartingWith(String search);
    
    public List<Book> findByTitleContains(String search);

}
