package hibJpaApp.services;

import java.util.List;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hibJpaApp.models.Book;
import hibJpaApp.models.Person;
import hibJpaApp.repositories.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> index(int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> index() {
        return bookRepository.findAll();
    }

    public List<Book> index(Boolean sortedByYear) {
        if (sortedByYear == true) {
            return bookRepository.findAll(Sort.by(Sort.Order.asc("year")));
        } else {
            return bookRepository.findAll();
        }
    }

    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);

    }

    @Transactional
    public void bookUnassign(int id) throws Exception {
        Book unassignedBook = bookRepository.findById(id).orElse(null);
        if (unassignedBook == null) {
            throw new Exception("Book with this identifyer" + id + "does not exist");
        }
        unassignedBook.setOwner(null);
        unassignedBook.setHiringDate(null);
        bookRepository.save(unassignedBook);
    }

    @Transactional
    public void bookAssignTo(int id, Person owner) throws Exception {
        Book assignedBook = bookRepository.findById(id).orElse(null);
        if (assignedBook == null) {
            throw new Exception("Book with this identifyer" + id + "does not exist");
        }
        assignedBook.setOwner(owner);
        Date hiringDate = new Date();
        assignedBook.setHiringDate(hiringDate);
        bookRepository.save(assignedBook);

    }

    @Transactional
    public void delete(int id) throws Exception {

        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Book> searchByTitle(String query) {

        return bookRepository.findByTitleContains(query);

    }

    public void setHiringDate() {
        // TODO Auto-generated method stub

    }

}
