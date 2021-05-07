import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.awt.Color.*;

//This object consists of common buttons, fields, labels that are
//commonly used within the Stopwatch UI + their actions provided by the TimingTools object

public class CommonStopwatchUI{

    //Text labels
    JLabel[] label;

    //Text fields
    JTextField[] textField;
    JButton[] button;

    //Shadow Labels: Only a maximum number of two shadows are allowed
    //With their default colors included
    JLabel[] buttonShadows;

    //Radio buttons
    JLabel[] colorChoice;

    //To store the marked records
    ArrayList<String> markedTimes = new ArrayList<>();

    //The setup constructor
    public CommonStopwatchUI(){

        label = new JLabel[]{new JLabel("Running Time:"),
                new JLabel("Total Time:"),
                new JLabel("VIEW MARKED TIMES")};

        button = new JButton[]{new JButton("START"),	        //0 = startMarkContinue button
                new JButton("STOP"), 	                    //1 = stopRest button
                new JButton("SAVE RECORDS")};

        textField = new JTextField[]{new JTextField("00:00:00"),	//0 = initial run time text
                new JTextField("00:00:00"),
                new JTextField()};

        colorChoice = new JLabel[]{new JLabel(ColorChoice.selector.getColorType()),
                new JLabel(ColorChoice.yellowChoice.getColorType()),
                new JLabel(ColorChoice.greenChoice.getColorType()),
                new JLabel(ColorChoice.blueChoice.getColorType()),
                new JLabel(ColorChoice.purpleChoice.getColorType()),
                new JLabel(ColorChoice.redChoice.getColorType()),
                new JLabel(ColorChoice.orangeChoice.getColorType())};

        buttonShadows = new JLabel[]{
                new JLabel(ShadowPalette.DEFAULT_SHAD.getShadow()),
                new JLabel(ShadowPalette.DEFAULT_SHAD.getShadow()),
                new JLabel(ShadowPalette.DEFAULT_SHAD.getShadow())};

        //'Opens' the color choice radio buttons
        openColorPalette.process();

        //Sets the labels
        setLabel.process();
        //Sets 'default' color
        setTextField.process();
        //sets 'default' button color
        setButton.process();
        //Updates shadow color after updating button color
        setButtonShadow.process();

        //Setting the provided action to the color choice menu
        setLabelClicker();
        //Popup display components will also be loaded during runtime
        //Setting the view for the recordsView frame

    }

    //This process will be used to set the color choice 'radio buttons'
    private final Procedure openColorPalette = () ->{

        //This label will be used as a "selector" to notify which
        //theme has been selected:: Default color theme is already chosen
        colorChoice[0].setBounds(255, 18, 25, 25);

        colorChoice[1].setBounds(65, 18, 25, 25);
        colorChoice[2].setBounds(104, 18, 25, 25);
        colorChoice[3].setBounds(144, 18, 25, 25);
        colorChoice[4].setBounds(182, 18, 25, 25);
        colorChoice[5].setBounds(218, 18, 25, 25);
        colorChoice[6].setBounds(255, 18, 25, 25);
       // colorChoice[1].setBounds(40, 18, 25, 25);
    };

    //This process will be used to set and place the labels onto the frame
    private final Procedure setLabel = () ->{

        for (JLabel jLabel : label) {

            //label[i].setFont(Font.createFont(2,new File("/fonts/gotham.ttf")));
            jLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
            jLabel.setForeground(Color.gray);
            label[0].setBounds(15, 90, 110, 20);
            label[1].setBounds(15, 210, 110, 20);
            label[2].setBounds(124, 420, 110, 20);
            label[2].setFont(new Font("Calibri", Font.BOLD, 11));
            label[2].setForeground(Color.gray);
        }
    };

    //This process will be used to change the textfield color, initialise its size, position and font
    private final Procedure setTextField = () ->{

        for (JTextField jTextField : textField) {

            jTextField.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
            jTextField.setHorizontalAlignment(SwingConstants.CENTER);
            textField[0].setFont(new Font("Cailbir", Font.PLAIN, 40));
            textField[1].setFont(new Font("Cailbir", Font.BOLD, 40));
            jTextField.setForeground(ColorPalette.ORANGE.getColor());
            jTextField.setFocusable(false);
            textField[0].setBounds(19, 115, 315, 66);
            textField[1].setBounds(19, 235, 315, 66);
        }
    };

    //This process will update the button colors, initialise its size, position and font
    private final Procedure setButton = () ->{

        for (JButton jButton : button) {

            jButton.setForeground(WHITE);
            jButton.setBackground(ColorPalette.ORANGE.getColor());
            jButton.setFocusable(false);
            jButton.setBorder(BorderFactory.createLineBorder(ColorPalette.ORANGE.getColor(), 0, true));
            jButton.setFont(new Font("Bahnschrift Semi-Condensed", Font.BOLD, 12));
            button[0].setBounds(28, 352, 136, 42);
            button[1].setBounds(185, 352, 136, 42);
        }
    };

    //This process will update the button shadows
    private final Procedure setButtonShadow = () -> {
        //Shadow positioning
        buttonShadows[0].setBounds(15, 351, 165, 84);
        buttonShadows[1].setBounds(171, 351, 165, 84);
    };

