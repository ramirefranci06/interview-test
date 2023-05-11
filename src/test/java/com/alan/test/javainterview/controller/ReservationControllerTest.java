package com.alan.test.javainterview.controller;

import com.alan.test.javainterview.domain.Reservation;
import com.alan.test.javainterview.domain.ReservationDate;
import com.alan.test.javainterview.exception.InvalidReservationDataException;
import com.alan.test.javainterview.service.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReservationControllerTest
{
    @Autowired
    ReservationController controller;

    @Autowired
    ReservationRepository repository;

    @Test
    public void saveReservationTest() throws InvalidReservationDataException
    {
        ReservationDate reservationDate = new ReservationDate(null, LocalDate.of(2023, 5, 15));
        Reservation inputReservation = new Reservation();
        inputReservation.setReservationDates(Arrays.asList(reservationDate));
        inputReservation.setClientFullName("Alan Ramirez");
        inputReservation.setRoomNumber(20001);

        ResponseEntity<Reservation> reservationResponseEntity = controller.saveReservation(inputReservation);
        assertEquals(HttpStatus.OK, reservationResponseEntity.getStatusCode());
        assertNotNull(reservationResponseEntity.getBody());
    }

    @Test
    public void saveReservationTest_InvalidDates()
    {
        Reservation inputReservation = new Reservation();
        inputReservation.setReservationDates(null);
        inputReservation.setClientFullName("Alan Ramirez");
        inputReservation.setRoomNumber(20001);

        InvalidReservationDataException exception = assertThrows(InvalidReservationDataException.class, () -> controller.saveReservation(inputReservation));
        assertEquals(exception.getMessage(), "Resservation dates are required");
    }

    @Test
    public void saveReservationTest_InvalidCustomerName()
    {
        Reservation inputReservation = new Reservation();
        inputReservation.setReservationDates(new ArrayList<>());
        inputReservation.setClientFullName(null);
        inputReservation.setRoomNumber(20001);

        InvalidReservationDataException exception = assertThrows(InvalidReservationDataException.class, () -> controller.saveReservation(inputReservation));
        assertEquals(exception.getMessage(), "Client Full Name is required");
    }

    @Test
    public void saveReservationTest_InvalidId()
    {
        Reservation inputReservation = new Reservation();
        inputReservation.setId(1);

        InvalidReservationDataException exception = assertThrows(InvalidReservationDataException.class, () -> controller.saveReservation(inputReservation));
        assertEquals(exception.getMessage(), "When creating a new reservation, ID must not be present, as it will be automatically generated");
    }

    //Tests for remaining endpoints and scenarios
    //
}
