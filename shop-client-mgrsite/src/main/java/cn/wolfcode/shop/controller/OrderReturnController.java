package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.query.QueryObject;
import cn.wolfcode.shop.service.IOrderReturnService;
import cn.wolfcode.shop.util.JSONResult;
import cn.wolfcode.shop.util.PageResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class OrderReturnController {
	@Reference
	private IOrderReturnService orderReturnService;

	@RequestMapping("auditor")
	@ResponseBody
	public JSONResult auditor(Long id){
		JSONResult jsonResult = new JSONResult();
		try {
			orderReturnService.auditor(id);
		}catch (Exception e){
			jsonResult.setMessage("审核失败");
		}
		return jsonResult;
	}
	@RequestMapping("orderReturn")
	public String orderReturn(@ModelAttribute("qo") QueryObject qo, Model model){
		PageResult pageResult = orderReturnService.query(qo);
		model.addAttribute("pageResult",pageResult);
		return "order/orderReturn";
	}
}
