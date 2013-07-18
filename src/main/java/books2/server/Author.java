package books2.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.GenericGenerator;

@Entity
//@Table(name = "author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String surname;
    private String name;
    Set<Book> books = new HashSet<Book>();

    public Author() {
    }
/*
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(name = "surname", length = 30, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

 //   @Column(name = "name", length = 15, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_book", joinColumns = {@JoinColumn(name = "fk_author_id", nullable = false, updatable = false, referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "fk_book_id", nullable = false, updatable = true, referencedColumnName = "id")})
    */
    /*
     * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
     * @JoinTable(name = "author_book", joinColumns = {
     * @JoinColumn(name = "fk_author_id", nullable = false, updatable = false)
     * }, inverseJoinColumns = { @JoinColumn(name = "fk_book_id", nullable =
     * false, updatable = false) }) <set name="books" inverse="true"
     * lazy="true"> <many-to-many class="Book" column="" /> </set>
     */
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String toString() {
        return getName() + " " + getSurname();
    }
}
