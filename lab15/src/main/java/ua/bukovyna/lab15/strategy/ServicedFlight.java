package ua.bukovyna.lab15.strategy;

/** Дані рейсу, за якими рахується вартість наземного обслуговування. */
public record ServicedFlight(String number, int weightTons, int passengers) {
}
