package com.example.demo.controllers;

import com.example.demo.entities.Reservation;
import com.example.demo.entities.Session;
import com.example.demo.entities.TicketType;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.SeatRepository;
import com.example.demo.repositories.SessionRepository;
import com.example.demo.repositories.TicketTypeRepository;
import com.example.demo.templates.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CinemaRestController {

    private final SessionRepository sessionRepository;

    private final SeatRepository seatRepository;

    private final TicketTypeRepository ticketTypeRepository;

    private final ReservationRepository reservationRepository;

    public CinemaRestController(SessionRepository sessionRepository, SeatRepository seatRepository, TicketTypeRepository ticketTypeRepository, ReservationRepository reservationRepository) {
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.reservationRepository = reservationRepository;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    /*{
        "startDate":"2023-07-25T10:00:00.000",
        "endDate":"2023-07-25T22:01:00.000"
    }*/

    @GetMapping("/movie")
    public List<MovieRequest> getMovies(@RequestBody SimpleDate simpleDate){
        return MovieRequest.createListOfMovieRequests(sessionRepository.getFilms(simpleDate.getStartDate().plusHours(2),
                                            simpleDate.getEndDate().plusHours(2)));
    }

    /*{
         "startTime": "2023-07-25T11:15:00.000+00:00",
         "endTime": "2023-07-25T13:15:00.000+00:00",
         "title": "Inception"
    }*/
    @GetMapping("/movie/reservation")
    public AvailableSeatsRequest getSeats(@RequestBody MovieRequest movieRequest){
        Session session = sessionRepository.getSessionByTitleAndDate(movieRequest.getTitle(),
                movieRequest.getStartTime(),
                movieRequest.getEndTime());

        return AvailableSeatsRequest.createRoomSeatRequest(seatRepository.getAvailableSeats(session.getRoom().getIdRoom(),session.getIdSession()), session.getIdSession());
    }

    @PostMapping("/movie/reservation")
    public ReservationInfoResponse bookSeats(@RequestBody ReservationRequest reservationRequest){

        //Check length
        if (reservationRequest.getFirstName().length()<3 || reservationRequest.getLastName().length() <3){
            throw new BadRequestException("The length of name or surname is too small");
        }

        //Check how many seat are going to be reserved
        if (reservationRequest.getSeats().size()<1){
            throw new BadRequestException("You need to reserve at least 1 seat");
        }

        //Check capital letter for name and surname
        reservationRequest.setFirstName(Validator.capitalize(reservationRequest.getFirstName()));
        reservationRequest.setLastName(Validator.capilizeLastName(reservationRequest.getLastName()));

        //Find session for check reservation time
        Optional<Session> sessionOptional = sessionRepository.findById(reservationRequest.getSessionId());
        Session session = sessionOptional.get();

        //Check reservation time
        if (LocalDateTime.now().plusMinutes(15).compareTo(session.getStartTime().toLocalDateTime())>=0){
            throw new BadRequestException("Reservation time is expired");
        }

        //Check rows for single places between two already reserved places
        if (checkAllSeatsForGap(reservationRequest, session)) {
            throw new BadRequestException("There cannot be a single place left over in a row between two already reserved places");
        }

        //Save all reservations
        List<Reservation> reservations = new ArrayList<>();
        for (TicketTypeSeatRequest s :
                reservationRequest.getSeats()) {
            Reservation reservation = new Reservation();
            reservation.setFirstName(reservationRequest.getFirstName());
            reservation.setLastName(reservationRequest.getLastName());
            reservation.setSeat(seatRepository.findById(s.getSeat().getIdSeat()).get());
            reservation.setTicketType(ticketTypeRepository.findByType(s.getTicketType().name()));
            reservation.setSession(session);
            reservations.add(reservation);
        }
        reservationRepository.saveAll(reservations);

        //Count amount to pay
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        Float toPay = reservationRequest.countAmountToPay(ticketTypes);

        ReservationInfoResponse response = new ReservationInfoResponse(session.getStartTime(), toPay);

        return response;
    }

    public boolean checkAllSeatsForGap(ReservationRequest reservationRequest, Session session){
        List<RowModel> rowModels = RowModel.createRowsWithSeats(
                SeatRequest.seatRequestFromTicketSeatRequest(reservationRequest.getSeats()));
        for (int i = 0; i < rowModels.size(); i++) {
            Integer maxSeatNumberInRow = seatRepository.maxSeatNumberByRoomId(session.getRoom().getIdRoom(), rowModels.get(i).getRowId());

            String binaryAllSeatsInRow = Validator.createBinaryRow(seatRepository.getAvailableSeatsInRow(
                            session.getRoom().getIdRoom(),session.getIdSession(), reservationRequest.getSeats().get(i).getSeat().getRowNumber()),
                    maxSeatNumberInRow);
            String binarySeatsToReservation = Validator.createBinaryRowBySeatNumbers(rowModels.get(i).getSeats(),maxSeatNumberInRow);

            String rowWithBookedPlaces = Integer.toBinaryString(Integer.parseInt(binaryAllSeatsInRow,2)
                    ^ Integer.parseInt(binarySeatsToReservation, 2));

            if (Validator.checkPlaces(rowWithBookedPlaces))
                return true;
        }
        return false;
    }
}
