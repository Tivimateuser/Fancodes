import org.fancode.services.Constants.fanCodeGeoRange;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.fancode.services.ToDo.filterTodosByUserId;
import static org.fancode.services.ToDo.getTodos;
import static org.fancode.services.User.filterUsersByGeoRange;
import static org.fancode.services.User.getUsers;

public class FanCodeTest {

    @Test
    public void verifyFanCodeUsersFiftyPercentTaskCompletion() {
        List<Map> users = getUsers();
        List<Map> fanCodeUsers = filterUsersByGeoRange(
                users,
                fanCodeGeoRange.LAT1.getValue(),
                fanCodeGeoRange.LAT2.getValue(),
                fanCodeGeoRange.LONG1.getValue(),
                fanCodeGeoRange.LONG2.getValue()
        );
        List<Integer> userIds = fanCodeUsers.stream()
                .map(user -> Integer.parseInt(user.get("id").toString()))
                .collect(Collectors.toList());

        List<Map> todos = getTodos();

        userIds.forEach(userId -> {
            List<Map> fanCodeTodos = filterTodosByUserId(todos, userId);

            float completedFanCodeTodos = (float) fanCodeTodos.stream()
                    .filter(todo -> todo.get("completed").equals(true))
                    .count();

            float incompleteFanCodeTodos = (float) fanCodeTodos.stream()
                    .count();

            float completedFanCodeTodosPercent = (completedFanCodeTodos / incompleteFanCodeTodos) * 100;
            System.out.println("Completed todos percentage of FanCode user: " + userId + " is " + completedFanCodeTodosPercent);
            Assert.assertTrue(completedFanCodeTodosPercent >= 50.0);
        });

    }

}
