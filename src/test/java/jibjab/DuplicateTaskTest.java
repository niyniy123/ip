package jibjab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for duplicate detection through JibJab.getResponse command handling.
 */
public class DuplicateTaskTest {

    @Test
    public void testPreventToDoDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("todo read book");
        // Ensure first add succeeds and count is 1
        assertTrue(first.contains("Now you have 1 tasks in the list"));

        String second = bot.getResponse("todo read book");
        assertEquals("This task already exists in your list!", second);

        // list should still contain only one
        String list = bot.getResponse("list");
        assertEquals("[T][ ] read book", list);
    }

    @Test
    public void testPreventDeadlineDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("deadline submit report /by Jan 01 2077 18:00");
        assertTrue(first.contains("Now you have 1 tasks in the list"));

        String dup = bot.getResponse("deadline submit report /by Jan 01 2077 18:00");
        assertEquals("This task already exists in your list!", dup);

        String list = bot.getResponse("list");
        assertEquals("[D][ ] submit report (by: Jan 01 2077 18:00)", list);
    }

    @Test
    public void testPreventEventDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("event party /from Jan 01 2077 18:00 /to Feb 01 2077 19:00");
        assertTrue(first.contains("Now you have 1 tasks in the list"));

        String dup = bot.getResponse("event party /from Jan 01 2077 18:00 /to Feb 01 2077 19:00");
        assertEquals("This task already exists in your list!", dup);

        String list = bot.getResponse("list");
        assertEquals("[E][ ] party (from: Jan 01 2077 18:00 to: Feb 01 2077 19:00)", list);
    }
}
