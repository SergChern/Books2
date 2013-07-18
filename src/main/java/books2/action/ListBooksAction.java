package books2.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Validateable;

import books2.server.Author;
import books2.server.Book;
import books2.server.HibernateUtil;
import books2.server.Utils;
import books2.services.ZZZService;

public class ListBooksAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    
    String seekName = null;
    Book book = new Book();
    List<Book> list_Book = new ArrayList<Book>();

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        ServletContext servletContext = ServletActionContext.getServletContext();
        
        if (Utils.isName(request, "name"))
            seekName = Utils.getValueName(request, "name");

        if (seekName != null && !seekName.isEmpty())
            book.setName(seekName);        
        
        String param = request.getParameter("del");

        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            book = ZZZService.getZZZService().getBook(id);
            ZZZService.getZZZService().delete(book);
        }

        servletContext.setAttribute("snakes", ZZZService.getSnake("list_of_books", getText("list_of_books")));      
        
        if (seekName != null && !seekName.isEmpty())
            list_Book = ZZZService.getZZZService().findBooks(seekName);
        else
            list_Book = ZZZService.getZZZService().getBooks();

        for (Book book : list_Book)
            if (book.getAuthors().isEmpty())
                book.getAuthors().add(new Author());

        servletContext.setAttribute("books", list_Book);
        return SUCCESS;
    }

    public String onSubmit() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (Utils.isName(request, "seek"))
            return SUCCESS;
        else
            return null;
    }
    
    public void validate() {
    }
    
}