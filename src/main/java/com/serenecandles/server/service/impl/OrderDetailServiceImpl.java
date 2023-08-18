package com.serenecandles.server.service.impl;

import com.serenecandles.server.config.JwtAuthenticationFilter;
import com.serenecandles.server.model.*;
import com.serenecandles.server.repo.CartRepository;
import com.serenecandles.server.repo.OrderDetailRepository;
import com.serenecandles.server.repo.ProductRepository;
import com.serenecandles.server.repo.UserRepository;
import com.serenecandles.server.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final static String ORDER_PLACED="Placed";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        for(OrderProductQuantity o : productQuantityList){
            Product product = productRepository.findById(o.getProductId()).get();
            String currentUser = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByUsername(currentUser);
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getOrderEmailAddress(),
                    product,
                    user,
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductPrice()*o.getQuantity(),
                    date
            );

            //Empty Cart
            if(!isSingleProductCheckout){
                List<Cart> carts = cartRepository.findByUser(user);
                carts.stream().forEach(x-> cartRepository.deleteById(x.getCartId()));
            }

            orderDetailRepository.save(orderDetail);
        }
    }

    public List<OrderDetail> getOrderDetails(){
        String username=JwtAuthenticationFilter.CURRENT_USER;
        return orderDetailRepository.findByUser(userRepository.findByUsername(username));
    }

    public List<OrderDetail> getAllOrderDetailsByDate(Date start, Date end){
            List<OrderDetail> orderDetails = new ArrayList<>();
            orderDetailRepository.findByOrderDateBetween(start, end).forEach(x->orderDetails.add(x));
            return orderDetails;
    }

    public List<OrderDetail> getAllOrderDetails(int pageNumber,String searchKey){
        Pageable pageable = PageRequest.of(pageNumber, 10);
            if(searchKey.equals("")){
                List<OrderDetail> orderDetails = new ArrayList<>();
                orderDetailRepository.findAll(pageable).forEach(x->orderDetails.add(x));
                return orderDetails;
            }else{
                List<OrderDetail> orderDetails = new ArrayList<>();
                orderDetailRepository.findByOrderFullNameContainingIgnoreCaseOrOrderEmailAddressContainingIgnoreCase(searchKey, searchKey, pageable).forEach(x->orderDetails.add(x));
                return orderDetails;
            }
    }

    public long getTotalSales(){
        return orderDetailRepository.count();
    }

    @Override
    public Double getTotalRevenue() {
        return orderDetailRepository.sumQuantities();
    }

    @Override
    public long getTotalUsers() {
        return orderDetailRepository.getTotalUsers();
    }

    @Override
    public void markOrderAsDelivered(Long orderId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).get();
        if(orderDetail!=null){
            orderDetail.setOrderStatus("Delivered");
            orderDetailRepository.save(orderDetail);
        }
    }


}
