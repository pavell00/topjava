package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    Meal save(Meal user);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    //Meal getByEmail(String email) throws NotFoundException;

    List<Meal> getAll();
}
