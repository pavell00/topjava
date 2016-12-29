package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int MEAL_ID = START_SEQ;

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2016, Month.DECEMBER, 29, 10, 0),
            "завтрак",
            MealsUtil.DEFAULT_CALORIES_PER_DAY);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
