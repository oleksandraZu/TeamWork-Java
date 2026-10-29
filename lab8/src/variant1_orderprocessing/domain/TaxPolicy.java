package variant1_orderprocessing.domain;

/** Protected Variations: ставка/алгоритм податку можуть змінюватись. */
public interface TaxPolicy {
    double taxOn(double taxableAmount);
}
