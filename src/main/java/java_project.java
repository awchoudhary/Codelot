/**
 * Created by Jane on 5/14/2017.
 */
public class java_project {
    public static void main (String args[]){
    /*
    We need you to save the village from the dragon's wrath!
    We'll let you in on a secret. The dragon has a weakness; it can't stand water. But the amount of water it can withstand is different each day.
    If you blast the dragon with the correct amount of water every time it comes for a whole month, the dragon will never come again!
    Hints:
        -The dragon only comes on days that are multiples of 5.
        -In order to defeat the dragon, on the days that the dragon comes,
            you must have soldiers throw dayNumber * 10 gallons of water on the dragon.
            You need to calculate what this number is.

    Here is the pseudocode (how the code should look and work) for this program:
        for every day of the month:
            if dragon coming = true:
                print the date and that the dragon is coming
                int gallons_of_water = dayNum*10;
                print gallons_of_water+" needed to defeat the dragon today"
            else if dragon coming != true:
                print the date and that the dragon is not coming
     */

        // Assume every month has 30 days
        int numDaysInMonth = 30;
        // Declaring a boolean variable to signify whether dragon is coming (true) or not (false)
        boolean dragonComing;

        /* For loop to iterate through each day and check if dragon is coming and if so, how to defeat it.
        This is where you will print a list of the days with whether the dragon is coming or not that day.
        If dragon is coming, on the same line that you said the dragon is coming,
            include instructions on how to defeat it that day. */

        // Write the for loop statement
        // CODE GOES HERE
        for (int day = 1; day <= numDaysInMonth; day++){

            // Originally we assume dragon is not coming (until we do calculation to figure out if it is)
            dragonComing = false;

            // Calculation: if the day is a multiple of 5, dragon is coming

            // Write if statement to check whether the dragon is coming this day
            // CODE GOES HERE
             if (day % 5 == 0){

                // Set dragonComing to true
                dragonComing = true;
            }

            // If dragon is coming, then print out that it is coming and what to do

            // Write if statement that executes the following code is the dragon IS coming
            // CODE GOES HERE
            if (dragonComing == true){
                // Statement that dragon is coming this day
                String dragonStatement = "Day "+ day +": dragon is coming, ";

                // Print dragonStatement. Do it so that the next thing printed will be continued on the same line
                // CODE GOES HERE
                 System.out.print(dragonStatement);

                // Variable gallons_of_water will store how many gallons of water the village needs that day.
                // Initially we set it to zero. This should be changed by your calculation!
                int gallons_of_water = 0;

                // Calculate number of gallons of water needed to defeat dragon (10 times the day) and set it equal to gallons_of_water
                // CODE GOES HERE
                 gallons_of_water = day * 10;

                // Instructions on how to defeat dragon
                String instructions = "have soldiers throw "+ gallons_of_water +" gallons of water on the dragon!";

                // Print instructions on what to do. Do it so that the next thing printed will be on the next line
                // CODE GOES HERE
                 System.out.println(instructions);

            }
            // If dragon is not coming, print out that dragon is not coming
            else {
                // Statement that dragon is not coming this day
                String dragonStatement = "Day "+ day +": dragon is not coming";

                // Print dragonStatement. Do it so that the next thing printed will be on the next line
                // CODE GOES HERE
                 System.out.println(dragonStatement);
            }
        }
    }
}
