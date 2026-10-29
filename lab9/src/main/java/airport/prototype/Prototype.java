package airport.prototype;

public interface Prototype<T> {
    /** Повертає повністю незалежну (глибоку) копію об'єкта. */
    T copy();
}
