package books2.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import books2.server.Author;
import books2.server.Book;
import books2.server.Utils;
import books2.services.ZZZService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Validateable;

public class CreatingBookAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    private static Long id = null;
    Book book = new Book();

    public Long getId() {
        return book.getId();
    }

    public void setId(Long id) {
        book.setId(id);
    }

    public String getName() {
        return book.getName();
    }

    public void setName(String name) {
        book.setName(name);
    }

    public String getBrief_Description() {
        return book.getBrief_Description();
    }

    public void setBrief_Description(String brief_Description) {
        book.setBrief_Description(brief_Description);
    }

    public String getYear_Of_Publication() {
        return book.getYear_Of_Publication();
    }

    public void setYear_Of_Publication(String year_Of_Publication) {
        book.setYear_Of_Publication(year_Of_Publication);
    }

    public Set getAuthors() {
        return book.getAuthors();
    }

    public void setAuthors(Set authors) {
        Set<Author> inauthors = new HashSet<Author>();
        for (Object obj : authors) {
            inauthors.add(ZZZService.getZZZService().getAuthor(new Long(obj.toString())));
        }
        book.setAuthors(inauthors);
    }

    public String execute() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        if (id == null)
            servletContext.setAttribute("snakes", ZZZService.getSnake("creating_a_of_the_book",
                    getText("creating_a_of_the_book")));
        else
            servletContext.setAttribute("snakes",
                    ZZZService.getSnake("modification_book", getText("modification_book")));

        List aList = ZZZService.getZZZService().getAuthorAll(id);
        servletContext.setAttribute("allAuthors", aList);
        servletContext.setAttribute("book", book);
        return SUCCESS;
    }

    public String onSubmit() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        if (!check()) {
            servletContext.setAttribute("book", book);
            return INPUT;
        }

        ZZZService.getZZZService().saveOrUpdate(book);
        servletContext.setAttribute("snakes",
                ZZZService.getSnake("list_of_books", getText("list_of_books")));
        id = null;
        List<Book> list_Book = ZZZService.getZZZService().getBooks();

        for (Book book : list_Book)
            if (book.getAuthors().isEmpty())
                book.getAuthors().add(new Author());

        servletContext.setAttribute("books", list_Book);

        return SUCCESS;
    }

    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String param = request.getParameter("id");

        if (param != null && !param.isEmpty()) {
            if (id == null) {
                id = new Long(param);
                book = ZZZService.getZZZService().getBook(id);
            }
        }
        if (id != null)
            check();
    }

    public boolean check() {
        
        if (StringUtils.isEmpty(book.getBrief_Description())) {
            addFieldError("brief_Description", getText("brief_Description_is_required"));
            return false;
        }
        if (StringUtils.isEmpty(book.getName())) {
            addFieldError("name", getText("name_is_required"));
            return false;
        }
        if (StringUtils.isEmpty(book.getYear_Of_Publication())) {
            addFieldError("year_Of_Publication", getText("year_Of_Publication_is_required"));
            return false;
        }
        if (Utils.isEmptySet(book.getAuthors())) {
            addFieldError("authors", getText("authors_is_required"));
            return false;
        }
        
        return true;
    }
}