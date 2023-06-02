#!/bin/bash

# Call the /api/movie endpoint
curl -X GET "http://localhost:8080/api/movie" -H "accept: */*" -H "Content-Type: application/json" -d "{\"startDate\":\"2023-07-25T10:00:00.000\",\"endDate\":\"2023-07-25T22:01:00.000\"}"

# Call the /api/movie/reservation endpoint to check availability
curl -X GET "http://localhost:8080/api/movie/reservation" -H "accept: */*" -H "Content-Type: application/json" -d "{\"startTime\": \"2023-07-25T11:15:00.000+00:00\",\"endTime\":\"2023-07-25T13:15:00.000+00:00\",\"title\":\"Inception\"}"

# Call the /api/movie/reservation endpoint to make a reservation
curl -X POST "http://localhost:8080/api/movie/reservation" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\":\"Adam\", \"lastName\":\"Kolo-Kolo\", \"sessionId\":3, \"seats\":[ { \"seat\": { \"idSeat\": 19, \"seatNumber\": 3, \"rowNumber\": 1 }, \"ticketType\":\"Adult\" }, { \"seat\": { \"idSeat\": 20, \"seatNumber\": 4, \"rowNumber\": 1 }, \"ticketType\":\"Child\" }]}"

read -rp "Press any key to exit..."