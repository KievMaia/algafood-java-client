package com.algaworks.algafood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.model.RestauranteResumoModel;
import com.algaworks.algafood.client.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static String RESOURCE_PATH = "/restaurantes";

	private RestTemplate restTemplate;
	private String url = "http://localhost:8080";

	public List<RestauranteResumoModel> listar() {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);

			RestauranteResumoModel[] restaurantes = restTemplate.getForObject(resourceUri, RestauranteResumoModel[].class);

			return Arrays.asList(restaurantes);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
	public RestauranteResumoModel adicionar(RestauranteInput restauranteInput) {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
			
			RestauranteResumoModel restauranteModel = restTemplate.postForObject(resourceUri, restauranteInput, RestauranteResumoModel.class);
			
			return restauranteModel;
		} catch (HttpClientErrorException  e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
}
