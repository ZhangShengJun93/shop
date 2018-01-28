package cn.wolfcode.shop.controller;


import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.query.OrderInfoQueryObject;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.service.IOrderProductPriceService;
import cn.wolfcode.shop.service.IOrderProductService;
import cn.wolfcode.shop.util.JSONResult;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.vo.OrderAndUserVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

	@Reference
	private IOrderInfoService orderInfoService;
	@Reference
	private IOrderProductService orderProductService;
	@Reference
	private IOrderProductPriceService orderProductPriceService;
	@RequestMapping("orderInfo")
	public String orderAction(@ModelAttribute("qo") OrderInfoQueryObject qo, Model model){
		PageResult pageResult = orderInfoService.query(qo);
		model.addAttribute("pageResult",pageResult);
		return "order/orderInfo";
	}

	@RequestMapping("orderInfoSend")
	public String orderInfoSend(Model model){
		List<OrderInfo> orderInfos = orderInfoService.selectAllSend();
		model.addAttribute("orderInfos",orderInfos);
		return "order/orderInfoSend";
	}
	@RequestMapping("orderItem")
	public String orderItem(Model model,Long id){
		OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(id);
		model.addAttribute("orderInfo",orderInfo);
		OrderProduct orderProduct = orderProductService.queryOrderProduct(id);
		model.addAttribute("orderProduct",orderProduct);
		OrderProductPrice opp = orderInfo.getOrderProductPrice();
		model.addAttribute("opp",opp);
		List<OrderAction> orderActions = orderInfo.getOrderActions();
		model.addAttribute("orderActions",orderActions);
		return "order/orderItem";
	}
	@RequestMapping("updateUserInfo")
	public String updateUserInfo(Model model,Long orderId){
		OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(orderId);
		User user = orderInfo.getUser();
		model.addAttribute("user",user);
		model.addAttribute("orderInfo",orderInfo);
		return "order/userItem";
	}
	@RequestMapping("userItemSubmit")
	@ResponseBody
	public JSONResult userItemSubmit(@ModelAttribute("vo") OrderAndUserVo vo){
		JSONResult jsonResult = new JSONResult();
		try {
			orderInfoService.userItemSubmit(vo);
		}catch (Exception e){
			e.printStackTrace();
			jsonResult.setMessage("保存失败");
		}
		return jsonResult;
	}
	@RequestMapping("changeOrderStatus")
	public String changeOrderStatus(String orderDesc,Byte status,Long orderId){
		orderInfoService.changeOrderStatus(orderDesc,status,orderId);
		return "redirect:orderItem?id="+orderId;
	}
	@RequestMapping("feeSubmit")
	@ResponseBody
	public JSONResult feeSubmit(OrderProductPrice orderProductPrice,Long orderId){
		JSONResult jsonResult = new JSONResult();
		orderProductPriceService.update(orderProductPrice,orderId);
		return jsonResult;
	}
	@RequestMapping("updateState")
	@ResponseBody
	public JSONResult updateState(Long id){
		JSONResult jsonResult = new JSONResult();
		try {
			orderInfoService.updateState(id);
		}catch (Exception e){
			jsonResult.setMessage("修改失败");
		}
		return jsonResult;
	}
}
