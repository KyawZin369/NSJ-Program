class TrainingPlan implements cost {
    private String name;
    private double cost;
    private boolean canEnterCompetition;

    public TrainingPlan(String name, double cost, boolean canEnterCompetition) {
        this.name = name;
        this.cost = cost;
        this.canEnterCompetition = canEnterCompetition;
    }

    
    /** 
     * @return double
     */
    @Override
    public double calculateCost() {
        return cost;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
}
