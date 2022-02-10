package kosaShoppingMall.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.GoodsWhDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsWhListService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(Model model) {
		List<GoodsWhDTO> list = goodsMapper.whSelect();
		model.addAttribute("list", list);
	}

}