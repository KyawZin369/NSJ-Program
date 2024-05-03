class PrivateCoaching implements cost {
    private int hours;
    private static final double COST_PER_HOUR = 9;

    public PrivateCoaching(int hours) {
        this.hours = hours;
    }


/** 
 * @return double
 */
//override calculate cost and hours
    @Override
    public double calculateCost() {
        return hours * COST_PER_HOUR;
    }

    public Object getHours() {
        return hours;
    }
}