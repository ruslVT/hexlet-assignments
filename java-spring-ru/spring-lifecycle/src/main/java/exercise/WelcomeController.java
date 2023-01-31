package exercise;

import exercise.daytimes.Day;
import exercise.daytimes.Daytime;
import exercise.daytimes.Evening;
import exercise.daytimes.Morning;
import exercise.daytimes.Night;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Meal meal;

    @Autowired
    Daytime daytime;

//    @Autowired
//    Day day;
//
//    @Autowired
//    Evening evening;
//
//    @Autowired
//    Morning morning;
//
//    @Autowired
//    Night night;

    @GetMapping(path = "/daytime")
    public String getDayTimeAndMeal() {
        String time = daytime.getName();
        return "It is " + time + " now. Enjoy your " + meal.getMealForDaytime(time) + ".";
    }

}
// END
