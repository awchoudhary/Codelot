/**
 * Created by Jane on 5/14/2017.
 */
public class java_project {
    public static void main (String args[]){
    /*
    <b>We need you to save the village from the dragon's wrath!</b>\n
    We'll let you in on a secret. The dragon has a weakness; it can't stand water BUT the amount of water it can withstand is different each day. If you blast the dragon with the correct amount of water every time it comes for a whole month, the dragon will never come again!\n
	
    \n<b>Hints:</b>\n
        \t-The dragon only comes on days that are multiples of 5.\n
        \t-In order to defeat the dragon, on the days that the dragon comes, you must have soldiers throw dayNumber * 10 gallons of water on the dragon. <i>You need to calculate what this number is.</i>\n
		
	\n<b>Print out a list of every day of the month followed by whether the dragon will come that day and if so, also print instructions for how to defeat the dragon that day.</b>\n
    \nHere is the pseudocode (how the code should look and work) for this program:\n
	<i>\tfor every day of the month:\n
		\t\tif dragon coming = true:\n
			\t\t\tprint the date and that the dragon is coming\n
			\t\t\tint gallons_of_water = dayNum*10;\n
			\t\t\tprint gallons_of_water+" needed to defeat the dragon today"\n
		\t\telse if dragon coming != true:\n
			\t\t\tprint the date and that the dragon is not coming</i>
     */

        // Assume every month has 30 days
        int numDaysInMonth = 30;
        // Declaring a boolean variable to signify whether dragon is coming (true) or not (false)
        boolean dragonComing;

        /* For loop to iterate through each day and check if dragon is coming and if so, how to defeat it.
        This is where you will print a list of the days with whether the dragon is coming or not that day.
        If dragon is coming, on the same line that you said the dragon is coming,
            include instructions on how to defeat it that day. */

        // Write the for loop statement (use variable 'day' as the int used to iterate)
        // HINT: Remember that the first day of the month is 1, not 0.
        // HINT: Days of the month go from 1 to 30 (including 30).
        // If you want a loop to run from 0 to 5 (including 5), you do 'while (x <= 5)' or 'for (int x = 0; x <= 5; x++)'
        // CODE GOES HERE
        for (int day = 1; day <= numDaysInMonth; day++){

            // Originally we assume dragon is not coming (until we do calculation to figure out if it is)
            dragonComing = false;

            // Calculation: if the day is a multiple of 5, dragon is coming

            // Write if statement to check whether the dragon is coming this day
            // HINT: If a number x is a multiple of number y, it means that number x is fully divisible by number y.
            // Ex. 21 is a multiple of 3 which means that 21 divided by 3 is a whole number (7).
            // HINT: To find out if a number x is divisible by number y, we use the mod function '%'.
            // If x % y is equal to 0, x is divisible by y.
            // HINT: To check equality, we use '=='.
            // Ex. int x = 5; if (x == 5){ System.out.print("x is equal to 5"); }
            // CODE GOES HERE
            if (day % 5 == 0){

                // Set dragonComing to true
                dragonComing = true;
            }

            // Write if statement that executes the following code if the dragon IS coming (if dragonComing is equal to true)
            // HINT: System.out.print() when you dont want a new line after this statement. System.out.println() when you want a new line after this statement.
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
                // HINT: To divide a number x by number y, we do x / y. To multiply a number x by number y, we do x * y.
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