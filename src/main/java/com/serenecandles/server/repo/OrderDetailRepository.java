package com.serenecandles.server.repo;

import com.serenecandles.server.model.OrderDetail;
import com.serenecandles.server.model.User;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    public List<OrderDetail> findByUser(User user);
    public List<OrderDetail> findByOrderDate(java.sql.Date date);
    public List<OrderDetail> findByOrderDateBetween(Date start, Date end);
    public List<OrderDetail> findByOrderFullNameContainingIgnoreCaseOrOrderEmailAddressContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
    List<OrderDetail> findAll(Pageable pageable);
    @Query(value = "SELECT sum(order_amount) FROM order_detail;", nativeQuery = true)
    public Double sumQuantities();
    @Query(value = "select count(user_role.user_role_id) from user_role where user_role.role_role_id=\"2\";", nativeQuery = true)
    public long getTotalUsers();

    public List<OrderDetail> findByOrderStatus(Pageable pageable, String status);
}
