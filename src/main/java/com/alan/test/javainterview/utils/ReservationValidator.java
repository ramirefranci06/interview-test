package com.alan.test.javainterview.utils;

import com.alan.test.javainterview.domain.Reservation;
import com.alan.test.javainterview.exception.InvalidReservationDataException;

public class ReservationValidator
{
    public static void validateForCreate(Reservation reservation) throws InvalidReservationDataException
    {
        if (reservation.getId() != null)
        {
            throw new InvalidReservationDataException("When creating a new reservation, ID must not be present, as it will be automatically generated");
        }
        validateBasicInformation(reservation);
    }

    public static void validateForUpdate(Reservation reservation) throws InvalidReservationDataException
    {
        if (reservation.getId() == null)
        {
            throw new InvalidReservationDataException("Reservtion ID is required");
        }
        validateBasicInformation(reservation);
    }

    private static void validateBasicInformation(Reservation reservation) throws InvalidReservationDataException
    {
        if (reservation.getClientFullName() == null)
        {
            throw new InvalidReservationDataException("Client Full Name is required");
        }
        if (reservation.getRoomNumber() == null)
        {
            throw new InvalidReservationDataException("Room # is required");
        }
        if (reservation.getReservationDates() == null
            || reservation.getReservationDates().isEmpty())
        {
            throw new InvalidReservationDataException("Reservation dates are required");
        }
    }
}
