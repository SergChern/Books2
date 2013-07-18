package books2.action;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import books2.server.Author;
import books2.services.ZZZService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.Validateable;

public class CreateAuthorAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    private static Long id = null;
    Author author = new Author();

    public Long getId() {
        return author.getId();
    }

    public void setId(Long id) {
        author.setId(id);
    }

    public String getName() {
        return author.getName();
    }

    public void setName(String name) {
        author.setName(name);
    }

    public String getSurname() {
        return author.getSurname();
    }

    public void setSurname(String surname) {
        author.setSurname(surname);
    }

    public Set getBooks() {
        return author.getBooks();
    }

    public void setBooks(Set books) {
        author.setBooks(books);
    }

    public String execute() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        servletContext.setAttribute("snakes",
                ZZZService.getSnake("create_the_author", getText("create_the_author")));
        servletContext.setAttribute("author", author);
        return SUCCESS;
    }

    public String onSubmit() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        if (!check()) {
            servletContext.setAttribute("author", author);
            return INPUT;
        }

        ZZZService.getZZZService().saveOrUpdate(author);

        servletContext.setAttribute("snakes",
                ZZZService.getSnake("list_of_authors", getText("list_of_authors")));
        id = null;
        servletContext.setAttribute("sAjaxSource", "");
        List<Author> list_Author = ZZZService.getZZZService().getAuthors();

        servletContext.setAttribute("authors", list_Author);
        return SUCCESS;
    }

    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String param = request.getParameter("id");

        if (param != null && !param.isEmpty()) {
            if (id == null) {
                id = new Long(param);
                author = ZZZService.getZZZService().getAuthor(id);
            }
        }
        if (id != null)
            check();
    }

    public boolean check() {
       
        if (StringUtils.isEmpty(author.getSurname())) {
            addFieldError("surname", getText("surname_is_required"));
            return false;
        }
        if (StringUtils.isEmpty(author.getName())) {
            addFieldError("name", getText("name_is_required"));
            return false;
        }
       
        return true;
    }
}