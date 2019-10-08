package br.com.teste.evoluum.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.evoluum.builder.CVSConverter;
import br.com.teste.evoluum.controller.domain.Message;
import br.com.teste.evoluum.dto.Localizacao;
import br.com.teste.evoluum.exception.CVSConvertException;
import br.com.teste.evoluum.service.LocalizacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/localizacao")
@Api(value="Localização")
public class LocalizacaoRestController {
	
	@Autowired
	LocalizacaoService localizacaoService;
	@Value("${app.evoluum.messages.request.error}")
	private String erroProcessamento;
	@Value("${app.evoluum.messages.request.cidades.error}")
	private String cidadeNaoEncontrada;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="Lista todos os estados cadastrados")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE,path="/estados")
	public ResponseEntity<List<Localizacao>>  getAllEstados(){
		
		return new ResponseEntity(localizacaoService.getEstados(), HttpStatus.OK);
	}
	
	@ApiOperation(value="Lista todos os estados cadastrados exibindo os mesmos em um arquivo csv")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE,path="/estados/csv")
	public ResponseEntity<?>  getAllEstadosCVS(HttpServletResponse response){
		try {
		List<Localizacao> localizacoes =localizacaoService.getEstados();
		CVSConverter cb = new CVSConverter();
		String custCsv = cb.convertToCvs(localizacoes, Localizacao.class);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=estados.csv;");
        ServletOutputStream os;
		
			os = response.getOutputStream();
			os.write(custCsv.getBytes(StandardCharsets.UTF_8));            
	        response.flushBuffer();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException | CVSConvertException e) {
			return new ResponseEntity<>(new Message(erroProcessamento),HttpStatus.BAD_REQUEST);
		}
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="A partir de um código de um estado, lista todos os municipios cadastrados deste Estado")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE,path="/{uf}/municipios")
	public ResponseEntity<List<Localizacao>>  getMunicipiosFromEstado(@PathVariable(name="uf") int uf){
		
		return new ResponseEntity(localizacaoService.getMunicipios(uf), HttpStatus.OK);
	}
	
	@ApiOperation(value="A partir de um código de um estado, lista todos os municipios cadastrados deste Estado. Exibindo os mesmos em um arquivo CSV")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE,path="/{uf}/municipios/csv")
	public ResponseEntity<?>  getAllMunicipiosFromCVS(HttpServletResponse response,@PathVariable(name="uf") int uf){
		try {
		List<Localizacao> localizacoes =localizacaoService.getMunicipios(uf);
		CVSConverter cb = new CVSConverter();
		String custCsv = cb.convertToCvs(localizacoes, Localizacao.class);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=municipio.csv;");
        ServletOutputStream os;
		
			os = response.getOutputStream();
			os.write(custCsv.getBytes(StandardCharsets.UTF_8));            
	        response.flushBuffer();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException | CVSConvertException e) {
			return new ResponseEntity<>(new Message(erroProcessamento),HttpStatus.BAD_REQUEST);
		}
        
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE,path="/municipio/{name}")
	public ResponseEntity  getIdMunicipiosFromName(@PathVariable(name="name") String name){
		Integer id = localizacaoService.getCidadeIdFromName(name);
		if(null!=id) {
			
			return new ResponseEntity(id, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message(cidadeNaoEncontrada),HttpStatus.BAD_REQUEST);
		}
	}

}
