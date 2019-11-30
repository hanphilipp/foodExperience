package api.sportsCalc;

import java.util.ArrayList;

public class SportsCalculator {

    public static ActivityModel calculateSports(double mass, double kcal){
        ArrayList<Activity> activities = new ArrayList<>();

        for ( ActivityType a: ActivityType.values()
             ) {
            activities.add(new Activity(a, mass, kcal));
        }

        return new ActivityModel(activities);
    }
}
