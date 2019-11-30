package api.sportsCalc;

public class Activity {
    public ActivityType name;
    public int time;


    public Activity(){}

    public Activity(ActivityType name, double mass, double kcal) {
        this.name = name;
        this.time = (int) (60 * kcal / mass / getMetabolicEquivalent(name));
    }

    public static int getMetabolicEquivalent(ActivityType type){
        switch (type){
            case Billiard: case Bowling: return 3;
            case Archery: case Nordicwalking_4kmh: case Tabletennis: return 4;
            case Golf: case Run_5kmh: case Dancing: return 5;
            case Aerobic: case Badminton: case Nordicwalking_6kmh: case Cycling_15kmh: case Basketball: case Swimming: case Skying: case Snowboarding: return 6;
            case Football: case Hiking: return 7;
            case Beachvolleyball: case Climbing: case Tennis: case Run_8kmh: return 8;
            case Cycling_25kmh: return 10;
            case Run_12kmh: return 12;
            default: return 0;
        }
    }

}
