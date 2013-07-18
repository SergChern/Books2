package books2.server;

import java.util.Enumeration;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static boolean isName(HttpServletRequest request, String summitName) {
        Enumeration fields = request.getParameterNames();

        if (fields.hasMoreElements()) {
            while (fields.hasMoreElements()) {
                String field = (String) fields.nextElement();
                if (field.equals(summitName))
                    return true;
            }
        }
        return false;
    }

    public static String getValueName(HttpServletRequest request, String summitName) {
        String valueName = null;
        Enumeration fields = request.getParameterNames();

        if (fields.hasMoreElements()) {
            while (fields.hasMoreElements()) {
                String field = (String) fields.nextElement();
                if (field.equals(summitName))
                    return request.getParameter(field);
            }
        }
        return null;
    }
    
    public static boolean isEmptySet ( Set value ) { 
        return value == null || value.size()==0; 
  } 
}
