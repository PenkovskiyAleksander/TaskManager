import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {

    @Test
    public void simpleTaskShouldMatchTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        assertTrue(task.matches("Позвонить"));
        assertTrue(task.matches("родителям"));
        assertFalse(task.matches("Купить"));
    }

    @Test
    public void epicShouldMatchSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        assertTrue(epic.matches("Молоко"));
        assertTrue(epic.matches("Хлеб"));
        assertFalse(epic.matches("Сыр")); // Такого нет
    }

    @Test
    public void meetingShouldMatchTopicOrProject() {
        Meeting meeting = new Meeting(3, "Выкатка 3й версии", "НетоБанк", "Завтра");

        assertTrue(meeting.matches("Выкатка")); // Совпадение по topic
        assertTrue(meeting.matches("НетоБанк")); // Совпадение по project
        assertFalse(meeting.matches("Сбер")); // Нет совпадений
    }
}