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

    @Test
    public void shouldFindTasksByQuery() {
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

        // 1. Поиск по слову "Позвонить" (должен найтись simpleTask)
        Task[] expected1 = { simpleTask };
        Task[] actual1 = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected1, actual1);

        // 2. Поиск по слову "Хлеб" (должен найтись epic)
        Task[] expected2 = { epic };
        Task[] actual2 = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected2, actual2);

        // 3. Поиск по слову "НетоБанка" (должен найтись meeting)
        Task[] expected3 = { meeting };
        Task[] actual3 = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected3, actual3);

        // 4. Поиск по слову, которого нет (должен быть пустой массив)
        Task[] expected4 = {};
        Task[] actual4 = todos.search("Самолет");
        Assertions.assertArrayEquals(expected4, actual4);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSearchingInEmptyTodos() {
        Todos todos = new Todos();
        Task[] expected = {};
        Task[] actual = todos.search("Любой запрос");
        Assertions.assertArrayEquals(expected, actual);
    }
}