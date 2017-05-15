# Assume every month has 30 days
numDaysInMonth = 30
# For loop to iterate through each day and check if dragon is coming and if so, how to defeat it.
# This is where you will print a list of the days with whether the dragon is coming or not that day.
# If dragon is coming, on the same line that you said the dragon is coming,
# include instructions on how to defeat it that day.
# Write the for loop statement (use variable 'day' as the int used to iterate)
for day in range(1,numDaysInMonth+1):
    # Originally we assume dragon is not coming (until we do calculation to figure out if it is)
    dragonComing = False
    #Calculation: if the day is a multiple of 5, dragon is coming
    # Write if statement to check whether the dragon is coming this day
    if day % 5 == 0:
        # Set dragonComing to True
        dragonComing = True
    # Write if statement that executes the following code if the dragon IS coming (if dragonComing is equal to True)
    if dragonComing == True:
        # Statement that dragon is coming this day
        dragonStatement = "Day "+ str(day) +": dragon is coming, "

        # Print dragonStatement. Do it so that the next thing printed will be continued on the same line
        print(dragonStatement, end='')

        # Variable gallons_of_water will store how many gallons of water the village needs that day.
        # Initially we set it to zero. This should be changed by your calculation!
        gallons_of_water = 0

        # Calculate number of gallons of water needed to defeat dragon (10 times the day) and set it equal to gallons_of_water
        gallons_of_water = day * 10

        # Instructions on how to defeat dragon
        instructions = "have soldiers throw "+str(gallons_of_water) +" gallons of water on the dragon!"

        # Print instructions on what to do. Do it so that the next thing printed will be on the next line
        print(instructions)

        # If dragon is not coming, print out that dragon is not coming
    elif dragonComing == False:
        # Statement that dragon is not coming this day
        dragonStatement = "Day "+ str(day) +": dragon is not coming"

        # Print dragonStatement. Do it so that the next thing printed will be on the next line
        print (dragonStatement)