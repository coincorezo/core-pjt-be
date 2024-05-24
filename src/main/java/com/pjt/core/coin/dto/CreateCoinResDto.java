package com.pjt.core.coin.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateCoinResDto {

    @Schema(description = " 적립 & 차감 코인")
    private int coin;


}
