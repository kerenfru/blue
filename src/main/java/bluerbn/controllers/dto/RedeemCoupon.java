package bluerbn.controllers.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RedeemCoupon {

    private int couponId;
    private double price;
}
