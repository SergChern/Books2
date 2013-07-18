package books2.action;

import com.opensymphony.xwork2.ActionSupport;

public abstract class ActionBase extends ActionSupport {

    private boolean checkAuth;

    public boolean isCheckAuth() {
        return checkAuth;
    }

    public void setCheckAuth(boolean checkAuth) {
        this.checkAuth = checkAuth;
    }

}