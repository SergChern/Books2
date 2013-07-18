package books2.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import books2.action.ActionBase;

public class AuthenticationInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Object action = actionInvocation.getAction();
        if (action != null && (action instanceof ActionBase) && ((ActionBase) action).isCheckAuth()) {
            if (AuthController.getUser() == null)
                return "redirectToLogin";
        }
        return actionInvocation.invoke();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }
}