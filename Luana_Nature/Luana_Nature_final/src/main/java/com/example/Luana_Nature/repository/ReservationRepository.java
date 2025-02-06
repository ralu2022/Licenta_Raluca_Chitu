/**
 * Interface used to handle CRUD (create, read, update, delete) operations on the Reservation entity.
 *
 * <p>This interface extends the JPARepository interface, which provides built-in CRUD methods.</p>
 * <p>Also, it defines a custom query method used to count reservations based on accommodation type.</p>
 */

package com.example.Luana_Nature.repository;


import com.example.Luana_Nature.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r.accommodationType AS type, COUNT(r) AS count FROM Reservation r GROUP BY r.accommodationType")
    List<Map<String, Object>> countReservationsByType();



}