package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by net_master on 30.12.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void save() throws Exception {
        Meal newmeal = new Meal(100002, of(2016, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal created = service.save(newmeal, 100000);
        newmeal.setId(created.getId());
        MATCHER.assertEquals(newmeal, service.get(100002, 100000));
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100003,100000);
        MATCHER.assertEquals(MEAL2, meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL1.getId(), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL8, MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(MEALS, all);
    }

    @Test
    public void update() throws Exception {
        Meal updatedmeal = new Meal(100002, of(2016, Month.MAY, 30, 10, 0), "Завтрак", 500);
        updatedmeal.setDescription("UpdatedEait");
        updatedmeal.setCalories(330);
        service.update(updatedmeal, 100000);
        MATCHER.assertEquals(updatedmeal, service.get(100002, 100000));
    }

    @Test(expected = NotFoundException.class)
    public void get_enemy_meal() throws Exception{
        Meal meal = service.get(100002,100001);
        MATCHER.assertEquals(MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void delete_enemy_meal() throws Exception{
        Meal meal = service.get(100002,100001);
        service.delete(meal.getId(), 100001);
        MATCHER.assertEquals(MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void update_enemy_meal() throws Exception {
        Meal updatedmeal = new Meal(100002, of(2016, Month.MAY, 30, 10, 0), "Завтрак", 500);
        updatedmeal.setDescription("UpdatedEait");
        updatedmeal.setCalories(330);
        service.update(updatedmeal, 100001);
        MATCHER.assertEquals(updatedmeal, service.get(100002, 100001));
    }
}