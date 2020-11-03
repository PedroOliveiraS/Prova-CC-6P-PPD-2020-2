package br.edu.iftm.threads;

public class Caixa {
	//Variaveis da Caixa
    private double valorTotal = 5000;
    private String extrato = new String();
    private int chamado = 1;
    
    //Metodo para colocar o valor inicial de dinheiro do caixa
    public Caixa(double valorTotal){
    	this.valorTotal = valorTotal;
    }
    
    //Metodo onde os clientes ficam esperando na fila por sua vez de usarem o caixa
    synchronized void operacao(String nome, double valor, int opr, int pos){
    	
    	//Trava o cliente na fila ate o seu número seja 'chamado'
    	while(chamado != pos){
    		try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
		System.out.println("\n" + nome + " entrou para falar com o caixa.");
		
    	if((opr == 1 && this.getValorTotal() >= valor) || opr == 2){
    		if(opr == 1){ 
            	// Operação 01 -> Realizar saque
                if(this.getValorTotal() >= valor){
                    this.setValorTotal(this.getValorTotal() - valor);
                    try {
        				Thread.sleep(500);
        			} catch (InterruptedException e) {
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
    				e.printStackTrace();
    			}
            	this.setValorTotal(this.getValorTotal() + valor);
            	this.setExtrato(this.getExtrato() + nome + " depositou " + valor + " reais \n");
            }
		}
    	
    	//O cliente cancela sua operação se o dinheiro no caixa for insuficiente para seu saque
		else if(opr == 1 && this.getValorTotal() < valor){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setExtrato(this.getExtrato() + nome + " cancelou a operação por falta de crédito no caixa.\n"); 
    	}
    	
    	chamado++;
    	System.out.println(nome +" saiu do caixa.\n");
		notifyAll();
        return;
    }
    
    //Getters Setters

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
