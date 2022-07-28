package hibJpaApp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="Person")
public class Person {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 80, message = "Name should be between 2 and 80 characters")
	@Pattern(regexp="[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "your full name should be in this format: FirstName Patronymic LastName)")
	@Column(name="fullName")
	private String fullName;

	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", message = "your birth-date should be in this format: yyyy-MM-dd)")
    @Column(name="birthDate")
    private Date birthDate;
    
    
    
    @OneToMany(mappedBy = "owner")
    private List<Book> books;
    

	public Person() {
		
	}

	public Person(int id, String fullName, Date birthDate) {
		
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
