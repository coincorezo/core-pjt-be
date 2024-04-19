package com.pjt.core.common.storage;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper {

    void insertBoardImage(Storage storage);

    void insertProfileImage(Storage storage);

    void insertEmotionImage(Storage storage);

}
