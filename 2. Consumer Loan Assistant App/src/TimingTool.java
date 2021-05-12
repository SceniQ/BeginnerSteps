/*
	Chapter  	: ---
	Programmer	: Tebogo Assagaai
	Filename	: FunctionPack.java
	Start Date  : 25 March 2021 [20:30]
	End Date	:
	Purpose		: These functinal interfaces will be used throughout the Java HomeWork Projects
	Source		: The methods will be used to work on Java Homework projects wrutten by - Philip Conrod
				  & Lou Tylee
*/

// Import/Package statements

import javax.swing.*;
import java.text.DecimalFormat;


//================================== Classes ===============================

//Declared an object that consists of the tools needed to maintain the timing
//procedure of the project
class TimingTools{

    //The Timer object to be used
    Timer timer;

    //Calculations for the timer results
    public String HMSdisplay(long tms){
        //hour, min, sec
        int h, m, s;

        //"recorded time" delivered in 'long' milliseconds
        //but divided by 1000 to reduce it to seconds
        double t = tms/1000.0;


        //Break the "recorded time" down into hours, minutes and seconds
        h = (int)(t/3600);
        m = (int)((t-h*3600)/60);
        s = (int)(t-h*3600-m*60);

        //Format the "recorded time" and return as a String
        return(new DecimalFormat("00").format(h)+":"+new DecimalFormat("00").format(m)+
                ":"+new DecimalFormat("00").format(s));
    }

    //This method will be used to validate input fields to see if they contain a valid decimal number
    //with only digits and a single decimal point
    public boolean validateDecimalNumber(JTextField tf){

        String s = tf.getText().trim();
        var hasDecimal = false;
        var valid = true;

        if(s.length()==0){ valid = false;}
        else{

            //Using the string's length to sieve through every character of the input string provided by the user
            for(int i = 0; i<s.length(); i++){

                char c = s.charAt(i);

                if( c >= '0' && c <= '9'){
                    continue;
                }
                else if(c == '.' && !hasDecimal){
                    hasDecimal = true;
                }
                else{
                    valid = false;
                }
            }
        }
        //If all is well and valid, the method will set the text on the textField in question
        tf.setText(s);
        if(!valid){
            tf.requestFocus();
        }

        return (valid);
    }

}
//========================== Functional Interfaces ========================
//1. This interface will be used to process all void() methods that need to run within
//The Stopwatch Object
@FunctionalInterface
interface Procedure{
    void process();
}




