package ua.bukovyna.airport.notification;

/** Зміна за рейсом, про яку треба сповістити пасажирів. */
public record FlightChange(String flightNumber, String description) {
}
