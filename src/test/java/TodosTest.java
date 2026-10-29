import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    // 1. Находится несколько задач
    @Test
    public void shouldFindMultipleTasksByQuery() {
        // Создаем задачи так, чтобы слово "Купить" встречалось в двух из них
        SimpleTask simpleTask = new SimpleTask(1, "Купить хлеб");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Яйца", "Сыр"});
        Meeting meeting = new Meeting(3, "Встреча", "Проект", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic };
        Task[] actual = todos.search("Купить");

        Assertions.assertArrayEquals(expected, actual);
    }

    // 2. Находится ровно одна задача
    @Test
    public void shouldFindExactlyOneTaskByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting meeting = new Meeting(3, "Обсуждение", "Проект", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask };
        Task[] actual = todos.search("маме");

        Assertions.assertArrayEquals(expected, actual);
    }

    // 3. Находится 0 задач (ни одна не подходит)
    @Test
    public void shouldFindNoTasksWhenQueryDoesNotMatch() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting meeting = new Meeting(3, "Обсуждение", "Проект", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Самолет");

        Assertions.assertArrayEquals(expected, actual);
    }
}