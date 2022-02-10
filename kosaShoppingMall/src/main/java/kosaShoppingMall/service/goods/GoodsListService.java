package kosaShoppingMall.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Component
@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(Model model) {
		List<GoodsDTO> list = goodsMapper.selectAll();
		model.addAttribute("list", list);
	}
	
}
