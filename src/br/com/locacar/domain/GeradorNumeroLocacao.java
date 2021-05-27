package br.com.locacar.domain;

import javax.swing.*;
import java.util.*;

/**
 * Classe responsável por gerar o número da locação!
 * @author Felipe Nascimento
 */
public class GeradorNumeroLocacao {
	public static void numeroLocacao(JTextField txtNumLocacao) {
		Random random = new Random();
		for (int i = 0; i <= 10000; i++) {
			txtNumLocacao.setText(String.valueOf("LOC" + random.nextInt(10000)));
		}
	}
}