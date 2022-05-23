package sendmessage;

import java.util.ArrayList;

/**
 *
 * @author sye
 */
public class ArrayListUser {
    Registrasi regis = new Registrasi();
    
    public void array() {
        ArrayList<String> user = new ArrayList<String>();
        user.add(regis.Username);
    }
}
