package gr.kariera.mindthecode.MyFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyFirstProjectApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<MyFirstProjectApplication> applicationClass = MyFirstProjectApplication.class;
}