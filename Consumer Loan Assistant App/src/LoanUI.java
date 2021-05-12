import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class LoanUI{

    JLabel[] component;
    JLabel[] textFieldLabel;
    JTextField[] inputTextField;
    JButton button;


    public LoanUI(){

        //Assigning all GUI components to one variable call
        component = new JLabel[]{LoanGUI.LOGO.getComponent(),LoanGUI.PROFILE_ICON.getComponent(),
                                 LoanGUI.EXIT_ICON.getComponent(), LoanGUI.RESULT_DISPLAY.getComponent(),
                                 LoanGUI.SUBTITLE.getComponent(),LoanGUI.BACKGROUND.getComponent(),
                                 LoanGUI.LINE_SEP.getComponent(),LoanGUI.LINE_SEP.getComponent(),
                                 LoanGUI.LINE_SEP.getComponent(),LoanGUI.LINE_SEP.getComponent(),
                                 LoanGUI.BUTT_SHAD.getComponent(), LoanGUI.COMPUTE.getComponent(), LoanGUI.COMPUTE.getComponent()};

        //Assigning all the textFields needed(Using an anonymous Array)
        textFieldLabel = new JLabel[7];

        //Assigning the textFields
        inputTextField = new JTextField[4];
        //Assigning the button value
        button = new JButton("CALCULATE");


        //Setting the properties
        setComponents.process();
        setInputTextField.process();
        setTextFieldLabel.process();

        //Setting the mouse listener for the labels viewed
        for(JLabel textLab : textFieldLabel) {
            textLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labClicked(e);
                }
            });

            component[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    compoClick(e);
                }
            });
            component[11].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    compoClick(e);
                }
            });
            component[12].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    compoClick(e);
                }
            });
        }

    }

    private final Procedure setComponents = () ->{

        component[0].setBounds(13,16,80, 20);       //Logo
        component[1].setBounds(720,16,25, 27);      //Profile Icon
        component[2].setBounds(680,16,25, 25);      //Exit Button
        component[3].setBounds(230,175,511, 154);   //Result Display
        component[4].setBounds(15,65,135, 50);      //Subtitle
        component[5].setBounds(0,0,800, 450);       //Background
        component[11].setBounds(185,232,40,40);     //Compute Button1
        component[12].setBounds(185,282,40,40);
        //Line Separators
        component[6].setBounds(18,171,169,2);
        component[7].setBounds(18,221,169,2);
        component[8].setBounds(18,271,169,2);
        component[9].setBounds(18,321,169,2);

        //Button Shadow
        component[10].setBounds(9,349,190,60);


        button.setBounds(27,350,150,31);
        button.setFocusable(false);
        button.setFont(new Font("Calibri", Font.PLAIN,15));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(ColorPalette.BROWNISH.getColor());
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(ColorPalette.BROWNISH.getColor(),0,true));

    };
    private final Procedure setInputTextField = () ->{

        //Assigning the textField values
        for(int i = 0; i<inputTextField.length; i++){
            inputTextField[i] = new JTextField();
            inputTextField[i].setForeground(ColorPalette.MY_GRAY.getColor());
            inputTextField[i].setBorder(BorderFactory.createLineBorder(ColorPalette.MY_GRAY.getColor(),0,true));
            inputTextField[i].setEditable(true);
            inputTextField[i].setFont(new Font("Calibri", Font.BOLD, 16));
            inputTextField[i].setHorizontalAlignment(SwingConstants.RIGHT);
        }
        inputTextField[0].setBounds(22,138,161,33);
        inputTextField[1].setBounds(22,188,161,33);
        inputTextField[2].setBounds(22,238,161,33);
        inputTextField[3].setBounds(22,288,161,33);

    };
    private final Procedure setTextFieldLabel = () ->{

        for(int i = 0; i<textFieldLabel.length; i++){
            textFieldLabel[i] = new JLabel();
            textFieldLabel[i].setFont(new Font("Calibri", Font.PLAIN,13));
            textFieldLabel[i].setForeground(ColorPalette.MY_GRAY.getColor());

        }

        textFieldLabel[0].setText("Loan Balance");
        textFieldLabel[0].setBounds(24,145,160,32);
        textFieldLabel[1].setText("Interest Rate");
        textFieldLabel[1].setBounds(24,195,160,32);
        textFieldLabel[2].setText("No. of Payments");
        textFieldLabel[2].setBounds(24,245,160,32);
        textFieldLabel[3].setText("Monthly Payment");
        textFieldLabel[3].setBounds(24,295,160,32);

        //Display textField labels
        for(int i = 4; i<textFieldLabel.length; i++){

            textFieldLabel[i].setText("-.--");
            textFieldLabel[i].setFont(new Font("Calibri", Font.PLAIN,30));
        }
        textFieldLabel[4].setBounds(240,210,160,90);
        textFieldLabel[5].setBounds(422,210,160,90);
        textFieldLabel[6].setBounds(608,210,160,90);

    };

    //Actions to be performed when label is clicked
    private void labClicked(MouseEvent e) {

        if (e.getSource() == textFieldLabel[0]) {

            inputTextField[0].setBackground(ColorPalette.BLUEISH_WHITE.getColor());
            inputTextField[0].setVisible(true);
            textFieldLabel[0].setText("Loan Balance(R):");
            textFieldLabel[0].setFont(new Font("Calibri", Font.BOLD, 10));
            textFieldLabel[0].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[0].setBounds(17, 122, 120, 20);
        } else if (e.getSource() == textFieldLabel[1]) {

            inputTextField[1].setBackground(ColorPalette.BLUEISH_WHITE.getColor());
            inputTextField[1].setVisible(true);
            textFieldLabel[1].setText("Interest Rate(%):");
            textFieldLabel[1].setFont(new Font("Calibri", Font.BOLD, 10));
            textFieldLabel[1].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[1].setBounds(17, 172, 120, 20);
        }
    }

    //The boolean below will be used to keep track of which payment type will be calculated
    boolean computePayment;
    private void compoClick(MouseEvent e) {

        if (e.getSource() == component[11]) {

            computePayment = false;
            component[11].setVisible(false);
            component[12].setVisible(true);

            inputTextField[2].setText("");
            inputTextField[2].setEditable(false);
            inputTextField[2].setBackground(Color.lightGray);
            inputTextField[2].setVisible(true);


            inputTextField[3].setVisible(true);
            inputTextField[3].setEditable(true);
            inputTextField[3].setBackground(ColorPalette.BLUEISH_WHITE.getColor());
            //inputTextField[3].setBackground(ColorPalette.BLUEISH_WHITE.getColor());


            textFieldLabel[2].setText("No. of Payments:");
            textFieldLabel[2].setFont(new Font("Calibri", Font.BOLD, 10));
            textFieldLabel[2].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[2].setBounds(17, 222, 120, 20);

            textFieldLabel[3].setText("Monthly Payments(R):");
            textFieldLabel[3].setFont(new Font("Calibri", Font.BOLD,10));
            textFieldLabel[3].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[3].setBounds(17,272,120,20);

        }

        else if (e.getSource() == component[12]) {

            computePayment = true;
            component[12].setVisible(false);
            component[11].setVisible(true);

            inputTextField[3].setText("");
            inputTextField[3].setEditable(false);
            inputTextField[3].setBackground(Color.lightGray);
            inputTextField[3].setVisible(true);


            inputTextField[2].setVisible(true);
            inputTextField[2].setEditable(true);
            inputTextField[2].setBackground(ColorPalette.BLUEISH_WHITE.getColor());
            inputTextField[2].setFocusable(true);
            //inputTextField[3].setBackground(ColorPalette.BLUEISH_WHITE.getColor());


            textFieldLabel[2].setText("No. of Payments:");
            textFieldLabel[2].setFont(new Font("Calibri", Font.BOLD, 10));
            textFieldLabel[2].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[2].setBounds(17, 222, 120, 20);

            textFieldLabel[3].setText("Monthly Payments(R):");
            textFieldLabel[3].setFont(new Font("Calibri", Font.BOLD,10));
            textFieldLabel[3].setForeground(ColorPalette.MY_GRAY.getColor());
            textFieldLabel[3].setBounds(17,272,120,20);

        }

        else if(e.getSource() == component[2]){
            System.exit(0);
        }
    }


    //Using the Consumer functional to center/incorporate all methods that need am argument
    //but never return anything
    final Consumer<JFrame> centerFrame = (e) ->{

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        e.setBounds((int)(0.5*(screenSize.width - e.getWidth())),
                (int)(0.5*(screenSize.height - e.getHeight())),
                e.getWidth(), e.getHeight());
    };
}
