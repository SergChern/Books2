package books2.action;

import org.apache.struts2.ServletActionContext;

import books2.services.ZZZService;

import com.opensymphony.xwork2.Validateable;

public class HelloAction extends ActionBase implements Validateable {
    private static final long serialVersionUID = 1L;
    
    public String execute() throws Exception {
        ServletActionContext.getServletContext().setAttribute("snakes", ZZZService.getSnake("_", getText("hello")));
        return SUCCESS;
    }
    
    public void validate() {
    }

}