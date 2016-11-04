import com.hsq.component.generator.MybatisGenerator;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class GeneratorTest {
    @Test
    public void generator() throws IOException {
        new MybatisGenerator().generator();
    }
}
