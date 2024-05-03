import java.util.ArrayList;
import java.util.Scanner;

public class JudoProgram {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args
     */
    // Main Method
    public static void main(String[] args) {
        ArrayList<Athlete> athletes = new ArrayList<>();

        displayWelcomeMessages();
        registerAthletes(athletes);
        displayAthletes(athletes);
        displayTotalCost(athletes);

        scanner.close();
    }

    // Diaplay Welcome Message function
    private static void displayWelcomeMessages() {
        System.out.println("------------------------------------------------------------");
        System.out.println("|                Welcome to North Sussex Judo!             |");
        System.out.println("------------------------------------------------------------");
        System.out.println("Manage and track your judo journey with ease.");
        System.out.println("From training plans to competition details and weight categories,");
        System.out.println("this program helps you optimize your performance and expenses.");
        System.out.println("------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Capture essential athlete information, training programs, and more.");
        System.out.println("Customize your experience with additional relevant details.");
        System.out.println("------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Receive categorized cost analysis and insights to stay on track.");
        System.out.println("Embark on your judo adventure, unlock your full potential,");
        System.out.println("and strive for excellence in North Sussex Judo!");
        System.out.println("------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Get ready to grow and take extraordinary steps.");
        System.out.println("------------------------------------------------------------");
        System.out.println(" ");
        pressEnter();
    }

    // Athlete Register and Store in arrayList

    private static void registerAthletes(ArrayList<Athlete> athletes) {
        int RegisteredSixAthlete = 0;
        while (true) {
            // Prompt to get Athlete Name
            String name = getValidName("Enter your name:");
            // Prompt to get Athlete current weight
            Weight weight = new Weight(getValidDoubleInput("Enter current weight in kilograms (kg):",
                    "Invalid input. Please enter a valid weight."));
            // prompt to select Training plan
            TrainingPlan trainingPlan = selectTrainingPlan();
            int competitions = (trainingPlan.getName().equals("Beginner")) ? 0
                    : getValidIntInput("Enter the number of competition this month:",
                            "Invalid input. Please enter an integer.");
            PrivateCoaching privateCoaching = getPrivateCoaching();

            Athlete athlete = new Athlete(name, weight, trainingPlan, competitions, privateCoaching);
            athletes.add(athlete);

            // display Athlete Information
            displayAthleteInformation(athlete);

            RegisteredSixAthlete += 1;

            // Ask to register more or not
            if (RegisteredSixAthlete > 6) {
                if (!wantToContinue("Want to register more athletes? (yes/no):")) {
                    break;
                }
            }
        }
    }

    // Display Athlete Information function

    private static void displayAthleteInformation(Athlete athlete) {
        System.out.println("\nAthlete Information\n");
        System.out.printf("Athlete Name : %s%n", athlete.getName());
        System.out.printf("Current Weight : %.2f kg%n", athlete.getWeight().getCurrentWeight());
        System.out.printf("Eligible Weight Category : %s%n",
                getEligibleWeightCategory(athlete.getWeight().getCurrentWeight()));
        System.out.printf("Training Program : %s%n", athlete.getTrainingPlan().getName());
        System.out.println("------------------------------------------------");
        System.out.printf("Total Cost : $%.2f%n", athlete.calculateTotalCost());
    }

    // compare Weight Catagory
    private static String getEligibleWeightCategory(double currentWeight) {
        if (currentWeight > 100) {
            return "Heavyweight";
        } else if (currentWeight > 90) {
            return "Light-Heavyweight";
        } else if (currentWeight > 81) {
            return "Middleweight";
        } else if (currentWeight > 73) {
            return "Light-Middleweight";
        } else if (currentWeight > 66) {
            return "Lightweight";
        } else {
            return "Flyweight";
        }
    }

    // Check Invalid value for Athlete Name input

    private static String getValidName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String name = scanner.nextLine();

