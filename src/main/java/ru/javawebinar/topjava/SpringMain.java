package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.service.UserServiceImpl;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN));

            MealServiceImpl mealService = appCtx.getBean(MealServiceImpl.class);
            UserServiceImpl userService = appCtx.getBean(UserServiceImpl.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            //System.out.println(mealService.getAll().toString());
            //System.out.println(AuthorizedUser.id());

            //mealService.delete(8);
            /*List<Meal> list = new ArrayList<>();
            list = mealService.getAll();
            list.stream().forEach(System.out::println);*/

            //Meal meal = new Meal(LocalDateTime.now(), "второй ужин", 2500, AuthorizedUser.id(), 10);
            //mealService.save(meal);
            //System.out.println(mealService.getAll().toString());
            //mealService.get(5);

            //userService.delete(1);
            //userService.getByEmail("user2@com");
            /*List<User> list = new ArrayList<User>();
            list = userService.getAll();
            list.stream().forEach(System.out::println);*/

            //mealRestController.rest_save(new Meal(LocalDateTime.now(), "test", 666, AuthorizedUser.id(), 1));
            mealRestController.rest_getAll();
        }
    }
}
