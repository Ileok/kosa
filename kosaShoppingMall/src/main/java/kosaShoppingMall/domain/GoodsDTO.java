package kosaShoppingMall.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value="goodsDTO")

public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	String goodsContent;

	Integer deliveryCost;
	Integer visitCount;
	
	String goodsImages;
	String goodsMain;
	
	GoodsWhDTO goodsWhDTO;
}