import com.project.util.PasswordUtil;
import org.junit.Test;

public class PasswordHashTest {
    @Test
    public void HashingTest() {
        String hash = PasswordUtil.encryptPassword("1234");
        System.out.println("Hash: " + hash);
        System.out.println("Check: " + PasswordUtil.checkPassword("1234", hash));

        String hash1 = "$2a$12$cF30A.hl3IF4VmUl5WxgjOEJ.2QtpJ8/7JmSmAUixRNYvnjRwkJWa";
        System.out.println("Hash: " + hash1);
        System.out.println("Check: " + PasswordUtil.checkPassword("123", hash1));

    }
}
