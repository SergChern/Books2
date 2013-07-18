package books2.action;

import org.apache.struts2.ServletActionContext;
import books2.server.User;
import books2.services.ZZZService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Validateable;

public class LoginAction extends ActionSupport implements Validateable {
    private static final long serialVersionUID = 1L;
    private String j_username;
    private String j_password;
    private User user;

    public String getJ_username() {
        return j_username == null ? "" : j_username.trim();
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    public String getJ_password() {
        return j_password == null ? "" : j_password.trim();
    }

    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

    public String input() throws Exception {
        return SUCCESS;
    }

    public String onSubmit() throws Exception {
        AuthController.setUser(this.user);
        ServletActionContext.getServletContext().setAttribute("login0", j_username);
        ZZZService.clearSnakes();
        ServletActionContext.getServletContext().setAttribute("snakes", ZZZService.getSnake("_", getText("hello")));
        return SUCCESS;
    }
  
    public void validate() {
        if (!this.getFieldErrors().isEmpty())
            return;
        this.user = new User(this.getJ_username(), this.getJ_password());
        if (("user".equalsIgnoreCase(this.user.getUsername())
              && "u000".equalsIgnoreCase(this.user.getPassword())) ||
            ("admin".equalsIgnoreCase(this.user.getUsername())
                    && "a999".equalsIgnoreCase(this.user.getPassword()))) ;        
              else
            this.addFieldError("invalidLoginPassw", "Invalid login/password");

    }
    
}
