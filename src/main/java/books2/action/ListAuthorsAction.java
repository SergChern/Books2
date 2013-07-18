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

public class ListAuthorsAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    
    Author author = new Author();
    List<Author> list_Author = new ArrayList<Author>();

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        ServletContext servletContext = ServletActionContext.getServletContext();
        String param = request.getParameter("del");

        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            author = ZZZService.getZZZService().getAuthor(id);

            if (author.getBooks().size() == 0)
                ZZZService.getZZZService().delete(author);
            else {
                for (Book book : author.getBooks()) {
                    if (book.getAuthors().size() == 1)
                        ZZZService.getZZZService().deleteAuthor(author.getId(), book.getId());
                    else
                        ZZZService.getZZZService().deleteAuthor(author.getId(), null);
                }
            }
        }
        servletContext.setAttribute("snakes", ZZZService.getSnake("list_of_authors", getText("list_of_authors")));
        servletContext.setAttribute("sAjaxSource", "");
        list_Author = ZZZService.getZZZService().getAuthors();
        // getServletContext().setAttribute("sAjaxSource",
        // "/list_of_authors.htm");
        // list_Author = new ArrayList();
        
        servletContext.setAttribute("authors", list_Author);
        return SUCCESS;
    }

    public void validate() {
    }

}
