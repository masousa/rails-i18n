package br.com.teste.evoluum.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.teste.evoluum.exception.CVSConvertException;

/***
 * 
 * @author matheus
 *
 */
public class CVSConverter {
	
	 Logger logger = LoggerFactory.getLogger(CVSConverter.class);
	/***
	 * Classe responsável para converter uma lista de objetos e uma lista cvs.
	 * @param listObjects objetos a serem listados no cvs
	 * @param classOf classe de um determinado tipo que permite a impressão do cabeçalho
	 * @return
	 */
	public <T> String convertToCvs(List<T> listObjects, Class<T> classOf) throws CVSConvertException{
		List<Field> fields= Arrays.asList(classOf.getDeclaredFields());
		StringBuilder builder = new StringBuilder();
		List<String> ordemCabecalho = montarCabecalho(fields);
		format(builder,ordemCabecalho);
		
		for (T object : listObjects) {
			List<String> linha= buildObjectLine(ordemCabecalho,object,fields);
			format(builder, linha);
		}
		
		return builder.toString();
	}

	private void format(StringBuilder builder, List<String> itens) {
		builder.append(itens.toString().replace("[", "").replace("]", "").trim());
		builder.append("\n");
	}

	private <T> List<String> buildObjectLine(List<String> ordemCabecalho, T object,  List<Field> fields) throws CVSConvertException {
		List<String> itens = new ArrayList<>();
		try {
			for (String ordem : ordemCabecalho) {
				for (Field field : fields) {
					if (field.getName().equals(ordem)) {
						Object ob = field.get(object);
						ob = null==ob?"":ob;
						itens.add(ob.toString());
					}
				}

			}
			return itens;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e.getMessage(),e);
			throw new CVSConvertException();
		}
	}

	/***
	 * Método que tem como objetivo montar o cabeçalho resultante do cvs gerado
	 * @param builder
	 * @param fields
	 */
	private List<String> montarCabecalho(List<Field> fields) {
		List<String> parametros = new ArrayList<>();
		fields.forEach(field->{
			if(!Modifier.isStatic(field.getModifiers())) {
				parametros.add(field.getName());
			}
		});
		return parametros;
		
	}

}
