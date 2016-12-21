package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal rest_save(Meal meal) {
        LOG.info("REST save " + meal);
        meal.setUserId(AuthorizedUser.id());
       return service.save(meal);
    }

    public List<MealWithExceed> rest_getAll() {
        List<MealWithExceed> list = new ArrayList<MealWithExceed>(MealsUtil.getWithExceeded(service.getAll(),
                MealsUtil.DEFAULT_CALORIES_PER_DAY));
        list.stream().forEach(System.out::println);
        LOG.info("MealRestController List<MealWithExceed> rest_getAll");
        return list;
    }

    public List<MealWithExceed> rest_getFiltered(LocalDate startDate, LocalDate endDate,
                                                 LocalTime startTime, LocalTime endTime) {

        List<MealWithExceed> list = new ArrayList<MealWithExceed>(MealsUtil.getWithExceeded(service.getAll(),
                MealsUtil.DEFAULT_CALORIES_PER_DAY));
        list.stream().forEach(System.out::println);
        LOG.info("MealRestController List<MealWithExceed> rest_getAll");
        return list;
    }
}
