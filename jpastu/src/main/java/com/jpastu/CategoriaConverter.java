package com.jpastu;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.jpastu.model.Categoria;

@Converter(autoApply = true)
public class CategoriaConverter implements AttributeConverter<Categoria, String> {

	@Override
	public String convertToDatabaseColumn(Categoria category) {
		if (category == null) {
			return null;
		}
		return category.getCodigo();
	}

	@Override
	public Categoria convertToEntityAttribute(String codigo) {
		if (codigo == null) {
			return null;
		}

		return Stream.of(Categoria.values()).filter(c -> c.getCodigo().equals(codigo)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}