package kosaShoppingMall.service.goods;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.GoodsWhDTO;
import kosaShoppingMall.domain.GoodsWhGoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;
@Service
public class GoodsWhDetailService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, String whDate, Model model) {
		GoodsWhDTO wdto = new GoodsWhDTO();
		wdto.setGoodsNum(goodsNum);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			wdto.setWhDate(sdf.parse(whDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		GoodsWhGoodsDTO dto = goodsMapper.whDetail(wdto);
		model.addAttribute("goodsWhGoodsCommand", dto);		
	}

}