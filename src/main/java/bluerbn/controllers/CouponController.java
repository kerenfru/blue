package bluerbn.controllers;

import bluerbn.controllers.dto.RedeemCoupon;
import bluerbn.models.FinalPrice;
import bluerbn.services.CouponService;
import bluerbn.services.TicketsService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/flights/coupon")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/redeem")
    public FinalPrice redeem(
        @RequestBody RedeemCoupon redeemCoupon
    ) {
         return couponService.redeem(redeemCoupon);
    }
}
