package com.gr.contacorrente.util;

/**
 * Classe utilitária para validações e operações comuns em vários tipos de objetos
 * @author gustavogrossmann
 *
 */
public class Util {
	
	/**
	 * Remove qualquer caracter não número da string
	 * 
	 * @param string
	 * @return
	 */
	public static String removeNaoNumericos(String string) {
		return string.replaceAll("[^\\d.]", "");
	}
	
	/**
	 * Valida um CPF
	 * 
	 * @param string
	 * @return
	 */
	public static boolean validaCPF(String cpf) {
		cpf = cpf.replaceAll("\\.", ""); // Tire os pontos
		cpf = cpf.replaceAll("-", ""); // Tire o traco
		try {
			if (cpf != null && cpf.length() == 11) {
				if ("01234567890".equals(cpf))
					return false;
				int d1, d2;
				int digito1, digito2, resto;
				int digitoCPF;
				String nDigResult;
				char aCpf[] = cpf.toCharArray();
				d1 = d2 = 0;
				digito1 = digito2 = resto = 0;
				for (int nCount = 1; nCount < aCpf.length - 1; nCount++) {
					digitoCPF = Integer.valueOf(String.valueOf(aCpf[nCount - 1])).intValue();
					d1 = d1 + (11 - nCount) * digitoCPF;
					d2 = d2 + (12 - nCount) * digitoCPF;
				}
				resto = (d1 % 11);
				if (resto < 2)
					digito1 = 0;
				else
					digito1 = 11 - resto;
				d2 += 2 * digito1;
				resto = (d2 % 11);
				if (resto < 2)
					digito2 = 0;
				else
					digito2 = 11 - resto;
				String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());
				nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
				boolean ret = nDigVerific.equals(nDigResult);
				if (ret) {
					String primeiroDigito = String.valueOf(aCpf[0]);
					boolean valid = false;
					for (int i = 1; i < aCpf.length; i++) {
						if (!String.valueOf(aCpf[i]).equals(primeiroDigito)) {
							valid = true;
							break;
						}
					}
					return valid;
				}
				return ret;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * Valida um CNPJ
	 * 
	 * @param string
	 * @return
	 */
	public static boolean validaCNPJ(String str_cnpj) {
		
		try {
			int soma = 0, dig;
			String cnpj_calc = str_cnpj.substring(0, 12);

			if (str_cnpj.length() != 14)
				return false;

			char[] chr_cnpj = str_cnpj.toCharArray();

			/* Primeira parte */
			for (int i = 0; i < 4; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);

			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

			/* Segunda parte */
			soma = 0;
			for (int i = 0; i < 5; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

			return str_cnpj.equals(cnpj_calc);

		} catch (Exception e) {
			return false;
		}
	}
}
