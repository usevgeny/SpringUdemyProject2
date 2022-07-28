package hibJpaApp.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hibJpaApp.models.Book;
import hibJpaApp.models.Person;
import hibJpaApp.repositories.BookRepository;
import hibJpaApp.repositories.PersonRepository;
import hibJpaApp.utils.DateUtils;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        super();
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Person show(int id) {

        return personRepository.findById(id).orElse(null);
    }

    public List<Book> showBooks(int id) {

        Person owner = personRepository.findById(id).orElse(null);
        List<Book> hiredBooks;
        hiredBooks = owner != null ? bookRepository.findByOwner(owner) : new ArrayList<Book>();
        hiredBooks.forEach(hiredBook -> hiredBook.setBookExpired());

        return hiredBooks;
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        try {
            // Date convertedDate =
            // DateUtils.formatStringToDate(updatedPerson.getBirthDate(), "yyyy-MM-dd");

            // updatedPerson.setBirthDate(convertedDate);
            updatedPerson.setId(id);
            personRepository.save(updatedPerson);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

}
