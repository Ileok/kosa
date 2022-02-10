package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsWhCommand;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsWhDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsWhCommand goodsWhCommand) {
		goodsMapper.goodsWhDelete(goodsWhCommand);

	}

}