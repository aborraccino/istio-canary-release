package com.worldpay.hpp.t20.istio.shopper.controller;

import com.worldpay.hpp.t20.istio.shopper.domain.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShopperController {

    Logger logger = LoggerFactory.getLogger(ShopperController.class);

    static Map<String, Shop> shops = new HashMap<>();

    static {
        Shop s1 = new Shop("WEDD321", "sport@shop.it", "07851123456", "Science Park, 270 Milton Rd, Milton, Cambridge CB4 0WE");
        Shop s2 = new Shop("OTDD422", "cat@shop.it", "07851123456", "Science Park, 270 Milton Rd, Milton, Cambridge CB4 0WE");
        Shop s3 = new Shop("AQSW165", "home@shop.it", "07851123456", "Science Park, 270 Milton Rd, Milton, Cambridge CB4 0WE");

        shops.put(s1.getId(), s1);
        shops.put(s2.getId(), s2);
        shops.put(s3.getId(), s3);

    }

    @GetMapping("/shops/{shopId}")
    public Shop getShopDetails(@PathVariable String shopId) {
        return shops.get(shopId);
    }

}