            if (name.isEmpty() || name.matches(".*\\d.*")) {
                System.out.println("** The name cannot be empty or contain numbers.");
            } else {
                return name;
            }
        }
    }

    // Select Training Plan and Check Validation

    private static TrainingPlan selectTrainingPlan() {
        System.out.println("There are 3 types of training programs here:");
        System.out.println("1. Beginner (2 sessions per week) - Weekly fee ($25)");
        System.out.println("2. Intermediate (3 times a week) - Weekly fee ($30)");
        System.out.println("3. Elite (5 times per week) - Weekly fee ($35)");

        while (true) {
            int choice = getValidIntInput("Choose your training plan (1/2/3):",
                    "Invalid input. Please enter an integer.");

            switch (choice) {
                case 1:
                    return new TrainingPlan("Beginner", 25, false);
                case 2:
                    return new TrainingPlan("Intermediate", 30, true);
                case 3:
                    return new TrainingPlan("Elite", 35, true);
                default:
                    System.out.println("Invalid choice. Please choose a valid training plan.");
                    continue;
            }
        }
    }

    // Check Validation for Number Type Input

    private static int getValidIntInput(String prompt, String errorMessage) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    // Check Validation for decimal Number Input

    private static double getValidDoubleInput(String prompt, String errorMessage) {
        while (true) {
            try {
                System.out.println(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    // Get Private coaching
    // return PrivateCoaching object representing the private coaching hours per
    // week
    private static PrivateCoaching getPrivateCoaching() {
        // Ask to get private Coaching or not
        while (true) {
            System.out.println("Would you like private training? (Yes/No):");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                // Ask user the number of Private Coaching hours
                int privateCoachingHours;
                do {
                    privateCoachingHours = getValidIntInput(
                            "Enter the number of dedicated training hours per week (max 5 hours):",
                            "Invalid input. Please enter an integer.");
                    // Check user can receive maximun of 5 hours
                    if (privateCoachingHours > 5) {
                        System.out.println(
                                "You can receive a maximum of five hours. private coaching a week. Please enter a valid number.");
                    }
                } while (privateCoachingHours > 5);
                return new PrivateCoaching(privateCoachingHours);
            } else if (choice.equalsIgnoreCase("no")) {
                return new PrivateCoaching(0);
            } else {
                System.out.println("Invalid choice. Please enter Yes or No.");
            }
        }
    }

    // Prompt user for more register Function
    private static boolean wantToContinue(String prompt) {
        System.out.println(prompt);
        String choice = scanner.nextLine();
        return choice.equalsIgnoreCase("yes");
    }

    // function for press Enter to Enter welcome massage
    private static void pressEnter() {
        System.out.println("Press Enter to continue.");
        scanner.nextLine();
    }

    // display Athletes with Comparison of Weight Catagory
    private static void displayAthletes(ArrayList<Athlete> athletes) {
        ArrayList<Athlete> heavyWeightAthletes = new ArrayList<>();
        ArrayList<Athlete> lightHeavyWeightAthletes = new ArrayList<>();
        ArrayList<Athlete> middleWeightAthletes = new ArrayList<>();
        ArrayList<Athlete> lightWeightAthletes = new ArrayList<>();
        ArrayList<Athlete> flyWeightAthletes = new ArrayList<>();

        // Divide Althlete with Weight Catagory
        for (Athlete athlete : athletes) {
            double weight = athlete.getWeight().getCurrentWeight();
            if (weight > 100) {
                heavyWeightAthletes.add(athlete);
            } else if (weight > 90) {
                lightHeavyWeightAthletes.add(athlete);
            } else if (weight > 81) {
                middleWeightAthletes.add(athlete);
            } else if (weight > 73) {
                lightWeightAthletes.add(athlete);
            } else {
                flyWeightAthletes.add(athlete);
            }
        }

        // Check Condition for Weight catagory
        if (!heavyWeightAthletes.isEmpty()) {
            displayAthletesByWeightCategory("Heavy Weight", heavyWeightAthletes);
        }
        if (!lightHeavyWeightAthletes.isEmpty()) {
            displayAthletesByWeightCategory("Light-Heavyweight", lightHeavyWeightAthletes);
        }
        if (!middleWeightAthletes.isEmpty()) {
            displayAthletesByWeightCategory("Middleweight", middleWeightAthletes);
        }
        if (!lightWeightAthletes.isEmpty()) {
            displayAthletesByWeightCategory("Lightweight", lightWeightAthletes);
        }
        if (!flyWeightAthletes.isEmpty()) {
            displayAthletesByWeightCategory("Flyweight", flyWeightAthletes);
        }
    }

    // display all Athlete By Weight Catagory
    private static void displayAthletesByWeightCategory(String weightCategory, ArrayList<Athlete> athletes) {
        System.out.println("\n" + weightCategory + "\n");
        System.out.println("Name\t\tCurrent weight (kg)\tCompetition numbers\tTraining program\tPrivate lessons");
        System.out.println(
                "---------------------------------------------------------------------------------------------------");

        for (Athlete athlete : athletes) {
            System.out.printf("%s\t\t%.2f\t\t\t%d\t\t\t%s\t\t\t%d%n",
                    athlete.getName(),
                    athlete.getWeight().getCurrentWeight(),
                    athlete.getCompetitions(),
                    athlete.getTrainingPlan().getName(),
                    athlete.getPrivateCoaching().getHours());
        }
    }

    // Display total cost
    private static void displayTotalCost(ArrayList<Athlete> athletes) {
        double totalCost = athletes.stream().mapToDouble(Athlete::calculateTotalCost).sum();
        System.out.println("\nTotal cost: $" + totalCost);
    }
}