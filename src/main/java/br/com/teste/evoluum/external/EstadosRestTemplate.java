package br.com.teste.evoluum.external;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.teste.evoluum.external.domain.ExternalEstados;
import br.com.teste.evoluum.external.domain.ExternalMunicipios;
import br.com.teste.evoluum.external.domain.ListaEstados;

@Component
public class EstadosRestTemplate {
	Logger logger = LoggerFactory.getLogger(EstadosRestTemplate.class);
	private final RestTemplate restTemplate;
	
	@Value("${app.evoluum.estados.endpoint.geral}")
    private String address;

    @Value("${app.evoluum.estados.endpoint.estados}")
    private String estados;
    
    @Value("${app.evoluum.estados.endpoint.municipios}")
    private String cidades;
	
	@Autowired
    public EstadosRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	@HystrixCommand(fallbackMethod = "getDefaultEstado")
	public List<ExternalEstados> getEstados() {
		
	    String uri = address + estados;
	    return getListOfResource(uri,ExternalEstados[].class);
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultCidade")
	public List<ExternalMunicipios> getCidades(int codigoEstado){
		String uri = address + MessageFormat.format(cidades, codigoEstado);
		
		return getListOfResource(uri,ExternalMunicipios[].class);
	}
	
	public List<ExternalEstados> getDefaultEstado() {
	    return new ListaEstados().getEstados();
	}
	
	public List<ExternalMunicipios> getDefaultCidade(int codigoEstado) {
	    return Lists.newArrayList();
	}
	
	private <T> List<T> getListOfResource(String url, Class<T[]> class1){
		 HttpHeaders requestHeaders = new HttpHeaders();
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", requestHeaders);
		    requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
		    ResponseEntity<String> st = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		    logger.debug(st.getBody());
		    Gson gson = new Gson();
		    return Arrays.asList(gson.fromJson(st.getBody(),  class1));
		   
	}

}
