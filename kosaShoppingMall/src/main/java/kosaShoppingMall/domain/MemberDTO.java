package kosaShoppingMall.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import java.util.Date;

@Data
@Alias(value = "memDTO")
public class MemberDTO {
	String memNum;
	String memId;
	String memPw;
	String memName;
	String memAddr;
	Date memRegist;
	String gender;
	String memPhone;
	Date memBirth;
	String memOk;
	String memEmail;
}
