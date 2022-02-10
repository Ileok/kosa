package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Component
@Service
public class GoodsRegistService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(GoodsCommand goodsCommand) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setDeliveryCost(goodsCommand.getDeliveryCost());
		Integer i = goodsMapper.goodsInsert(dto);
		System.out.println(i + "개 상품이 등록되었습니다.");
	}

}
