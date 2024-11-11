package com.nit.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.nit.binding.Passenger;
import com.nit.binding.Ticket;

@Service
public class MakeMyTripService {
	@Value("${irctc.endpoint.book_ticket}")
	private String IRCTC_URL;
	@Value("${irctc.endpoint.get_ticket}")
	private  String IRCTC_URL_GET;
	
	
	public Ticket getTicket(Integer id)
	{
		WebClient wc= WebClient.create();// get webclient instance
		
		Ticket ticket = wc.get()//represent http get request
						  .uri(IRCTC_URL_GET, id)// irctc getreuest url and id
						  .accept(MediaType.APPLICATION_JSON)// mediatype of client accept
						  .retrieve()// take response from response body
						  .bodyToMono(Ticket.class) // bind response body data to java object
						  .block();// make sync call
		
		
		if(ticket!=null)
		{
			return ticket;
		}
		return null;
	}
	
	
	public Ticket processBookingTicket(Passenger passenger)
	{
WebClient wc= WebClient.create();// get webclient instance
		
		Ticket ticket = wc.post()//represent http post request
						  .uri(IRCTC_URL)// irctc post_reuest url 
						  .body(BodyInserters.fromValue(passenger))
						  .header("Content-Type", "application/json")
						  .accept(MediaType.APPLICATION_JSON)// mediatype of client accept
						  .retrieve()// take response from response body
						  .bodyToMono(Ticket.class) // bind response body data to java object
						  .block();// make sync call
		
		
		if(ticket!=null)
		{
			return ticket;
		}
		return null;
	}
	
	

}
