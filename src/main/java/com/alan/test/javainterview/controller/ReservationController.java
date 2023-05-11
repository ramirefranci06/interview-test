package com.alan.test.javainterview.controller;

import com.alan.test.javainterview.domain.Reservation;
import com.alan.test.javainterview.exception.InvalidReservationDataException;
import com.alan.test.javainterview.exception.ReservationNotFoundExceptionException;
import com.alan.test.javainterview.service.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.alan.test.javainterview.utils.ReservationValidator.validateForCreate;
import static com.alan.test.javainterview.utils.ReservationValidator.validateForUpdate;

@RestController
public class ReservationController
{
    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation)
        throws InvalidReservationDataException
    {
        validateForCreate(reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    @PutMapping("/reservations")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation)
        throws ReservationNotFoundExceptionException, InvalidReservationDataException
    {
        validateForUpdate(reservation);
        Optional<Reservation> reservationInDb = reservationRepository.findById(reservation.getId());
        if (reservationInDb.isPresent())
        {
            Reservation updatedReservation = reservationRepository.save(reservation);
            return ResponseEntity.ok(updatedReservation);
        }
        else
        {
            throw new ReservationNotFoundExceptionException("reservation ID " + reservation.getId() + " is not present in DB");
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations()
    {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }
}
