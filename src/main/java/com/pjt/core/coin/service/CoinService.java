package com.pjt.core.coin.service;

import com.pjt.core.coin.repository.CoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {
    @Autowired
    CoinMapper coinMapper;

}
