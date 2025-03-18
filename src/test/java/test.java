import com.dl.utils.SpellUtil;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author dongliang
 * @date 2024/10/25 15:43:43
 * @description
 **/
public class test {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void test1() {
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        System.out.println(encode);
    }

    @Test
    public void testPinyin(){
        String input = "董梁";
        String pinyin = SpellUtil.toPinyin(input);
        System.out.println(pinyin);
    }
}
