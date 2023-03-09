package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByAddressContainingIgnoreCase(String address, Pageable pageable);
}
