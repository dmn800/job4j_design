package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        int added = 0, changed = 0;
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                added++;
            }
            if (map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                changed++;
            }
            map.remove(user.getId());
        }
        int deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
