package com.ecommerce;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.vo.GoodsDataInfo;
import com.ecommerce.vo.GoodsReportSalesInfo;


@SpringBootApplication
public class SpringTrainingApplication extends SpringBootServletInitializer{
	
	private static Logger logger = LoggerFactory.getLogger(SpringTrainingApplication.class);
	
	public static String SERVER_IP = null;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringTrainingApplication.class, args);		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(FilehandlingApplication.class);
		return builder.sources(SpringTrainingApplication.class);
	}
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
				try {
					String hostname = InetAddress.getLocalHost().getHostName();
					InetAddress[] addresses = InetAddress.getAllByName(hostname);
					SERVER_IP = addresses[0].getHostAddress();
				} catch (UnknownHostException e) {
					logger.error(e.getMessage(), e);
				}
			}
			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}
		};
	}
	
	
	
}
