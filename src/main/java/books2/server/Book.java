package books2.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.annotations.GenericGenerator;

import books2.services.ZZZService;

@Entity
//@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String brief_Description;
    private String year_Of_Publication;
    Set<Author> authors = new HashSet<Author>();

    public Book() {
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

//    @Column(name = "name", length = 120, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Column(name = "brief_description", length = 255, nullable = false)
    public String getBrief_Description() {
        return brief_Description;
    }

    public void setBrief_Description(String brief_Description) {
        this.brief_Description = brief_Description;
    }

//    @Column(name = "year_of_publication", length = 4, nullable = false)
    public String getYear_Of_Publication() {
        return year_Of_Publication;
    }

    public void setYear_Of_Publication(String year_Of_Publication) {
        this.year_Of_Publication = year_Of_Publication;
    }
/*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_book", joinColumns = {@JoinColumn(name = "fk_book_id", nullable = false, updatable = true, referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "fk_author_id", nullable = false, updatable = false, referencedColumnName = "id")})
    */
    /*
     * <set name="authors" inverse="false" lazy="false"> <many-to-many
     * class="Author" /> </set>
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors=authors;
    }
}
