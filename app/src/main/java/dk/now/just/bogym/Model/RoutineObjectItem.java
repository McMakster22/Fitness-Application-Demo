package dk.now.just.bogym.Model;

public  class  RoutineObjectItem{

    private String weekOfTheDay;
    private String muscleGroup;
    private String exercise;
    private String set;
    private String rep;

    public RoutineObjectItem(String weekOfTheDay, String muscleGroup, String exercise, String set, String rep) {
        this.weekOfTheDay = weekOfTheDay;
        this.muscleGroup = muscleGroup;
        this.exercise = exercise;
        this.set=set;
        this.rep=rep;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getWeekOfTheDay() {
        return weekOfTheDay;
    }

    public void setWeekOfTheDay(String weekOfTheDay) {
        this.weekOfTheDay = weekOfTheDay;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}