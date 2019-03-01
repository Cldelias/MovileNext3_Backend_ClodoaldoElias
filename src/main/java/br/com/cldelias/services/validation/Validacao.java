package br.com.cldelias.services.validation;

import java.util.List;

public interface Validacao<T, V> {

	public List<String> isValido(T objeto, V objetoDTO);
}
