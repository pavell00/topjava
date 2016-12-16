package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;

/**
 * Created by net_master on 16.12.2016.
 */
public class MockUserRepository implements UserRepository {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public User save(User user) {
        LOG.info("Save " + user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("Delete " + id);
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
