package controller.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;

public class GoodsDeleteController {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		String goodsImages = dao.getImages(goodsNum);
		String [] fileImages = goodsImages.split("`");
		if(fileImages.length >= 1) {
			String path = "goods/upload";
			String realPath = request.getServletContext().getRealPath(path);
			File file = null;
			for(String fileName : fileImages) {
				file = new File(realPath + "/" + fileName);
				if(file.exists()) file.delete();
				
			}
		}
		dao.goodsDelete(goodsNum);
	}
}
