package com.pjt.core.example.repository;

import com.pjt.core.example.entity.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {
}
