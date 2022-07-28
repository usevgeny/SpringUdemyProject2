package hibJpaApp.models;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 2 and 80 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "author should not be empty")
    @Size(min = 2, max = 90, message = "Name should be between 2 and 80 characters")
    @Column(name = "author")
    private String author;

    @Min(1)
    @Column(name = "year")
    private Integer year;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hiringDate")
    private Date hiringDate;

    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "id")
    private Person owner;

    @Transient
    private Boolean bookExpired;
    
    @Transient
    private long locationDuration;
    
    @Transient
    @Value("${application.location.limit}:3")
    private long locationLimit;

    public long getLocationLimit() {
        return locationLimit;
    }

    public void setLocationLimit(long locationLimit) {
        this.locationLimit = locationLimit;
    }

    public Book(int id, String title, String author, Integer year, Person owner, Date hiringDate, Boolean bookExpired, long locationDuration,long locationLimit) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.owner = owner;
        this.hiringDate = hiringDate;
        this.bookExpired = bookExpired;
        this.locationDuration = 0L;
        this.locationLimit = locationLimit;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Boolean getBookExpired() {

        return bookExpired;

    }

    public void setBookExpired() {
        Date today = new Date();
        
        setLocationDuration(getHiringDate() != null? ((today.getTime() - getHiringDate().getTime()) / (1000 * 60 * 60 * 24)):0L);
        setBookExpired(getLocationDuration() > 3 ? true : false);
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + ", owner=" + owner
                + "]\n\n";
    }

    public long getLocationDuration() {
        return locationDuration;
    }

    public void setLocationDuration(long locationDuration) {
        this.locationDuration = locationDuration;
    }

    public void setBookExpired(Boolean bookExpired) {
        this.bookExpired = bookExpired;
    }

}
