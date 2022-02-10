package kosaShoppingMall.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.command.GoodsWhCommand;
import kosaShoppingMall.service.goods.GoodsDeleteService;
import kosaShoppingMall.service.goods.GoodsDelsService;
import kosaShoppingMall.service.goods.GoodsDetailService;
import kosaShoppingMall.service.goods.GoodsItemService;
import kosaShoppingMall.service.goods.GoodsListService;
import kosaShoppingMall.service.goods.GoodsModifyService;
import kosaShoppingMall.service.goods.GoodsNumberService;
import kosaShoppingMall.service.goods.GoodsRegistService;
import kosaShoppingMall.service.goods.GoodsSearchService;
import kosaShoppingMall.service.goods.GoodsUpdateService;
import kosaShoppingMall.service.goods.GoodsWhDeleteService;
import kosaShoppingMall.service.goods.GoodsWhDelsService;
import kosaShoppingMall.service.goods.GoodsWhDetailService;
import kosaShoppingMall.service.goods.GoodsWhListService;
import kosaShoppingMall.service.goods.GoodsWhModifyService;
import kosaShoppingMall.service.goods.GoodsWhService;
import kosaShoppingMall.service.goods.GoodsWhUpdateService;

@RequestMapping("goods")
@Controller
public class GoodsController {
	@Autowired
	GoodsNumberService goodsNumberService;
	@Autowired
	GoodsRegistService goodsRegistService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsModifyService goodsModifyService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@Autowired
	GoodsSearchService goodsSearchService;
	@Autowired
	GoodsItemService goodsItemService;
	@Autowired
	GoodsWhService goodsWhService;
	@Autowired
	GoodsWhListService goodsWhListService;
	@Autowired
	GoodsWhDetailService goodsWhDetailService;
	@Autowired
	GoodsWhModifyService goodsWhModifyService;
	@Autowired
	GoodsWhUpdateService goodsWhUpdateService; 
	@Autowired
	GoodsWhDeleteService goodsWhDeleteService;
	@Autowired
	GoodsWhDelsService goodsWhDelsService;
	@Autowired
	GoodsDelsService goodsDelsService;
	@RequestMapping(value="goodsDels", method = RequestMethod.POST)
	public String goodsDels(@RequestParam(value="delete") String [] deletes) {
		goodsDelsService.execute(deletes);
		return "redirect:goodsList";
	}
	@RequestMapping(value="goodsWhdels" , method = RequestMethod.POST)
	public String goodsWhdels(@RequestParam(value="delete") String [] deletes) {
		goodsWhDelsService.execute(deletes);
		return "redirect:goodsWhList";
	}

	@RequestMapping(value = "goodsWhDelete" , method = RequestMethod.GET)
	public String goodsWhDelete(GoodsWhCommand goodsWhCommand) {
		goodsWhDeleteService.execute(goodsWhCommand);
		return "redirect:goodsWhList";
	}


	@RequestMapping(value ="goodsWhModify" , method = RequestMethod.POST)
	public String goodsWhModify(GoodsWhCommand goodsWhCommand) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(goodsWhCommand.getWhDate());
		goodsWhUpdateService.execute(goodsWhCommand);
		return "redirect:goodsWhDetail?goodsNum="+goodsWhCommand.getGoodsNum()+
				"&whDate="+date;
	}

	@RequestMapping(value="goodsWhModify" , method = RequestMethod.GET)
	public String goodsWhModify(@RequestParam(value = "goodsNum") String goodsNum,
			@RequestParam(value="whDate") String whDate, Model model) {
		goodsWhModifyService.execute(goodsNum,whDate, model);
		return "thymeleaf/goods/goodsWhUpdate";
	}


	@RequestMapping("goodsWhDetail")
	public String goodsWhDetail(@RequestParam(value = "goodsNum") String goodsNum,
			@RequestParam(value="whDate") String whDate, Model model) {
		goodsWhDetailService.execute(goodsNum,whDate, model);
		return "thymeleaf/goods/goodsWhDetail";
	}


	@RequestMapping(value = "whRegist", method = RequestMethod.GET)
	public String whRegist1() {
		return "redirect:/";
	}

	@RequestMapping(value = "whRegist", method = RequestMethod.POST)
	public String whRegist(@Validated GoodsWhCommand goodsWhCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/goods/goodsWh";
		}
		goodsWhService.execute(goodsWhCommand);
		return "redirect:goodsWhList";
	}

	@RequestMapping(value = "goodsItem", method = RequestMethod.GET)
	public String goodsItem() {
		return "thymeleaf/goods/goodsItem";
	}

	@RequestMapping(value = "goodsItem", method = RequestMethod.POST)
	public String goodsItems(@RequestParam(value = "goodsName") String goodsName, Model model) {
		goodsItemService.execute(goodsName, model);
		return "thymeleaf/goods/goodsItem";
	}

	@RequestMapping("goodsWh")
	public String goodsWh(GoodsWhCommand goodsWhCommand) {
		goodsWhCommand.setWhDate(new Date());
		return "thymeleaf/goods/goodsWh";
	}

	@RequestMapping("goodsWhList")
	public String goodsWhList(Model model) {
		goodsWhListService.execute(model);
		return "thymeleaf/goods/goodsWhList";
	}

	@RequestMapping("goodsSearch")
	public String goodsSearch(@RequestParam(value = "goodsWord") String goodsWord, Model model) {
		goodsSearchService.execute(goodsWord, model);
		return "thymeleaf/goods/goodsList";
	}

	@RequestMapping(value = "goodsDelete/{goodsNum}", method = RequestMethod.GET)
	public String goodsDelete(@PathVariable(value = "goodsNum") String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:../goodsList";
	}

	@RequestMapping(value = "goodsUpdate", method = RequestMethod.POST)
	public String goodsUpdate(@Validated GoodsCommand goodsCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/goods/goodsUpdate";
		}
		goodsUpdateService.execute(goodsCommand);
		return "redirect:goodsDetail/" + goodsCommand.getGoodsNum();
	}

	@RequestMapping(value = "goodsModify", method = RequestMethod.GET)
	public String goodsNum(GoodsCommand goodsCommand, Model model) {
		goodsModifyService.execute(goodsCommand, model);
		return "thymeleaf/goods/goodsUpdate";
	}

	@RequestMapping(value = "goodsDetail/{goodsNum}", method = RequestMethod.GET)
	public String goodsDetail(@PathVariable(value = "goodsNum") String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsDetail";
	}

	@RequestMapping(value = "goodsRegist", method = RequestMethod.POST)
	public String goodsRegist(@Validated GoodsCommand goodsCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/goods/goodsForm";
		}
		goodsRegistService.execute(goodsCommand);
		return "redirect:goodsList";
	}

	@RequestMapping(value = "goodsRegist", method = RequestMethod.GET)
	public String goods(GoodsCommand goodsCommand) {
		goodsNumberService.execute(goodsCommand);
		return "thymeleaf/goods/goodsForm";
	}

	@RequestMapping("goodsList")
	public String goodsList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}

}