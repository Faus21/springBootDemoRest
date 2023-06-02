package com.example.demo.templates;

public class TicketTypeSeatRequest {

    private SeatRequest seat;

    private TicketType ticketType;

    public enum TicketType{
        Adult("Adult"),
        Student ("Student"),
        Child ("Child");

        TicketType(String str) {
        }

    }

    public TicketTypeSeatRequest(SeatRequest seat, TicketType ticketType) {
        this.seat = seat;
        this.ticketType = ticketType;
    }

    public SeatRequest getSeat() {
        return seat;
    }

    public void setSeat(SeatRequest seat) {
        this.seat = seat;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
