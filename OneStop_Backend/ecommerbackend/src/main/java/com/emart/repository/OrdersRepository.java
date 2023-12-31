//package com.emart.repository;
//
//import com.emart.entities.Orders;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//@Transactional
//public interface OrdersRepository extends JpaRepository<Orders, Integer> {
//
////    @Query("SELECT o.order_Date, o.order_Total, c.quantity, c.total_Cost, c.customer.customer_Id, c.product.product_Id " +
////            "FROM Orders o " +
////            "JOIN o.cart c " +
////            "WHERE c.customer.customer_Id = :customer_Id")
////    List<Object[]> getOrdersByCustomerId(@Param("customer_Id") int customer_Id);
//
//	@Query(value = "SELECT o.order_date AS OrderDate, " +
//            "o.order_total AS OrderTotal, " +
//            "c.quantity AS quantity, " +
//            "c.total_cost AS totalCost, " +
//            "c.customer_id AS customerId " +
//            "FROM Orders o " +
//            "JOIN cart c ON c.id = o.cart_id_fk " +
//            "WHERE c.customer_id = :customer_Id", nativeQuery = true)
//	
//List<Object[]> getOrdersByCustomerId(@Param("customer_Id") int customer_Id);
//
//}



package com.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emart.entities.Orders;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    // @Query("SELECT o.order_Date, o.order_Total, c.quantity, c.total_Cost,
    // c.customer.customer_Id, c.product.product_Id " +
    // "FROM Orders o " +
    // "JOIN o.cart c " +
    // "WHERE c.customer.customer_Id = :customer_Id")
    // List<Object[]> getOrdersByCustomerId(@Param("customer_Id") int customer_Id);

	@Query(value = "SELECT p.p_id AS productId, " +
            "p.p_image AS productImage, " +
            "p.p_name AS productName, " +
            "p.p_price AS productPrice, " +
            "c.customer_id AS customerId, " +
            "c.quantity, " +
            "c.total_cost AS totalCost, " +
            "c.id AS cartId, " +
            "o.order_id AS orderId, " +
            "o.order_date AS orderDate " +
            "FROM cart c " +
            "JOIN product p ON c.p_id = p.p_id " +
            "JOIN customer cu ON c.customer_id = cu.customer_id " +
            "JOIN orders o ON c.id = o.cart_id_fk " +
            "WHERE cu.customer_id = :customerId", nativeQuery = true)
	List<Object[]> getByCId(@Param("customerId") int customer_id);

}
