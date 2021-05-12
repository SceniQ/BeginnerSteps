import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class LoanAssistant{

    private final LoanUI ui = new LoanUI();
    private TimingTools timing = new TimingTools();

    public LoanAssistant(){

        //Host frame
        JFrame host = new JFrame("Loan Assistant");

        //Setting the panel for the background color...
        JPanel backgroundColor = new JPanel();
        backgroundColor.setBackground(ColorPalette.BLUEISH_WHITE.getColor());

        //Setting frame properties
        host.setTitle("Loan Assistant");
        host.setSize(800, 450);
        host.setResizable(false);
        host.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        host.setVisible(true);

        //Adding the button
        host.add(ui.button);
        ui.button.addActionListener(e -> computePayments.process());

        //Adding the textlabel
        for(JLabel textLab: ui.textFieldLabel)
            host.add(textLab);

        //Adding the visual components and giving some actions
        for(JLabel lab: ui.component)
            host.add(lab);


        //Adding textFields
        for(JTextField texts: ui.inputTextField){
            texts.setVisible(false);
            host.add(texts);
        }

        //Adding the background color
        host.add(backgroundColor);
        //Centering the frame
        ui.centerFrame.accept(host);
    }

    //Method(s) that will affect the app flow
    private Procedure computePayments = () ->{

        double balance, interest, payment;
        int months;
        double monthlyInterest, multiplier;
        double loanBalance, finalPayment;

        if(timing.validateDecimalNumber(ui.inputTextField[0])){
            balance = Double.valueOf(ui.inputTextField[0].getText()).doubleValue();
        }else{
            /*
            Here we will use the JOptionPane object to notify the user about their incorrect
            inputs
            */
           JOptionPane.showConfirmDialog(null,"The enterd amount is invalid/incorrect"+
                   "\nPlease enter an appropriate amount","Invalid Loan Balance Input",JOptionPane.DEFAULT_OPTION,
                   JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //Checking the validity of the user input
        if(timing.validateDecimalNumber(ui.inputTextField[1])){
            interest = Double.valueOf(ui.inputTextField[1].getText()).doubleValue();
        }else{
            JOptionPane.showConfirmDialog(null,"The enterd amount is invalid/incorrect"+
                            "\nPlease enter an appropriate amount","Invalid Interest Rate Input",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        monthlyInterest = interest/1200;

        if(ui.button.getText().equalsIgnoreCase("calculate")){

            if(ui.computePayment) {

                //Compute the monthly payment
                if(timing.validateDecimalNumber(ui.inputTextField[2])){
                    months = Integer.valueOf(ui.inputTextField[2].getText()).intValue();
                }else{

                    JOptionPane.showConfirmDialog(null,"The enterd amount is invalid/incorrect"+
                                    "\nPlease enter an appropriate amount","Invalid Number of Payments Input",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(interest == 0){
                    payment = balance/months;
                }else{
                    multiplier = Math.pow(1 + monthlyInterest, months);
                    payment = balance * monthlyInterest * multiplier / (multiplier - 1);
                }
                ui.inputTextField[3].setText(new DecimalFormat("0.00").format(payment).replace(",","."));
            }
            else{

                //Compute the number of payments
                if(timing.validateDecimalNumber(ui.inputTextField[3])){
                    payment = Double.valueOf(ui.inputTextField[3].getText()).doubleValue();
                }else{

                    JOptionPane.showConfirmDialog(null,"The eneterd amount is invalid/incorrect"+
                                    "\nPlease enter an appropriate amount","Invalid Monthly Paymnet Input",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(interest == 0){
                    months = (int)(balance/payment);
                }else{
                    months = (int)((Math.log(payment) -
                            Math.log(payment - balance * monthlyInterest))/Math.log(1 + monthlyInterest));
                }
                ui.inputTextField[2].setText(String.valueOf(months));
            }

            //Resetting the payment prior to the analysis to fix at two decimal places
            payment = Double.valueOf(ui.inputTextField[3].getText()).doubleValue();

            ui.inputTextField[0].setText(new DecimalFormat("0.00").format(balance).replace(",","."));
            ui.inputTextField[1].setText(new DecimalFormat("0.00").format(interest).replace(",","."));

            //Process all but last payment
            loanBalance = balance;
            for(int paymentNum = 1; paymentNum <= months-1; paymentNum++)
                loanBalance += loanBalance * monthlyInterest - payment;

            //Find final payment
            finalPayment = loanBalance;

            if(finalPayment > payment){

                //Applying one more payment
                loanBalance += loanBalance * monthlyInterest - payment;
                finalPayment = loanBalance;
                months++;
                ui.inputTextField[2].setText(String.valueOf(months));
            }

            double interestPaid = ((months - 1) * payment + finalPayment - balance);
            double totalPayment = ((months - 1) * payment + finalPayment);

            ui.inputTextField[3].setText(new DecimalFormat("0.00").format(payment).replace(",","."));
            ui.textFieldLabel[4].setText(new DecimalFormat("0.00").format(finalPayment).replace(",","."));
            ui.textFieldLabel[5].setText(new DecimalFormat("0.00").format(interestPaid).replace(",","."));
            ui.textFieldLabel[6].setText(new DecimalFormat("0.00").format(totalPayment).replace(",","."));

            ui.button.setText("NEW ANALYSIS");

            ui.component[11].setVisible(false);
            ui.component[12].setVisible(false);

        }
        else if(ui.button.getText().equalsIgnoreCase("new analysis")){

            ui.textFieldLabel[3].setText("Monthly Payment");
            ui.textFieldLabel[3].setBounds(24,295,160,32);
            ui.textFieldLabel[2].setText("No. of Payments");
            ui.textFieldLabel[2].setBounds(24,245,160,32);

            for(int i = 2; i<4; i++){
                ui.textFieldLabel[i].setFont(new Font("Calibri", Font.PLAIN,13));
                ui.inputTextField[i].setVisible(false);
                ui.inputTextField[i].setText("");
            }

            for(int i = 4; i<7; i++){
                ui.textFieldLabel[i].setText("-.--");
            }

            ui.button.setText("CALCULATE");
            ui.component[11].setVisible(true);
            ui.component[12].setVisible(true);


        }
    };

    public static void main(String... assistant){ new LoanAssistant();}
}
