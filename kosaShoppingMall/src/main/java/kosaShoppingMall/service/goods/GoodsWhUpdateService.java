package kosaShoppingMall.service.goods;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsWhCommand;
import kosaShoppingMall.domain.GoodsWhDTO;
import kosaShoppingMall.mapper.GoodsMapper;
@Service
public class GoodsWhUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsWhCommand goodsWhCommand) {
		GoodsWhDTO dto = new GoodsWhDTO();
		dto.setGoodsNum(goodsWhCommand.getGoodsNum());
		dto.setWhDate(goodsWhCommand.getWhDate());
		dto.setWhQty(goodsWhCommand.getWhQty());
		dto.setMadeDate(Timestamp.valueOf(goodsWhCommand.getMadeDate()));
		goodsMapper.goodsWhUpdate(dto);
	}

}