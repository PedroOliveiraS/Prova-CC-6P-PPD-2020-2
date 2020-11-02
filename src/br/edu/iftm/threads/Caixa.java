package br.edu.iftm.threads;

public class Caixa {
    private double valorTotal = 5000;
    private String extrato = new String();
    private int chamado = 1;
    
    public Caixa(double valorTotal){
    	this.valorTotal = valorTotal;
    }
    
    synchronized void operacao(String nome, double valor, int opr, int pos){
    	while(chamado != pos){
    		try {
    			//System.out.println("Thread " + nome + " esperando.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		System.out.println(nome + " entrou para falar com o caixa.");
    	if((opr == 1 && this.getValorTotal() >= valor) || opr == 2){
    		if(opr == 1){ 
            	// Operação 01 -> Realizar saque
                if(this.getValorTotal() >= valor){
                    this.setValorTotal(this.getValorTotal() - valor);
                    try {
        				Thread.sleep(500);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
                    this.setExtrato(this.getExtrato() + nome +" sacou " + valor + " reais \n"); 
                    
                }
            }
            else if(opr == 2){ 
            	// Operação 02 -> Realizar depósito
            	try {
    				Thread.sleep(1000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	this.setValorTotal(this.getValorTotal() + valor);
            	this.setExtrato(this.getExtrato() + " depositou" + valor + " reais \n");
            }
		}
		else if(opr == 1 && this.getValorTotal() < valor){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setExtrato(this.getExtrato() + nome + " cancelou a operação por falta de crédito no caixa.\n"); 
    	}
    	
		//System.out.println("Thread " + nome + " notificou");
    	chamado++;
    	System.out.println(nome +" saiu do caixa.");
		notifyAll();
        return;
    }

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getExtrato() {
		return extrato;
	}

	public void setExtrato(String extrato) {
		this.extrato = extrato;
	}

}
