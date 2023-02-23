# Platform Science Challenge
# Description
Write and android app using a provided json file that displays a list of drivers, each driver has a destination based on an algorithm.

# App Overview
The app displays a list of drivers with a placeholder icon and the name of the driver, after clicking on a driver, a detail screen loads with the assigned destination and displays a placeholder animation.

The challenge was built using Jetpack Compose to display the UI and a Room database to store the data. This allows the app to work offline 
without the need of an internet connection.

Third party Libraries used:
Hilt (dependency injection)
Gson Converter (parse json file)
Lottie (animations)

# Minimum Requirements to run the app:
* Compile SDK 33
* Min SDK 26
* Real device or emulator
* The app was build using: Android Studio Electric Eel | 2022.1.1

# App structure
* Cache: Room database and Entities
* Datasource: UseCases and Repositories to load data
* Feature/drivers: UI elements to display list of drivers
* Feature/shipments: UI elements to display detail screen with the shipment address assigned to a driver
* Domain: Algorithm and objects to find the score and match drivers to shipments 
* Navigation: Uses compose navigation 
* Common: Classes that have common functionality in the app 

# App Architecture
The app uses 4 layers, the domain layer holds the models  


<img src="https://user-images.githubusercontent.com/6471872/220914884-d5e63651-6441-4bbf-8cc2-bb9c349cfbf4.jpg" width=50% height=50%>



