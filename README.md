# Design a Travel Package Management System

A Travel Package management system allows travel agencies to maintain their travel packages, itineraries and passengers. All operations of a travel package are controlled by Travel Package management system.

This system involves defining a travel package including destinations and activities with capacity and pricing. 

## System Requirements

We will focus on the following set of requirements while designing the Travel Package Management System:

1. User can check the itinerary details comprising destinations and details of the activities available at each destination, like name, cost, capacity and description.
2. User can check the passenger list of the travel package including package name, passenger capacity, number of passengers currently enrolled and name and number of each passenger. 
3. User can fetch the details for a passenger including their name, passenger number, balance (if applicable), list of activity they have signed up for, including the destination at which the activity is taking place and the price the passenger paid for the activity.
4. User can fetch the activities with the available slots. 

## Class Diagram

Here are the main classes of our Travel Package Management System:

* **TravelPackage**: Represents a travel package offered by a travel agency. It contains destinations, passengers, and has methods to add destinations, add passengers, and print itinerary, passenger list, passenger details, and available activities.
* **Passenger**: Represents a passenger who signs up for activities within a travel package. It contains details such as name, passenger number, passenger type, balance, and a list of activities the passenger has signed up for. It also has a method to sign up for activities.
* **Destination**: Represents a destination within a travel package. It contains activities available at that destination and has a method to add activities.
* **Activity**: Represents an activity available at a destination. It contains details such as name, description, cost, and capacity. It also keeps track of the passengers signed up for the activity.
* **Address**: Represents the address of a passenger.
* **PassengerType**: Enumerates the types of passengers (Standard, Gold, Premium) with associated discounts.