    //Used a method to set the action that will affect the colorChoice palette
    private void setLabelClicker(){

       for(int i = 1; i<colorChoice.length; i++){
           colorChoice[i].addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   mouseClick(e);
               }
           });
       }

            label[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseClick(e);
                }
            });
    }

    //The behaviour of buttons when mouse is clicked
    private void mouseClick(MouseEvent e){

        if(e.getSource() == colorChoice[1]){
            colorChoice[0].setBounds(65, 18, 25, 25);
            textField[0].setForeground(ColorPalette.YELLOW.getColor());
            textField[1].setForeground(ColorPalette.YELLOW.getColor());
            textField[2].setForeground(ColorPalette.YELLOW.getColor());
            button[0].setBackground(ColorPalette.YELLOW.getColor());
            button[1].setBackground(ColorPalette.YELLOW.getColor());
            button[2].setBackground(ColorPalette.YELLOW.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.YELLOW.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.YELLOW.getColor(),0,true));
            //buttonShadows[0] = new JLabel(ShadowPalette.YELLOW_SHAD.getShadow());
            //buttonShadows[1] = new JLabel(ShadowPalette.YELLOW_SHAD.getShadow());
        }
        if(e.getSource() == colorChoice[2]){
            colorChoice[0].setBounds(104, 18, 25, 25);
            textField[0].setForeground(ColorPalette.GREEN.getColor());
            textField[1].setForeground(ColorPalette.GREEN.getColor());
            textField[2].setForeground(ColorPalette.GREEN.getColor());
            button[0].setBackground(ColorPalette.GREEN.getColor());
            button[1].setBackground(ColorPalette.GREEN.getColor());
            button[2].setBackground(ColorPalette.GREEN.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.GREEN.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.GREEN.getColor(),0,true));
            //buttonShadows[0] = new JLabel(ShadowPalette.GREEN_SHAD.getShadow());
            //buttonShadows[1] = new JLabel(ShadowPalette.GREEN_SHAD.getShadow());
        }
        if(e.getSource() == colorChoice[3]){

           // buttonShadows[0] = new JLabel(ShadowPalette.BLUE_SHAD.getShadow());
           // buttonShadows[1] = new JLabel(ShadowPalette.BLUE_SHAD.getShadow());
            colorChoice[0].setBounds(144, 18, 25, 25);
            textField[0].setForeground(ColorPalette.BLUE.getColor());
            textField[1].setForeground(ColorPalette.BLUE.getColor());
            textField[2].setForeground(ColorPalette.BLUE.getColor());
            button[0].setBackground(ColorPalette.BLUE.getColor());
            button[1].setBackground(ColorPalette.BLUE.getColor());
            button[2].setBackground(ColorPalette.BLUE.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.BLUE.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.BLUE.getColor(),0,true));

        }
        if(e.getSource() == colorChoice[4]){
            colorChoice[0].setBounds(182, 18, 25, 25);
            textField[0].setForeground(ColorPalette.PURPLE.getColor());
            textField[1].setForeground(ColorPalette.PURPLE.getColor());
            textField[2].setForeground(ColorPalette.PURPLE.getColor());
            button[0].setBackground(ColorPalette.PURPLE.getColor());
            button[1].setBackground(ColorPalette.PURPLE.getColor());
            button[2].setBackground(ColorPalette.PURPLE.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.PURPLE.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.PURPLE.getColor(),0,true));
            //buttonShadows[0] = new JLabel(ShadowPalette.PURPLE_SHAD.getShadow());
            //buttonShadows[1] = new JLabel(ShadowPalette.PURPLE_SHAD.getShadow());
        }
        if(e.getSource() == colorChoice[5]){
            colorChoice[0].setBounds(218, 18, 25, 25);
            textField[0].setForeground(ColorPalette.RED.getColor());
            textField[1].setForeground(ColorPalette.RED.getColor());
            textField[2].setForeground(ColorPalette.RED.getColor());
            button[0].setBackground(ColorPalette.RED.getColor());
            button[1].setBackground(ColorPalette.RED.getColor());
            button[2].setBackground(ColorPalette.RED.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.RED.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.RED.getColor(),0,true));
            //buttonShadows[0] = new JLabel(ShadowPalette.RED_SHAD.getShadow());
            //buttonShadows[1] = new JLabel(ShadowPalette.RED_SHAD.getShadow());
        }
        if(e.getSource() == colorChoice[6]){
            colorChoice[0].setBounds(255, 18, 25, 25);
            textField[0].setForeground(ColorPalette.ORANGE.getColor());
            textField[1].setForeground(ColorPalette.ORANGE.getColor());
            textField[2].setForeground(ColorPalette.ORANGE.getColor());
            button[0].setBackground(ColorPalette.ORANGE.getColor());
            button[1].setBackground(ColorPalette.ORANGE.getColor());
            button[2].setBackground(ColorPalette.ORANGE.getColor());
            button[0].setBorder(BorderFactory.createLineBorder(ColorPalette.ORANGE.getColor(),0,true));
            button[1].setBorder(BorderFactory.createLineBorder(ColorPalette.ORANGE.getColor(),0,true));
            //buttonShadows[0] = new JLabel(ShadowPalette.ORANGE_SHAD.getShadow());
            //buttonShadows[1] = new JLabel(ShadowPalette.ORANGE_SHAD.getShadow());
        }

    }

}

//For the timer User interactions. These panels are created to offer
// simple divisions on the stopwatch User interface
class BackPan extends JPanel{

    public BackPan(){

        this.setBackground(WHITE);
        this.setSize(360, 500);
        this.setVisible(true);
    }
}
class PalettePanel extends JPanel{

    public PalettePanel(){

        this.setBackground(new Color(213,213,213));
        this.setSize(353, 59);
        this.setVisible(true);

    }
}