package br.edu.iftm.threads;

public class Thread_pessoa implements Runnable {

	// Variaveis dos clientes
    private String nome;
    private double valor;
    private int opr; 
    private Caixa caixa;
    private Thread Thread;
    private int pos;
    
    //Inicializador do Cliente
    public Thread_pessoa(String nome, double valor, int opr, int pos, Caixa caixa) {
        this.nome = nome;
        this.valor = valor;
        this.opr = opr;
        this.pos = pos;
        this.caixa = caixa;
        Thread = new Thread(this,nome);
        Thread.start();
    }
    
    //Get Setters
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getOpr() {
		return opr;
	}

	public void setOpr(int opr) {
		this.opr = opr;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Thread getThread() {
		return Thread;
	}

	public void setThread(Thread thread) {
		Thread = thread;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Thread_pessoa(int opr){
    	this.opr = opr;
    	try {
			new Thread(this, nome).wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Método que envia as recém criadas threads para o caixa
    public void run() {
    	caixa.operacao(nome, valor, opr, pos); 
    }
}