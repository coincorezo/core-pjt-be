package com.pjt.core.common.storage.mapper;

import com.pjt.core.common.storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    void deleteByImgSaveNm(String filename);

    Optional<Storage> findByImgSaveNm(String filename);

}
