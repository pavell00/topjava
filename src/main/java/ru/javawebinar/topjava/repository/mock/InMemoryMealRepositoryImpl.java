package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by net_master on 16.12.2016.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    @Override
    public Meal save(Meal meal) {
        LOG.info("save " + meal);
        return null;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return true;
    }

    @Override
    public Meal get(int id) {
        LOG.info("get " + id);
        return null;
    }

    @Override
    public List<Meal> getAll() {
        LOG.info("getAll");
        return null;
    }
}
