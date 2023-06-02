package com.example.demo.templates;

import java.util.ArrayList;
import java.util.List;

public class SeatRequest {

    private Integer idSeat;

    private Integer seatNumber;

    private Integer rowNumber;


    public SeatRequest(Integer idSeat, Integer seatNumber, Integer rowNumber) {
        this.idSeat = idSeat;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
    }

    public static List<SeatRequest> seatRequestFromTicketSeatRequest(List<TicketTypeSeatRequest> seats){
        List<SeatRequest> seatRequests = new ArrayList<>();
        for (TicketTypeSeatRequest t :
                seats) {
            seatRequests.add(t.getSeat());
        }
        return seatRequests;
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
}
