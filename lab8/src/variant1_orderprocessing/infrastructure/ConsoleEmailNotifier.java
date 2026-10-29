package variant1_orderprocessing.infrastructure;

import variant1_orderprocessing.application.OrderNotifier;
import variant1_orderprocessing.domain.Order;

public final class ConsoleEmailNotifier implements OrderNotifier {
    @Override
    public void notifyProcessed(Order order) {
        System.out.println("[Email] Повідомлення відправлено на " + order.customerEmail());
    }
}
