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
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

/**
 * Created by net_master on 16.12.2016.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>(MEALS.size());

    public InMemoryMealRepositoryImpl(){
        int i=0;
        for(Meal m: MEALS) {
            repository.put(i, m);
            i++;
        }
    }

    @Override
    public Meal save(Meal meal) {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        Meal new_meal;
        if (meal.getId()==null){
            new_meal = new Meal(meal.getDateTime(), meal.getDescription() , meal.getCalories(),
                    authorizedUser.id(), repository.size()+1 );
        } else {
            new_meal = new Meal(meal.getDateTime(), meal.getDescription() , meal.getCalories(),
                    authorizedUser.id(), meal.getId() );
        }
        repository.put(repository.size()+1, new_meal);
        LOG.info("InMemoryMealRepositoryImpl save: " + new_meal.toString());
        return new_meal;
    }

    @Override
    public boolean delete(int id) {
        //System.out.println(repository.size());
        for (Integer key : repository.keySet()) {
            Meal value = repository.get(key);
            if (value.getId().equals(id)) {
                //System.out.println("delete Key = " + key + ", Value = " + value.getId());
                return true;
            }
        }
        LOG.info("delete " + id);
        return false;
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
        List<Meal> list = new ArrayList<Meal>(repository.values());
        LOG.info("List<Meal> getAll");
        return list;
    }
}
