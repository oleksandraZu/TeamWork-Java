package variant1_orderprocessing.domain;

public final class VatTax implements TaxPolicy {
    private static final double RATE = 0.20;

    @Override
    public double taxOn(double taxableAmount) {
        return taxableAmount * RATE;
    }
}
