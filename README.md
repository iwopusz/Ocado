	
# OrdersScheduler

Application for making orders picking schedules for workers.

# Technologies used:
#Java
#JSON


# This application makes orders picking schedule for every worker available at the store.
# User uploads store.json file with store details (opening hours, workers available) and orders.json with orders details (ID, picking time, order value and deadline).
# Then schedule for every worker is printed (what order to pick and when to start picking) and then completed orders summary - number of orders and total value.
# If there's not enough time to pick order before deadline or opening hours, order is skipped.


# START:

Program is starting with getFiles() method and then schedule maker method is initialized.
I was experimenting with different priorities for orders and so, four different sorting methods and
schedule makers were made. I'm using only one, with orders sorted by completion time deadline, which is the most
efficient according to total number of orders completed and total value of those.
Each method is using 1 of 4 sorting methods defined.
Scanner is used for user's absolute path inputs and those paths are assigned to String variables.
Then data from both .json files is pulled using two different methods for each file.
Google's json-simple is used to parse files and get needed values.
Array of pickers is created and pickers with ID and available time are added.
Array of orders is created and orders with ID, value, picking time and deadline are added.
	

# CORE:

Using arrays of orders, array of pickers, completed orders count, completed orders value and 2 conditions.
Conditions check whether picker will finish picking order before or on deadline.
After checking conditions, pickerID, orderId and time of picking start is printed.
Then order picking time is subtracted from picker's available time.
Completed orders counter and total value of completed orders are updated and order removed from list.
Program is checking whether continue to another picker or if reached last picker, return to first.
If none of pickers have time to pick remaining orders, summary regarding orders is printed.
For every picker available time is resetted - for next iteration by another method.

