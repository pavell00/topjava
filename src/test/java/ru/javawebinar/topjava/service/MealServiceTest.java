package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by net_master on 29.12.2016.
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
    public void testget() throws Exception {
        Meal meal = service.get(MEAL_ID, 100000);
        MATCHER.assertEquals(MEAL, meal);
    }

    @Test
    public void testdelete() throws Exception {

    }

    @Test
    public void testgetBetweenDates() throws Exception {

    }

    @Test
    public void testgetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testgetAll() throws Exception {
        Collection<Meal> all = service.getAll(100000);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL), all);
    }

    @Test
    public void testupdate() throws Exception {

    }

    @Test
    public void testsave() throws Exception {

    }

}