import com.project.util.PasswordUtil;
import org.junit.Test;

public class PasswordHashTest {
    @Test
    public void HashingTest() {
        String hash = PasswordUtil.encryptPassword("1234");
        System.out.println("Hash: " + hash);
        System.out.println("Check: " + PasswordUtil.checkPassword("1234", hash));
    }
}
