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

**Android Studio**
- Pull the repository files and place them into a folder.
- Place this folder in your Android Studio workspace.
- Open the project in Android Studio.
- You can now view all source files in Android Studio, and debug by using the green triangle.

**Running it on Android**
- Install the application from the Google Play Store from [here](https://play.google.com/store/apps/details?id=com.ubcse.kevin.wear_where).
- Provide the requested permissions, and you're good to go!

# Technical Details

## Determining User's City & State

The code is found in the getLocation() method of MainActivity. The user's city and state are needed to get useful data from the Weather Underground API calls. The city and state of the user's current location are determined from the user's latitude and longitude. The Geocoder object uses reverse geocoding to determine a list of possible addresses for a given latitude and longitude. The first Address object returned by the Geocoder is used because it is most likely the user's current location. The Address class methods getLocality() and getAdminArea() return the city and state respectively. The location TextView is updated with the user's city and state. Finally, the Weather Underground API calls are made witht the updated city and state Strings.

## Database

The database code is found in the Database package. SQLite is the database provided for Android apps. The DatabaseCreator class creates a database file called "range.db"  with a table called "ranges". Each row in the table has three columns: id, min, and max. The SQLite "create table" command is executed in the DatabaseCreator's onCreate() method. The TempRange class is used to move data in and out of the database. It has variables to hold the id, min, and max for any row in the database. The DatabaseInterface class is used to interact with the database. The variable datasource in Main Activity is a DatabaseInterface object. The variable datasource has to close when the app is paused and reopen when the app resumes again. Data is returned using TempRange objects. Cursor objects copy database row values into TempRange objects. Data is inserted into the database using ContentValues objects. The row id is used to update existing rows in the database.

## User Preferences

The user can set weather preferences using the menu button. The menu button is clickable to show a Popup Window, as defined in activity_main.xml. In the onCreate() method of MainActivity, the database is checked to see if any data is added. If not, default temperature ranges are put into the database ([60, 79] for the warm range and [40, 59] for the chilly range). The onClick functions for the clothing suggestion buttons are defined to use the database values every time. This makes sure that the clothing suggestions change whenever the user changes the preferences. The code is found in the showPopup() method of MainActivity. The user can update either the warm range or the chilly range. When the user selects a range, a prompt provides two NumberPickers (min and max). The two NumberPickers are set to the database values for the given range. The user can select a temperature between 0F and 100F. If the user selects "ok", the new range is saved into the database. The hot range is any value above the warm range, while the freezing range is any value below the chilly range. These four ranges help with the clothing suggestions.

## Search Places For Current Temperatures

The user can also search places for current temperatures using the menu button. The code is found in the showPopup() method of MainActivity. The user provides the city and state using two EditText widgets. A CurrentConditionAST object is created to make a Weather Underground API call using the city and state provided by the user. The current temperature of the place queried by the user is displayed in a new prompt.

## Apparel
This tab is for the user to view clothing suggestions for the current weather outside as well as a separate set of suggestions in the case that the weather changes to a certain degree in the later hours of the day.

- Clothes
    - The clothes were collected by surveying a group of individuals for their own wardrobe. They were asked to fill out a text file and organize their wardrobe based upon which temperature condition they would wear each clothing item. The temperature conditions are separated into four ranges that are defined as freezing, chilly, warm, and hot. 
    - After receiving their submission, the text files were manually assessed for any errors like wrong formatting and misspelling. Then they are compiled into two separate text files, one for male clothing and one for female clothing. Both files are then passed as parameters during the creation of the Clothing class that is defined in the /wear folder.
    
- Gender Button
    - The button is a switch that changes the gender of the clothing suggestions that the user receives. When the switch is clicked, it changes a globally defined String that all classes have access to. This will change the user's clothing suggestions throughout the whole application. A Toast message will pop up to notify the user which gender the app is currently set to and the code for this switch is in onCreate() of the Main Activity class.

- Suggestion Buttons
    - The user can choose which body part they want to see clothing suggestions for by clicking one of the buttons:
        - Miscellaneous, Upperbody, LowerBody, Shoes
        
    - Each button's onClick function is defined in the onCreate() method in the MainActivity class. Once clicked, they call on one of the accessor methods from the Clothing class which returns a set of clothing pertaining to that body part that was parsed from the text file:
        - getMisc(), getUpperBody(), getLowerBody(), getShoes()

    - The ClothingActivity class will then start and bring up a new activity formatted under clothing_list.xml. The screen contains two ListView widgets that adapt a custom list format that's under mylist.xml. The top ListView widget provides details on the current temperature and current condition and gives clothing suggestions that reflect those details. The ListView widget on the bottom half will show details when the temperature changes to a degree that lands the user in a different temperature range. If the current temperature is higher than the user's lower bound range setting for Chilly Temperature, the clothing suggestions will be in the Chilly range. However if a few hours later the temperature drops to below the bound, the user has moved from the Chilly range down to the Freezing range. The ListView will then show details of how many hours later the change of temperature range happens and also provide details of the temperature and condition at that hour. The clothing suggestions in that ListView reflect items that they would need in that new temperature range.
        
- Warning Message
    - The warning message is simply a text view on the bottom of the screen that has a very small functionality to it. A short snippet of code in the MainActivty class looks through the Hourly forecast and tests for certain weather conditions to show a warning for. The purpose of including the warning message was to resolve the case that the user skips viewing the hourly forecast and goes straight to looking at the Apparel tab for clothing suggestions. There is only a warning message when there is a possibility of rain, snow, or both during the next 16 hours. These were the only condition cases considered because it was decided that there were no other weather conditions that people would really need to especially prepare for. Huge weather phenomenons like tornados, hurricanes and such do require attention too but they are beyond the scope of what the application can help with so it was decided to leave them out of the warning message.
     
##Vacation
The user can input a vacation location along with the intended departure and return dates. All fields must be filled in or a message will appear prompting the user to do so. Afterwards when the “Submit” button is pressed the VacationDataActivity appears.

- Time Frame
    - With the provided departure and return dates the length of the vacation is calculated into number of days through the CalculateDuration() method.
    - The number of days is then passed into the CalculateTimeFrame() method to determine the length of a time frame to be looked up. The length of the time frames are broken into every day, week, or month if the vacation duration is between 1 and 7 days, 8 and 35 days, or greater than 35 days respectively. This is done to prevent over usage of API calls to our weather service provider. 
    - The CreateTimeFrames() method is called to create the starting date and ending date of each time frames within the departure and return dates. Each time frame is then passed along with the same vacation location that was inputted at the beginning to Weather Underground’s planner service.
    - Each time frame is then displayed with the statistics of certain weather conditions occurring during that time.

- Clothing Suggestion
    - Next to each time frame there is a “Suggestion” button when clicked provides a list of suggested clothing to prepare for that time frame of the vacation.
    - The main list of clothing at the top is determined by the temperature range that has the highest percentage of occurrence.
    - Under that main clothing suggestion list there is another secondary list which recommends a separate list of clothing that the user should consider bringing. This list is determined by taking into account all the other temperature range’s chance of occurrence. If any of the other temperature ranges has a chance of 30% or higher of occurring then clothing for that range will be added into this secondary list for the user to consider.

- Weather Underground Planner
    - Weather Underground’s Planner service takes in a location and a starting and ending date and provides statistical percentages of the weather condition between those dates based on historical conditions.
    - Only statistics for the temperature ranges and weather condition of snow or rain are displayed to the user.

## Road Trip Functionality

- Layout
  - The layout for this tab can be found in the ScrollView with the field “android:id=”@+id/layout4”. The layout consists of a ScrollView containing a RelativeLayout, which contains a RelativeLayout, a LinearLayout, and a RelativeLayout. The first RelativeLayout contains the MapFragment, TextViews for the title and contents for the overall suggested clothings for the road trip, and a TextView for the title of the interval details section. The linear layout is initially empty, but will become populated with TextViews with details for each interval upon a successful query. The second RelativeLayout contains the PlaceAutocompleteFragments for the starting and ending locations, as well as text views for their respective section headers, and also a button to call the PlaceMarkers() method upon its reception of a click.
  
- Google Maps
  - The map functionality is powered by the Google Maps Android API. The map fragment in the road trip tab is set up to automatically focus and zoom in on your current location upon start-up. The layout for each interval is also defined upon start-up in a setInfoWindowAdapter() method. The code for the initialization of the map fragment can be found in the onMapReady() method.
  
- Starting and Ending Locations
  - The starting and ending location fields are powered by the Google Places API for Android. They are of type PlaceAutocompleteFragment, and defined as startingLocation and endingLocation respectively in the onStart() method. The listeners for these fields can also be found in the onStart() method. The listeners are currently defined to instantiate startingCoordinates and endingCoordinates with new LatLng objects based on the place selected.
  
- Let's Go Button
  - The Let’s Go button calls the placeMarkers() method when it is clicked. If the user has not picked both a starting and ending location, then the placeMarkers() method will simply reset startingCoordinates and endingCoordinates to null, reset both PlaceAutocompleteFragments to blank, and prompt the user to enter both a starting and ending location before pressing the Let’s Go button. Otherwise, the placeMarkers() method will call getRouteBetweenPoints() and adjust the zoom and camera bounds based on the LatLng objects of startingCoordinates and endingCoordinates.

    - getRouteBetweenPoints()
      - The getRouteBetweenPoints() method creates a new GoogleDirectionsAST object with its argument LatLng objects. With the result of the GoogleDirectionsAST object, it places the polyline for the queried route. It will also determine the number of intervals that should be on the map based on the distance of the queried route, and calculate the interval coordinates that weather should be displayed for. The getIntervalInformation() method is then called with the aforementioned interval coordinates as its arguments. If an exception is thrown, the method will end and the end-user will be notified that the query attempt has failed.
      
        - GoogleDirectionsAST
          - The GoogleDirectionsAST class is an asynchronous task powered by the Google Maps Directions API. This class is run in the background in order to get the JSON object for the route between the input LatLng objects. The code for this class can be found in the GoogleDirectionsAST.java file in the AsyncTask folder.

    - getIntervalInformation()
      - The getIntervalInformation() method creates a new IntervalInformationAST object with its argument LatLng objects. With the result of the IntervalInformationAST objects, it displays the clothing suggestions for the overall route, as well as the clothing suggestions for each interval in the Road Trip Tab. The getIntervalInformation() method also uses the results of the IntervalInformationAST in order to display the markers for each interval on the mapFragment. If an exception is thrown, the method will end and the end-user will be notified that the query attempt has failed.
        
        - IntervalInformationAST
          - The IntervalInformationAST class is an asynchronous task that encapsulates all events responsible for retrieving information for each interval that is passed in as an argument. For each interval that is passed in, this class will create a GoogleDistanceAST object, a MapsForecastAST object and a GoogleTimeZoneAST object in order to get the Weather, ETA, and Time Zone information for each interval. The asynchronous task will create markers for each of the intervals passed in with the information from the created asynchronous tasks, and return a MapInformation object containing the created objects and the relevant information to be used in the Text Views in the road trip tab. The code for this class can be found in the IntervalInformationAST.java file in the AsyncTask folder.
          
            - GoogleDistanceAST
              - The GoogleDistanceAST class is an asynchronous task powered by the Google Maps Distance Matrix API. This class is run in the background in order to get the JSON object for the argument list of intervals. It will return a distanceMatrixObject for the argument list of intervals. The code for this class can be found in the GoogleDistanceAST.java file in the AsyncTask folder.
              
            - MapsForecastAST
              - The MapsForecastAST class is an asynchronous task powered by the hourly 10 day forecast feature of the Weatherunderground API. This class is run in the background in order to read the JSON object for the argument LatLng object. It will return an HourlyItem with the temperature, condition, and icon url for the argument LatLng object. The code for this class can be found in the MapsForecastAST.java file in the AsyncTask folder.
              
            - GoogleTimeZoneAST
              - The TimeZoneAST class is an asynchronous task powered by the Google Maps Time Zone API. This class is run in the background in order to get the Time Zone for the argument LatLng object. It will return a timeZoneObject for the argument LatLng object. The code for this class can be found in the GoogleTimeZoneAST.java file in the AsyncTask folder.
