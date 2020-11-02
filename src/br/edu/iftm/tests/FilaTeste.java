package br.edu.iftm.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import br.edu.iftm.threads.Caixa;
import br.edu.iftm.threads.Thread_pessoa;


public class FilaTeste {

	public static void main(String[] args) {
		// Iniciando a caixa com um valor determinado
		Caixa cx = new Caixa(3500);
		int operacoes,valor,nome;
		
		//Abrindo arquivo de texto com os nomes aleatorios
		File txt = new File("./nomes/nomes.txt");		
		Scanner leitor = null ;
		try {
			leitor = new Scanner(txt);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//Criando um Vetor com os Nomes aleatorios
		ArrayList<String> nomes = new ArrayList<String>() ;
		while(leitor.hasNextLine()){
		    nomes.add(leitor.nextLine());
		}
		String[] simpleArray = nomes.toArray(new String[]{});
	
		ArrayList<Thread_pessoa> aThread = new ArrayList<Thread_pessoa>();
		Random gerador = new Random();
		
		/*Função em que seleciona pessoas aleatorias com operações aléatorias para realizarem onde : 1) sacar dinheiro do caixa
																									 2) depositar dinheiro do caixa
																									 
		 Os clientes tambem são gerados com os nomes aleatorios do vetor e com valores de dinheiro para suas operações.
		*/
		
		synchronized(cx){
			for(int i=0; i<10; i++){
				nome = gerador.nextInt(340);
				operacoes = gerador.nextInt(2)+1;
				valor = gerador.nextInt(1500)+500;
				Thread_pessoa threadA = new Thread_pessoa(simpleArray[nome],valor , operacoes, i+1, cx);
				aThread.add(threadA);
			}
		}
		
		//Colocando as Threads em uma fila para inicialas
		try {
			for(int i = 0; i<10; i++){
				aThread.get(i).getThread().join();
			}
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		//Exibe todas as operações realizadas pelos clientes no caixa
		System.out.println("\n\nImprimindo o extrato do caixa:");
		System.out.println(cx.getExtrato());
		System.out.println("Total no caixa:" + cx.getValorTotal());
	}

}
