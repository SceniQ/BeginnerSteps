/*
	Chapter  	: Inheritance - Application Project
	Programmer	: Tebogo Assagaai
	Filename	: MonthlyPayment.java
	Start Date  : 24 October 2020 [20:18]
	End Date	: 25 October 2020 [~15:00]
	Purpose		: To provide a better understanding of Java abilities
*/

// Import statements
import javax.swing.*;
import java.text.DecimalFormat;


public class MonthlyPayment{
	public static void main (String... payment){
		
		DecimalFormat form = new DecimalFormat("R#00.00");
		
		Customer fin = new Finance_Period();
		Finance_Period cust = (Finance_Period) fin;
		
		// ========================================= Calling methods
		cust.setCustomerName();
		cust.setContactNum();
		cust.setProductPrice();
		cust.setNumOfMonths();
		
		// ========================================= Displaying Results
		double totalDue = cust.calMonthlyRepAmount() * cust.getNumOfMonths();
		
		JOptionPane.showMessageDialog(null, "Full Name       : "+ cust.getCustomerName() + "\n"+
											"Conatct Number  : "+ cust.getContactNum()+ "\n"+
											"Product Price   : "+ form.format(cust.getProductPrice())+ "\n"+
											"Repayment Months: "+ cust.getNumOfMonths()+ "\n"+
											"Monthly Payment : "+ form.format(cust.calMonthlyRepAmount())+ "\n"+
											"Total Amount due: "+ form.format(totalDue));
	}
}


class Customer{
	
	private String customerName, contactNum;
	private double productPrice, monthlyRepAmount;
	private int numOfMonths;
	
	//public double totalDue = (this.monthlyRepAmount + this.productPrice);
	
	public void setCustomerName(){
		
		this.customerName = JOptionPane.showInputDialog(null, "Please enter your full name");
	}
	
	public void setContactNum(){
		
		while(true){
			
			this.contactNum = JOptionPane.showInputDialog(null, "Please enter your phone number");
		
			if(this.contactNum.length() > 10 || this.contactNum.length() < 10){
				JOptionPane.showMessageDialog(null, "Contact number shouldn't be less than or exceed ten digits");
				continue;
			}
			else
				break;
		}
	}
	
	public void setProductPrice(){
		
		this.productPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the price of the product"));
	}
	
	public void setNumOfMonths(){
		
		while(true){
			
			this.numOfMonths = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the repayment months"));
		
			if(this.numOfMonths <= 0 || this.numOfMonths > 12){
				JOptionPane.showMessageDialog(null, "You cannot be granted a product who is greater than 12 months or less than 1,"+"\n"+
													"please re - enter the correct repayment months");
				continue;
			} else{ break;}
		}
	}
	
	public String getCustomerName(){
		
		return customerName;
	}
	
	public String getContactNum(){
		
		return contactNum;
	}
	
	public double getProductPrice(){
		
		return productPrice;
	}
	
	public int getNumOfMonths(){
		
		return numOfMonths;
	}
	
	public double calMonthlyRepAmount(){
		
		this.monthlyRepAmount = (this.productPrice/this.numOfMonths);
		
		return monthlyRepAmount;
	}
}


final class Finance_Period extends Customer{
	
	public Finance_Period(){
		
		JOptionPane.showMessageDialog(null, "This is a mini project to test the payment of customer's monthly"+"\n"+"\t"+
											"repayment on product credited");
	}
	
	//public double monthlyRepAmount;
	
	@Override
	public double calMonthlyRepAmount(){
		
		if(getNumOfMonths() > 3){
			return ((getProductPrice()/getNumOfMonths())*0.25) + 
			       (getProductPrice()/getNumOfMonths());
		}
		else{		
			return (getProductPrice()/getNumOfMonths());
		}
	}
}

