public class Athlete {
    private String name;
    private Weight weight;
    private TrainingPlan trainingPlan;
    private int competitions;
    private PrivateCoaching privateCoaching;

    // Constructure and getter,setter method for Athlete

    public Athlete(String name, Weight weight, TrainingPlan trainingPlan, int competitions,
            PrivateCoaching privateCoaching) {
        this.name = name;
        this.weight = weight;
        this.trainingPlan = trainingPlan;
        this.competitions = competitions;
        this.privateCoaching = privateCoaching;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public int getCompetitions() {
        return competitions;
    }

    public PrivateCoaching getPrivateCoaching() {
        return privateCoaching;
    }

    public double calculateTotalCost() {
        if (trainingPlan.getName().equals("Beginner")) {
            return trainingPlan.calculateCost() + privateCoaching.calculateCost();
        } else {
            return trainingPlan.calculateCost() + privateCoaching.calculateCost() + (competitions * 22);
        }
    }
}
