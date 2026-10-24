package variant1_orderprocessing.application;

/** Controller (use case "Оформити замовлення"): лише координує, без I/O та обчислень. */
public final class PlaceOrderController {
    private final OrderFactory factory;
    private final OrderCompletion completion;

    public PlaceOrderController(OrderFactory factory, OrderCompletion completion) {
        this.factory = factory;
        this.completion = completion;
    }

    public OrderResult execute(PlaceOrderCommand command) {
        return completion.complete(factory.create(command));
    }
}
