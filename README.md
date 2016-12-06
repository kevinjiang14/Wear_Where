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
- Install the application from the Google Play Store.
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

      - The getRouteBetweenPoints() method creates a new GoogleDirectionsAST with its argument LatLng objects. With the result of the GoogleDirectionsAST, it places the polyline for the queried route. It will also determine the number of intervals that should be on the map based on the distance of the queried route, and calculate the interval coordinates that weather should be displayed for. The getIntervalInformation() method is then called with the aforementioned interval coordinates as its arguments. If an exception is thrown, the method will end and the end-user will be notified that the query attempt has failed.

    - getIntervalInformation()

      - The getIntervalInformation() method creates a new IntervalInformationAST with its argument LatLng objects. With the result of the IntervalInformationAST, it displays the clothing suggestions for the overall route, as well as the clothing suggestions for each interval in the Road Trip Tab. The getIntervalInformation() method also uses the results of the IntervalInformationAST in order to display the markers for each interval on the mapFragment. If an exception is thrown, the method will end and the end-user will be notified that the query attempt has failed.
    
