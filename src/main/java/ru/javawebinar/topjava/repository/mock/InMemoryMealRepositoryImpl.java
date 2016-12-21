package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

/**
 * Created by net_master on 16.12.2016.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>(MEALS.size());
    private AtomicInteger counter = new AtomicInteger(MEALS.size());

    public InMemoryMealRepositoryImpl(){
        int i=0;
        for(Meal m: MEALS) {
            repository.put(m.getId(), m);
            i++;
        }
    }

    @Override
    public Meal save(Meal meal) {
        LOG.info("isNew : " + meal.isNew());
        if (meal.isNew()){
            meal.setId(counter.incrementAndGet());
        }
        LOG.info("InMemoryMealRepositoryImpl save: " + meal.toString());
        return repository.put(meal.getId(), meal);
    }

    @Override
    public boolean delete(int id) {
        if (repository.get(id) != null){
            repository.remove(id);
            LOG.info("delete " + id);
            return true;
            //LOG.info("key for delete " + repository.get(id).getId());
        } else {
            LOG.info("key(id) for delete not found ");
            return false;
        }
    }

    @Override
    public Meal get(int id) {
        for (Integer key : repository.keySet()) {
            Meal value = repository.get(key);
            if (value.getId().equals(id)) {
                //System.out.println("get Key = " + key + ", Value = " + value.getId());
                return value;
            }
        }
        LOG.info("get meal " + id);
        return null;
    }

    @Override
    public List<Meal> getAll() {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        List<Meal> list = new ArrayList<Meal>(repository.values());
        LOG.info("List<Meal> getAll sorted");
        return list.stream()
                .filter(s -> s.getUserId()==authorizedUser.id())
                .sorted(Comparator.comparing(Meal::getTime))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

    public int getCountId(){
        return counter.incrementAndGet();
    }
}
