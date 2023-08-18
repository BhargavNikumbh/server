package com.serenecandles.server.service;

import com.serenecandles.server.model.OrderDetail;
import com.serenecandles.server.model.OrderInput;
import java.sql.Date;

import java.util.List;

public interface OrderDetailService {
    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout);

    List<OrderDetail> getOrderDetails();

    List<OrderDetail> getAllOrderDetailsByDate(Date start, Date end);
    List<OrderDetail> getAllOrderDetails(int pageNumber, String searchKey);
    public long getTotalSales();
    Double getTotalRevenue();
    public long getTotalUsers();
    public void markOrderAsDelivered(Long orderId);
}
