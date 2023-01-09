package com.algaworks.algafood.client;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.ClientApiException;
import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.model.RestauranteResumoModel;

public class ListagemRestaurantesMain {
	
	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");
			
			restauranteClient.listar().stream()
			.map(RestauranteResumoModel::toString)
			.forEach(System.out::println);
			
		} catch (ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem());
				System.out.println(e.getProblem().getUserMessage());
			} else {
				System.out.println("Erro desconhecido!");
				e.printStackTrace();
			}
		}
	}
}
