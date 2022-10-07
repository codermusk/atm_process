package src.atm_model;

import java.io.Serializable;

public class ATM_User implements Serializable {

	private static final long serialVersionUID = -1963401930459552036L;
	
	private int accNo;
	private String name;
	private int balance;
	private int pin;
	
	public ATM_User(int accNo, String name, int pin) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.balance = 0;
		this.pin = pin;
	}

	public ATM_User(int accNo, String name, int balance, int pin) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
		this.pin = pin;
	}
	
	public boolean checkPin(int pin) {
		return (this.pin == pin);
	}
	
	public void resetPin(int pin) {
		this.pin = pin;
	}
	
	public void withdraw(int amount) {
		balance -= amount;
	}

	public int getAccNo() {
		return accNo;
	}
	
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return (accNo + "\t" + name + "\t" + balance);
	}
	
}
