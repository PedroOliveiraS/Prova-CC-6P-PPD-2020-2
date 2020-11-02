package br.edu.iftm.tests;

import java.util.ArrayList;

import br.edu.iftm.threads.Caixa;
import br.edu.iftm.threads.Thread_pessoa;

public class FilaTeste {

	public static void main(String[] args) {
		Caixa cx = new Caixa(5000);
		
		ArrayList<Thread_pessoa> aThread = new ArrayList<Thread_pessoa>();
		synchronized(cx){
			for(int i=0; i<10; i++){
				Thread_pessoa threadA = new Thread_pessoa(Integer.toString(i), 2500, 1, i+1, cx);
				aThread.add(threadA);
			}
		}
		
		/*Thread_pessoa threadA = new Thread_pessoa("A", 300, 1, 1, cx);
		//lista.add(t);
		Thread_pessoa threadB = new Thread_pessoa("B", 900, 1, 2, cx);
		//lista.add(t);
		Thread_pessoa threadC = new Thread_pessoa("C", 1000, 1, 3, cx);
		//lista.add(t);
		Thread_pessoa threadD = new Thread_pessoa("D", 1000, 1, 3, cx);*/
		try {
			for(int i = 0; i<10; i++){
				aThread.get(i).getThread().join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\nImprimindo o extrato do caixa:");
		System.out.println(cx.getExtrato());
		System.out.println("Total no caixa:" + cx.getValorTotal());
	}

}
