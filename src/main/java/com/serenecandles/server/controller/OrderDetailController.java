package com.serenecandles.server.controller;

import com.serenecandles.server.model.OrderDetail;
import com.serenecandles.server.model.OrderInput;
import com.serenecandles.server.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    @PreAuthorize("hasAuthority('NORMAL')")
    public void placeOrder(@PathVariable(name="isSingleProductCheckout") boolean isSingleProductCheckout,
                           @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput,isSingleProductCheckout);
    }

    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/getOrderDetails")
    public List<OrderDetail> getOrderDetails(){
        return orderDetailService.getOrderDetails();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/getAllOrderDetailsByDate"})
    public List<OrderDetail> getAllOrderDetailsByDate(@RequestParam(name = "start") Date start, @RequestParam(name="end") Date end){
        return orderDetailService.getAllOrderDetailsByDate(start, end);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/getAllOrderDetails"})
    public List<OrderDetail> getAllOrderDetails(
                                                @RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = "") String searchKey){
        return orderDetailService.getAllOrderDetails( pageNumber, searchKey);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/getTotalSales"})
    public long getTotalSales(){
        return orderDetailService.getTotalSales();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/getTotalRevenue"})
    public Double getTotalRevenue(){
        return orderDetailService.getTotalRevenue();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getTotalUsers")
    public long getTotalUsers(){
        return orderDetailService.getTotalUsers();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Long orderId){
        orderDetailService.markOrderAsDelivered(orderId);
    }

}
