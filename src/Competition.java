public class Competition {
    private int competitions;

    public Competition(int competitions) {
        this.competitions = competitions;
    }

    
    /** 
     * @return int
     */
    public int getCompetitions() {
        return competitions;
    }

    
    /** 
     * @return double
     */
    public double calculateCost() {
        return competitions * 22;
    }
}
