package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;

@ServletComponentScan	// 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}


	// appication.properties에 설정 값을 넣어주면 스프링 부트가 자동으로 해준다.
	// @Bean
	// ViewResolver internalResourceViewResolver(){
	// 	return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	// }

	// @Bean
	// SpringMemberFormControllerV1 springMemberFormControllerV1(){
	// 	return new SpringMemberFormControllerV1();
	// }

}
