package com.pjt.core.common.category.repository;

import com.pjt.core.common.category.entity.CategoryCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCoinRepository extends JpaRepository<CategoryCoin, String> {
}
