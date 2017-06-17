import com.sun.Start;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pengjikun on 2017/6/11.
 * 测试基础类，单元测试只需继承该类即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Start.class)
public class BaseTest {
}
