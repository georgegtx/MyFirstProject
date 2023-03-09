package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}
