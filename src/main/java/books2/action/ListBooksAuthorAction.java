package books2.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Validateable;
import books2.server.Author;
import books2.server.Book;
import books2.services.ZZZService;

public class ListBooksAuthorAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    
    Author author = new Author();
    List<Book> list_Books_Author = new ArrayList<Book>();
    
    public String execute() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        servletContext.setAttribute("snakes", ZZZService.getSnake("list_of_books_by_the_author",getText("list_of_books_by_the_author")));
        HttpServletRequest request = ServletActionContext.getRequest();
        String param = request.getParameter("list");
        
        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            list_Books_Author = ZZZService.getZZZService().getBooks(id);
        } else
            list_Books_Author = new ArrayList<Book>();

        servletContext.setAttribute("books", list_Books_Author);
        return SUCCESS;
    }
    
    public void validate() {
    }    
}