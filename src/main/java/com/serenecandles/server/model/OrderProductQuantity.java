package com.serenecandles.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductQuantity {
    private Long productId;
    private int quantity;
}
