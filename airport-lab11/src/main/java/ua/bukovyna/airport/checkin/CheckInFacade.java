package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Facade: уся послідовність реєстрації в одному місці. */
public class CheckInFacade {
    private final DocumentCheckService documents;
    private final SeatingService seating;
    private final BaggageService baggage;
    private final BoardingPassPrinter printer;
    private final BorderServiceNotifier border;

    public CheckInFacade(DocumentCheckService documents, SeatingService seating, BaggageService baggage,
                         BoardingPassPrinter printer, BorderServiceNotifier border) {
        this.documents = documents;
        this.seating = seating;
        this.baggage = baggage;
        this.printer = printer;
        this.border = border;
    }

    /** Реєструє пасажира на рейс і повертає призначене місце. */
    public String checkIn(Passenger passenger, String flightNumber) {
        Log.log("CheckInFacade", "Початок реєстрації пасажира " + passenger.name() + " на рейс " + flightNumber);
        documents.verify(passenger);
        String seat = seating.assignSeat(passenger, flightNumber);
        baggage.acceptBaggage(passenger);
        printer.print(passenger, flightNumber, seat);
        border.notifyBorderService(passenger, flightNumber);
        Log.log("CheckInFacade", "Реєстрацію пасажира " + passenger.name() + " завершено");
        return seat;
    }
}
