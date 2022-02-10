package kosaShoppingMall.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias(value = "goodsWhDTO")
public class GoodsWhDTO {
	Date whDate;
	String goodsNum;
	Integer whQty;
	Timestamp madeDate;
}