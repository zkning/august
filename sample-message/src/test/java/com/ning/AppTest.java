package com.ning;

import static org.junit.Assert.assertTrue;
import com.ning.message.MessageApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple MessageApplication.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MessageApplication.class})
public class AppTest
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
