package kosaShoppingMall.command;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Data
public class MemberCommand {
	@Size(min = 10, max = 10, message = "번호를 입력하여 주세요.")
	String memNum;
	@Size(min = 4, max = 12, message = "아이디는 4자에서 12자사이로 입력하여 주세요!")
	String memId;
	@NotEmpty(message = "비밀번호를 입력해주세요!")
	@Size(min = 3, max = 12)
	String memPw;
	@NotBlank(message = "비밀번호확인을 입력해주세요!")
	@Size(min = 3, max = 12)
	String memPwCon;
	@NotBlank(message = "이름을 입력해주세요!")
	String memName;
	@NotBlank(message = "주소를 입력해주세요!")
	String memAddr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (message = "등록일을 입력하여 주세요.")
	Date memRegist;
	@NotBlank(message = "성별을 선택해주세요!")
	String gender;
	@NotBlank(message = "연락처를 입력해주세요!")
	String memPhone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (message = "생년월일을 입력하여 주세요.")
	Date memBirth;
	@Email(message = "형식에 맞지 않습니다.")
	@NotBlank(message = "이메일을 입력해주세요!")
	String memEmail;
	
	public boolean isMemPwEqualsMemPwCon() {
		return memPw.equals(memPwCon);
	}
}
