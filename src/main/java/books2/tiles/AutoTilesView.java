package books2.tiles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

import books2.action.AuthController;
import books2.server.Snake;

public class AutoTilesView extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private static List<String> snakes = new ArrayList();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // Register a PreResultListener and implement the beforeReslut method
        invocation.addPreResultListener(new PreResultListener() {
            @Override
            public void beforeResult(ActionInvocation invocation, String resultCode) {

                Object o = invocation.getAction();
                try {
                    ServletContext servletContext = ServletActionContext.getServletContext();
                    String snake = (String) servletContext.getAttribute("snake");
                    System.out.println("1)"+snake+"2)"+getSnake(snake).toString());
                    if (snake != null)
                        servletContext.setAttribute("snakes", servletContext.getAttribute("snakes")+"/"+snake); // getSnake(snake)
                    
                    servletContext.setAttribute("login0", AuthController.getUser().getUsername());
                } catch (Exception e) {
                    invocation.setResultCode("error");
                }
            }
        });

        // Invocation Continue
        return invocation.invoke();
    }

    public List<Snake> getSnake(String leksem) {
        if (snakes.size() == 1)
            snakes.add("hello");
        int i = snakes.indexOf(leksem);
        if (i == -1)
            snakes.add(leksem);
        else
            while (i + 1 < snakes.size())
                snakes.remove(i + 1);

        List<Snake> snakeList = new ArrayList();
        for (i = 0; i < snakes.size(); i++) {
            snakeList.add(new Snake((String) snakes.get(i)));
        }
        return snakeList;
    }
}
