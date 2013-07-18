package books2.action;

import com.opensymphony.xwork2.ActionContext;

import books2.server.User;

public class AuthController {

    private static final String SESSION_KEY = "authUser";

    public static User getUser() {
        return (User) ActionContext.getContext().getSession().get(SESSION_KEY);
    }

    public static void setUser(User user) {
        ActionContext.getContext().getSession().put(SESSION_KEY, user);
    }
}