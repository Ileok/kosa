package kosaShoppingMall;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{
	// html이나 jsp문서에서 view 밑에 있는 파일을 불러올 때 404 오류 나는 거 방지
	@Override
	public void addResourceHandlers(
			ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("/view")
		.setCachePeriod(14400);
	}
	
}
