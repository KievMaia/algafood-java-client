package com.algaworks.algafood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.ClientApiException;
import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.model.input.CidadeInput;
import com.algaworks.algafood.client.model.input.CozinhaInput;
import com.algaworks.algafood.client.model.input.EnderecoInput;
import com.algaworks.algafood.client.model.input.RestauranteInput;

public class AdicionarRestauranteMain {

	public static void main(String[] args) {
		try {
			var restTemplate = new RestTemplate();
			
			var restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");
			
			var restauranteInput = RestauranteInput.builder()
			.nome("Bar da sede")
			.taxaFrete(new BigDecimal("10.00"))
			.cozinha(new CozinhaInput(1L))
			.endereco(EnderecoInput.builder()
					.cep("88113815")
					.logradouro("Rua Joice Cecília Correia")
					.numero("99")
					.complemento("N/D")
					.bairro("Areias")
					.cidade(new CidadeInput(1L))
					.build())
			.build();
				
			System.out.println(restauranteClient.adicionar(restauranteInput));
			
		} catch (ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
		        
		        e.getProblem().getObjects().stream()
		          .forEach(p -> System.out.println("- " + p.getUserMessage()));
			} else {
				System.out.println("Erro desconhecido!");
				e.printStackTrace();
			}
		}
	}
}
