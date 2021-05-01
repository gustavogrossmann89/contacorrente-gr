package com.gr.contacorrente.util;

import java.math.BigDecimal;

/**
 * Classe utilitária para validações e operações comuns matemáticas em vários tipos de objetos
 * @author gustavogrossmann
 *
 */
public class MathUtil {
	
	/**
	 * Verifica se o valor é maior que zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean maiorQueZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == 1;
	}
	
	/**
	 * Verifica se o valor é maior que zero ou igual a zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean maiorIgualZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == 1 || valor.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * Verifica se o valor é menor que zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean menorQueZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == -1;
	}
	
	/**
	 * Verifica se o valor é menor que zero ou igual a zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean menorIgualZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == -1 || valor.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * Verifica se o valor 1 é maior que o valor 2
	 * 
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public static boolean maior(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2) == 1;
	}
	
	/**
	 * Verifica se o valor 1 é igual ao valor 2
	 * 
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public static boolean igual(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2) == 0;
	}
	
	/**
	 * Verifica se o valor 1 é menor que o valor 2
	 * 
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public static boolean menor(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2) == -1;
	}
}
