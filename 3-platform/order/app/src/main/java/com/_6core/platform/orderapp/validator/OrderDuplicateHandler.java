package com._6core.platform.orderapp.validator;

import com._6core.lib.java.domain.model.order.OrderV01;

public interface OrderDuplicateHandler {
    Boolean isDublicate(OrderV01 order);
}
