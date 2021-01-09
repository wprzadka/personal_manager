package manager.components.iteration;

import manager.components.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskRegexFilter implements TaskFilter{
    Pattern pattern;

    public TaskRegexFilter(String regex){
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean satisfiesPredicate(Task task) {
        Matcher titleMatch = pattern.matcher(task.title);
        Matcher descriptionMatch = pattern.matcher(task.description);
        return titleMatch.find() || descriptionMatch.find();
    }
}
