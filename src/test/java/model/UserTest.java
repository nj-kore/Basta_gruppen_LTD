/**
 * @author Gustav Häger
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void getProfileImagePath() throws Exception {
        User user = new User(1,"","","","",StatusType.Available,false);
        assertTrue(user.getStatusImagePath().toString().equals("pics/statusGreen.png"));
        user.setStatus(StatusType.Busy);
        assertTrue(user.getStatusImagePath().equals("pics/statusOrange.png"));
        user.setStatus(StatusType.Do_not_disturb);
        assertTrue(user.getStatusImagePath().equals("pics/statusRed.png"));
    }

    @Test
    public void equalsTest() throws Exception {
        User user = new User(1,"","","","",StatusType.Available,false);
        User user2 = new User(1,"","","","",StatusType.Available,false);
        User user3 = new User(2,"","","","",StatusType.Available,false);
        User user4 = new User(1,"","","","",null,false);
        user.setEmail("");
        user2.setEmail("");
        //assertEquals is not used since it is in fact equals that needs to be tested
        assertTrue(user.equals(user2));
        assertTrue(user2.equals(user));
        assertFalse(user.equals(user3));
        assertFalse(user3.equals(user));
        assertFalse(user.equals(user4));
        assertFalse(user4.equals(user));
    }

    @Test
    public void hashCodeTest() throws Exception {
        User user = new User(1,"","","","",StatusType.Available,false);
        User user2 = new User(1,"","","","",StatusType.Available,false);
        User user3 = new User(4,"","","","",StatusType.Available,false);
        assertEquals(user.hashCode(), user2.hashCode());
        assertNotEquals(user.hashCode(), user3.hashCode());
    }
}