package com.bridgelabz.configuration;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.response.Response;
import com.bridgelabz.utility.EncryptUtil;
import com.bridgelabz.utility.MailUtil;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.bridgelabz")
public class ApplicationConfiguration {
	
	
	
	
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();		
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public Response getRespomse() {
    	return new Response();
    }
@Bean 
public EncryptUtil getEncryptUtil(){
	return new EncryptUtil();
   }





@Bean
public RestTemplate getreRestTemplate(RestTemplateBuilder restTemplateBuilder) {
	ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
	RestTemplate restTemplate = new RestTemplate(requestFactory);
	return restTemplate;
	//return restTemplateBuilder.build();
}

}