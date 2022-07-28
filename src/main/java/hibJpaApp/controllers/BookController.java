package hibJpaApp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hibJpaApp.models.Book;
import hibJpaApp.models.Person;
import hibJpaApp.services.BookService;
import hibJpaApp.services.PersonService;


@Controller
@RequestMapping("/books")
public class BookController {

	private final  BookService bookService;
	private final PersonService personService;

	
	
	@Autowired
	private BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
	public String index(Model model, HttpServletRequest request) {
	//@RequestParam("page") int page, @RequestParam("books_per_page") int booksPerPageage) {
		try {
            int page = Integer.parseInt(request.getParameter("page"));
            int booksPerPageage = Integer.parseInt(request.getParameter("books_per_page"));
            model.addAttribute("books", bookService.index(page, booksPerPageage));
            return "/books/index";
        } catch (Exception e) {
            Boolean sortedByYear;
            
            sortedByYear = request.getParameter("sorted_by_year")==null?false:Boolean.parseBoolean(request.getParameter("sorted_by_year"));
            
            if (null!= sortedByYear)
                model.addAttribute("books", bookService.index(sortedByYear));
            else
                model.addAttribute("books", bookService.index());
            return "/books/index";
        }
		
	}
    

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {

		if (bookService.show(id) == null) {
			return "redirect:/books";
		}

		if (bookService.show(id).getOwner()!= null) {

		

			model.addAttribute("subscriber", bookService.show(id).getOwner());
		}
		
		
		model.addAttribute("book", bookService.show(id));
		model.addAttribute("subscribers", personService.index());
		
		
		return "/books/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {

		model.addAttribute("book", bookService.show(id));

		return "/books/edit";
	}

	
	
	@GetMapping("/new")
	public String newBook(@ModelAttribute("book")Book book) {

		return "/books/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "redirect:/books/new";
		}
		System.out.println(book.getYear());
		bookService.save(book);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/update")
	public String update(@PathVariable("id") int id, @ModelAttribute("book") Book updatedBook) {

		
	    bookService.update(id, updatedBook);

		return "redirect:/books/{id}";
	}
	
	
	@PatchMapping("/{id}/unassign")
	public String unassign(@PathVariable("id") int id) {
	    
	    try {
            bookService.bookUnassign(id);
            return "redirect:/books/{id}";
        } catch (Exception e) {
            
            e.printStackTrace();
            return "/books/index";
        }

		
	}
	
	@PatchMapping("/{id}/assign")
	public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		
		if(bookService.show(id)==null) {
			return "redirect:/books";
		}
		if(personService.show(person.getId())==null) {
			return "redirect:/books";
		}
		
		try {
            bookService.bookAssignTo(id, person);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "redirect:/books";
        }
		

		return "redirect:/books/{id}";
	}
	
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		try {
		    bookService.delete(id);
		    return "redirect:/books";
        } catch (Exception e) {
            return "redirect:/books";
        }
	   
	}
	
	

	
	
	@GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("books", bookService.searchByTitle(query));
        return "books/search";
    }

}
