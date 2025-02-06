/**
 * Interface used to handle CRUD (create, read, update, delete) operations on the Order entity.
 *
 * <p>This interface extends the JPARepository interface, which provides built-in CRUD methods.</p>
 */


package com.example.Luana_Nature.repository;

import com.example.Luana_Nature.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

