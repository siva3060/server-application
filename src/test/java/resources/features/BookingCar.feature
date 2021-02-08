Feature: A Driver Can Book a Avaliable Car
  Scenario: A Driver with ID 1  makes a booking request for a car with carID 2
    When driver with driverId send request select endpoint with carId
    Then driver should get a response with status code 200