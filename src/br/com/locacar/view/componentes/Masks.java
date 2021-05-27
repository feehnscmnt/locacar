package br.com.locacar.view.componentes;

import javax.swing.text.*;
import java.text.*;

/**
 * Classe responsável pela aplicação das máscaras de texto e valores monetários em campos específicos!
 * @author Felipe Nascimento
 */
public class Masks {
	public static DefaultFormatterFactory mascaraTextoValorMonetario;
	private static MaskFormatter mascaraTexto;
	
	public static MaskFormatter mascaraPlaca() {
		try {
			mascaraTexto = new MaskFormatter("UUU-####");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraRg() {
		try {
			mascaraTexto = new MaskFormatter("##.###.###-#");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraCpf() {
		try {
			mascaraTexto = new MaskFormatter("###.###.###-##");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraCnpj() {
		try {
			mascaraTexto = new MaskFormatter("##.###.###/####-##");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraInscEstadual() {
		try {
			mascaraTexto = new MaskFormatter("###.###.###.###");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraTelCel() {
		try {
			mascaraTexto = new MaskFormatter("(##)#####-####");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static MaskFormatter mascaraCep() {
		try {
			mascaraTexto = new MaskFormatter("#####-###");
			mascaraTexto.setPlaceholderCharacter('_');
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mascaraTexto;
	}
	
	public static DefaultFormatterFactory mascaraValorMonetario() {
		DecimalFormat df = new DecimalFormat("#,###,###.00");
		NumberFormatter nf = new NumberFormatter(df);
		nf.setFormat(df);
		nf.setAllowsInvalid(false);
		return mascaraTextoValorMonetario = new DefaultFormatterFactory(nf);
	}
}