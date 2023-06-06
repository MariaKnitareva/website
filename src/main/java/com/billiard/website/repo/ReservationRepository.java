package com.billiard.website.repo;

import com.billiard.website.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query(value = "SELECT r FROM Reservation r WHERE r.table_id = :needed_id AND (r.table_status = 'Забронировано' " +
                   "OR r.table_status = 'Открыто') AND NOT r.dt_from >= :end AND NOT r.dt_to <= :start")
    List<Reservation> findReservedTable(@Param("needed_id") int needed_id, @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);
    @Query(value = "SELECT r FROM Reservation r WHERE (r.table_status = 'Забронировано' OR r.table_status = 'Открыто') " +
            "AND NOT r.dt_from >= :end AND NOT r.dt_to <= :start")
    List<Reservation> findReservedTable(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    @Query(value = "SELECT r FROM Reservation r WHERE DATE(r.dt_from) = :date")
    List<Reservation> findTablesByDate(@Param("date") LocalDate date);
    @Query(value = "SELECT r FROM Reservation r WHERE r.table_status = :table_status AND DATE(r.dt_from) = :date")
    List<Reservation> findTablesByDateAndTS(@Param("date") LocalDate date, @Param("table_status") String table_status);
}
