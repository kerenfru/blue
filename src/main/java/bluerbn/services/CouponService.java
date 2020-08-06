package bluerbn.services;

import bluerbn.controllers.dto.RedeemCoupon;
import bluerbn.models.FinalPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponService {

    private final CacheService cacheService;
    private List<Integer> discounts = Arrays.asList(10, 50, 60);

    public FinalPrice redeem(RedeemCoupon redeemCoupon) {

        String key = getKey(redeemCoupon);
        Optional<Object> value = cacheService.get(key);
        if (value.isPresent()) {
            return (FinalPrice)value.get();
        }

        FinalPrice price = null;
        if (isCouponValid(redeemCoupon.getCouponId())) {
            price = FinalPrice.builder().validCoupon(true).finalPrice(getDiscountedPrice(redeemCoupon.getPrice())).build();
        } else {
            price = FinalPrice.builder().validCoupon(false).finalPrice(redeemCoupon.getPrice()).build();
        }

        cacheService.add(getKey(redeemCoupon), price);
        return price;
    }

    private boolean isCouponValid(int couponId) {
        return couponId > 0;
    }

    private double getDiscountedPrice(double price) {
        int idx = new Random().nextInt(discounts.size());
        return price * (100 - discounts.get(idx)) / 100;
    }

    private String getKey(RedeemCoupon redeemCoupon) {
        return String.format("COUPON;%s", redeemCoupon.toString());
    }

}
