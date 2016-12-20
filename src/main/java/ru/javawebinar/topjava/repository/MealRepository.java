package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public interface MealRepository {
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    // null if not found
    //User getByEmail(String email);

    List<Meal> getAll();

    int getCountId();
}
