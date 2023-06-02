package com.example.demo.templates;

import com.example.demo.entities.Seat;


import java.util.ArrayList;
import java.util.List;


public class AvailableSeatsRequest {


    private Integer roomNumber;

    private Integer sessionId;
    private List<SeatRequest> availableSeats;
    
    public static AvailableSeatsRequest createRoomSeatRequest(List<Seat> seats, Integer sessionId){
        List<SeatRequest> seatRequests = new ArrayList<>();
        AvailableSeatsRequest availableSeatsRequest = new AvailableSeatsRequest();
        if (seats.size()>0){
            for (Seat seat :
                    seats) {
                seatRequests.add(new SeatRequest(seat.getIdSeat(), seat.getSeatNumber(), seat.getRowNumber()));
            }
            availableSeatsRequest.availableSeats = seatRequests;
            availableSeatsRequest.roomNumber = seats.get(0).getRoom().getIdRoom();
            availableSeatsRequest.sessionId = sessionId;
        }

        return availableSeatsRequest;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<SeatRequest> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatRequest> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
