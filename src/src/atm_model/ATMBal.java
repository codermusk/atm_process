package src.atm_model;

import java.io.Serializable;

public class ATMBal implements Serializable {

	private static final long serialVersionUID = 8175447570703203539L;

	private int _2000;
	private int _500;
	private int _100;

	public ATMBal() {
		_2000 = 0;
		_500 = 0;
		_100 = 0;
	}

	public ATMBal(int _2000, int _500, int _100) {
		super();
		this._2000 = _2000;
		this._500 = _500;
		this._100 = _100;
	}

	public long balance() {
		return ((_2000 * 2000) + (_500 * 500) + (_100 * 100));
	}

	public boolean withdraw(int amount) {
		int t2000=0,t500=0,t100=0;
		if(balance()>amount) {
			if(_2000 > (amount/2000) && _500 > ((amount%2000)/500) && _100 > ((amount%500)/100)) {
				t2000 = amount/2000;
				amount %= 2000;
				t500 = amount/500;
				amount %= 500;
				t100 = amount/100;
				amount %= 100;
				if(amount == 0) {
					_2000 -= t2000;
					_500 -= t500;
					_100 -= t100;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int get_2000() {
		return _2000;
	}

	public void set_2000(int _2000) {
		this._2000 = _2000;
	}

	public int get_500() {
		return _500;
	}

	public void set_500(int _500) {
		this._500 = _500;
	}

	public int get_100() {
		return _100;
	}

	public void set_100(int _100) {
		this._100 = _100;
	}

	@Override
	public String toString() {
		return ("ATM Balance \n 2000 x " + _2000 + " = " + (_2000 * 2000) + "\n 500  x " + _500 + " = " + (_500 * 500)
				+ "\n 100  x " + _100 + " = " + (_100 * 100) + "\n Total Balance: " + balance());
	}

}
