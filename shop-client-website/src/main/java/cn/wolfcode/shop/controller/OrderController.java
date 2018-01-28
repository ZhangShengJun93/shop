package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.service.IOrderReturnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private IOrderReturnService orderReturnService;

    @RequestMapping("return")
    public void orderReturn(Long orderId, String reason) {

        orderReturnService.insert(orderId,reason);

    }
}
