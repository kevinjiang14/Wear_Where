# Wear_Where
An android app to suggest clothing and accessories based on the weather of your current location.

# Working Functionality

- Weather Tab: Currently displays the weather of your current location, as well as daily and hour-by-hour weather forecasts. Users can specify their preferred chilly and warm temperature ranges which clothing suggestions will be based off of. Can search a place to get the current temperature of that place. 

- Apparel Tab: Back-end for clothing suggestions. Headwear, Upperbody, Lowerbody, and Shoes buttons to suggest apparel based on the weather in your current location. Additional clothing suggestion is also given based on any drastic temperature or weather condition changes to be expected in the next 16hours. Gender can be specified to determine the returned clothing suggestion through a switch. 

- Vacation Tab: Functionality to properly allow user to search for the weather conditions for a queried place during a specified interval. Suggestions on what to bring is given on a daily, weekly, or monthly interval depending on the specified vacation duration. These suggestions are based on the highest chance of a temperature range occuring. Also clothing consideration is displayed when an alternative temperature range is high enough.

- Road Trip Tab: Can search places (suggested places show up while searching) for starting and ending points, and place these points on the map as markers. Can also view your current location. Functionality to properly allow users to search for the weather conditions at pre-specified intervals throughout a queried route. Map updates with an estimated route between the specified locations with weather icons, interval locations, distance traveled from the starting location, time elapsed from the starting location (based on non-stop driving after querying the route), Estimated Arrival Time (correct with respect to the time zone of the interval), temperature and conditions at the Estimated Arrival Time, and clothing suggestions based at each interval location based on the estimated arrival time to that location. In addition all the clothing suggestion during the whole trip is displayed 

# Pending Functionality

- Vacation Tab: Update weather closer to starting date of vacation.

# How to run

- Pull the repository files and place them into a folder.
- Place this folder in your Android Studio workspace.
- Open the project in Android Studio.
- You can now view all source files in Android Studio, and debug by using the green triangle.

# Technical Details

**Determining User's City & State**

The code is found in the getLocation() method of the Main Activity. The user's city and state are needed to get useful data from the Weather Underground API calls. The city and state of the user's current location are determined from the user's latitude and longitude. The Geocoder object uses reverse geocoding to determine a list of possible addresses from a given latitude and longitude. The first Address object returned by the Geocoder is used because it is most likely the user's current location. The Address class methods getLocality() and getAdminArea() return the city and state respectively. The location TextView is updated with the user's city and state. Finally, the Weather Underground API calls are made witht the updated city and state Strings.

**Database**

The database code is found in the Database package. SQLite is the database used in Android apps. The DatabaseCreator class creates a database file called "range.db"  with a table called "ranges". Each row in the table has three columns: id, min, and max. The SQLite "create table" command is executed in the DatabaseCreator's onCreate() method. The TempRange class is used to move data in and out of the database. It has variables to hold the id, min, and max for any row in the database. The DatabaseInterface class is used to interact with the database. The datasource variable in Main Activity is a DatabaseInterface object. Data is returned using TempRange objects. Cursor objects copy database row values into TempRange objects. Data is inserted into the database using ContentValues objects. The row id is used to update existing rows in the database.
