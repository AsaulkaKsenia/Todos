package ru.netology.manager;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.domain.Epic;
import ru.netology.domain.Meeting;
import ru.netology.domain.SimpleTask;
import ru.netology.domain.Task;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
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

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOfTrueQuerySimpleTask() {
        SimpleTask task = new SimpleTask(23, "Важное письмо");

        String query = "письмо";

        boolean expected = true;

        boolean actual = task.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfFalseQuerySimpleTask() {
        SimpleTask task = new SimpleTask(23, "Важное письмо");

        String query = "встреча";

        boolean expected = false;

        boolean actual = task.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfTrueQueryEpic() {
        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Приказ"};
        Epic epic = new Epic(17, subtasks);

        String query = "Приказ";

        boolean expected = true;

        boolean actual = epic.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfFalseQueryEpic() {
        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Приказ"};
        Epic epic = new Epic(17, subtasks);

        String query = "Письмо";

        boolean expected = false;

        boolean actual = epic.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfTrueQueryMeetingOnlyTopic() {
        Meeting meeting = new Meeting(5, "Конкурс", "Сетевое взаимодействие", "Первая декада июня");

        String query = "Конкурс";

        boolean expected = true;

        boolean actual = meeting.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfTrueQueryMeetingOnlyProject() {
        Meeting meeting = new Meeting(5, "Конкурс", "Сетевое взаимодействие", "Первая декада июня");

        String query = "взаимодействие";

        boolean expected = true;

        boolean actual = meeting.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfTrueQueryMeetingEverywhere() {
        Meeting meeting = new Meeting(5, "Конкурс учредителя", "Сеть учредителя", "Первая декада июня");

        String query = "учредителя";

        boolean expected = true;

        boolean actual = meeting.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfFalseQueryMeeting() {
        Meeting meeting = new Meeting(5, "Конкурс", "Сетевое взаимодействие", "Первая декада июня");

        String query = "Письмо";

        boolean expected = false;

        boolean actual = meeting.matches(query);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindOfQueryTasksAll() {

        SimpleTask task = new SimpleTask(23, "Важное письмо");
        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Разъясняющее письмо"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(5, "Конкурс (письмо)", "Сетевое взаимодействие", "Первая декада июня");


        Todos todos = new Todos();

        todos.add(task);
        todos.add(meeting);
        todos.add(epic);

        String query = "письмо";

        Task[] expected = {task, meeting, epic};

        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindOfQueryTasInOne() {

        SimpleTask task = new SimpleTask(23, "Важное");
        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Разъяснения"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(5, "Конкурс (письмо)", "Сетевое взаимодействие", "Первая декада июня");


        Todos todos = new Todos();

        todos.add(task);
        todos.add(meeting);
        todos.add(epic);

        String query = "письмо";

        Task[] expected = {meeting};

        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindOfQueryTaskInTwo() {

        SimpleTask task = new SimpleTask(23, "Важное письмо");
        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Разъяснения"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(5, "Конкурс (письмо)", "Сетевое взаимодействие", "Первая декада июня");


        Todos todos = new Todos();

        todos.add(task);
        todos.add(meeting);
        todos.add(epic);

        String query = "письмо";

        Task[] expected = {task, meeting};

        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNoFindOfQueryTask() {

        String[] subtasks = {"Запрос учредителя", "Ответ на запрос", "Разъяснения"};
        Epic epic = new Epic(55, subtasks);
        SimpleTask task = new SimpleTask(23, "Важное письмо");
        Meeting meeting = new Meeting(5, "Конкурс (письмо)", "Сетевое взаимодействие", "Первая декада июня");


        Todos todos = new Todos();

        todos.add(task);
        todos.add(meeting);
        todos.add(epic);

        String query = "собеседование";

        Task[] expected = {};

        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);

    }

}

