# CSC-411 Databases Assignment #4, Problem 5.12
***
### (I spent too much time on this...got carried away)

Problem: Write a Java program that allows university administrators to print the teaching record of an instructor.

* Start by having the user input the login *ID* and password; then open the proper connection.
* The user is asked next for a search substring and the system returns (ID, *name*) pairs of instructors whose names match the substring. Use the **like** ('%substring%') construct in SQL to do this. If the search comes back empty, allow continued searches until there is a nonempty result.
* Then the user is asked to enter an ID number, which is a number between 0 and 99999. Once a valid number is entered, check if an instructor with that ID exists. If there is no instructor with the given ID, print a reasonable message and quit.
* If the instructor has taught no courses, print a message saying that. Other-wise print the teaching record for the instructor, showing the department name, course identifier, course title, section number, semester, year, and total enrollment (and sort those by *dept_name*, *course_id*, *year*, *semester*).

Test carefully for bad input. Make sure your SQL queries won't throw an exception. At login, exception may occur since the user might type a bad password, but catch those exceptions and allow the user to try again.

**DISCLAIMER*: I was only required to do the first two bullets so all of these have not been implemented, *yet =D*.

***
**USAGE:** 

*Searching:* Type a string that matches a name in the instructors table. If you want to see all instructors in the table, just press 'ENTER'.

Example: Searching for 'John Smith', you can type 'john' or 'John' or 'JOHN' or 'joh' or 'smit', okayyy....you get the point.

| Command | What it does |
| --------|--------------|
| -h, --help | This will bring up the help menu in the program |
| -q, --quit | This will exit the program |

**SHOUTOUT!** to my professor Dr. Bo Li
