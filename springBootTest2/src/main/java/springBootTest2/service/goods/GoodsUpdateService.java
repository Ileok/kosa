package springBootTest2.service.goods;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.GoodsCommand;
import springBootTest2.domain.GoodsDTO;
import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(GoodsCommand goodsCommand, HttpServletRequest request) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsDate(goodsCommand.getGoodsDate());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsQty(goodsCommand.getGoodsQty());
		dto.setGoodsCompany(goodsCommand.getGoodsCompany());

		String originalTotal = null;
		String storeTotal = null;
		String fileSizeTotal = null;

		String[] fileNames = null;
		if (dto.getGoodsImage() != null) {
			fileNames = dto.getGoodsImage().split("`");
		}

		// 파일 정보를 입력하기 위한 변수
		String fileDir = "/view/goods";
		String filePath = request.getServletContext().getRealPath(fileDir);

		if (dto.getGoodsImage() != null) {
			storeTotal = dto.getGoodsImage();
		} else {
			storeTotal = "";
		}

		if (!goodsCommand.getGoodsImage()[0].getOriginalFilename().isEmpty()) {
			for (MultipartFile mf : goodsCommand.getGoodsImage()) {
				String original = mf.getOriginalFilename();
				String extension = original.substring(original.lastIndexOf("."));
				// 중복 파일명이 있을 때를 대비하여 유일한 이름의 파일명을 생성.
				String store = UUID.randomUUID().toString().replace("-", "");
				String storeFileName = store + extension;

				storeTotal += storeFileName + "`";
				File file = new File(filePath + "/" + storeFileName);
				try {
					mf.transferTo(file); // 파일을 저장
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		dto.setGoodsImage(storeTotal);

		Integer i = goodsMapper.goodsUpdate(dto);
		// update가 정상적으로 실행이 되었다면 파일 삭제
		if (!goodsCommand.getGoodsImage()[0].getOriginalFilename().isEmpty()) {
			if (i > 0) {
				File file = null;
				try {
					for (String fileName : fileNames) {
						file = new File(fileDir + "/" + fileName);
						if (file.exists())
							file.delete();
					}
				} catch (Exception e) {
				}
			}
		}
		System.out.println(i + "개 행이 수정되었습니다.");
	}
}
