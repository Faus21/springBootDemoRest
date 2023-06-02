package com.example.demo.templates;

import com.example.demo.entities.TicketType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReservationRequest {

    private String firstName;

    private String lastName;

    private Integer sessionId;

    private List<TicketTypeSeatRequest> seats;

    public Float countAmountToPay(List<TicketType> ticketTypes){
        Float toPay = 0.0f;
        Map<TicketTypeSeatRequest.TicketType, Float> price = listOfPricesToMap(ticketTypes);
        for (TicketTypeSeatRequest s :
                seats) {
            toPay += price.get(s.getTicketType());
        }
        return  toPay;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Map<TicketTypeSeatRequest.TicketType, Float> listOfPricesToMap(List<TicketType> ticketTypes){
        Map<TicketTypeSeatRequest.TicketType, Float> priceMap = new HashMap<>();

        for (TicketType t :
                ticketTypes) {
            priceMap.put(TicketTypeSeatRequest.TicketType.valueOf(t.getTitle()),t.getPrice());
        }

        return priceMap;
    }

    public void setFisrtName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TicketTypeSeatRequest> getSeats() {
        return seats;
    }

    public void setSeats(List<TicketTypeSeatRequest> seats) {
        this.seats = seats;
    }
}
