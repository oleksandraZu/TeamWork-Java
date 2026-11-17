package ua.lab13.task1;

public record Passenger(String name,
                        String flightNumber,
                        FlightType flightType,
                        boolean boardingPassValid,
                        boolean passportValid,
                        boolean hasProhibitedItems,
                        double carryOnWeightKg,
                        boolean customsDeclarationOk) {
}
