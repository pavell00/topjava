package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.MealsUtil.MEALS;
import static ru.javawebinar.topjava.util.MealsUtil.USERS;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>(USERS.size());

    public InMemoryUserRepositoryImpl(){
        int i=0;
        for(User u: USERS) {
            repository.put(i, u);
            i++;
        }
    }

    @Override
    public boolean delete(int id) {
        for (Integer key : repository.keySet()) {
            User value = repository.get(key);
            if (value.getId().equals(id)) {
                repository.remove(key);
                //System.out.println("delete Key = " + key + ", Value = " + value.getId());
                return true;
            }
        }
        LOG.info("delete " + id);
        return false;
    }

    @Override
    public User save(User user) {
        User new_user;
        new_user = new User(repository.size()+1, user.getName(), user.getEmail(), user.getPassword(), Role.ROLE_USER);
        repository.put(repository.size()+1, new_user);
        LOG.info("save " + user);
        return user;
    }

    @Override
    public User get(int id) {
        for (Integer key : repository.keySet()) {
            User value = repository.get(key);
            if (value.getId().equals(id)) {
                return value;
            }
        }
        LOG.info("get meal " + id);
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>(repository.values());
        LOG.info("List<User> getAll sorted");
        return list.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        for (Integer key : repository.keySet()) {
            User value = repository.get(key);
            if (value.getEmail().equals(email)) {
                LOG.info("getByEmail " + value.getName());
                return value;
            }
        }
        return null;
    }
}
