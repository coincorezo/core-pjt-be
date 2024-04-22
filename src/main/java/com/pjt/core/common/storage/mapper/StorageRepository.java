package com.pjt.core.common.storage.mapper;

import com.pjt.core.common.storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {

}
