import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Random;

public class FlashCardQuiz {

    private final QuizUI ui = new QuizUI();
    private Random mathQuizRandom = new Random();
    private MethodTools mathQuizTimer = new MethodTools();
    private MethodTools wordsTimer = new MethodTools();

    /*
    class level variables to keep track of the number of
    problems tried and the number correct
    */
    private int numberTried, numberCorrect;
    private int correctAnswer, numberDigit;
    private String problem;

    /*
    class level variables to hold answers and the current digit
    number in the answer
    */
    private String yourAnswer;
    private int digitNumber;

    /*
    class level variable (problemTime) to store the time value
    (whether counting up or down) in seconds.
    */
    private int problemTime;

    //Class variables needed to keep track of time and words played
    private long startTime;

    private JFrame host = new JFrame("Flash Card Quiz");

    public FlashCardQuiz(){



        JPanel bckgrd = new JPanel();
        bckgrd.setBackground(Color.WHITE);

        host.setSize(new Dimension(500,580));
        host.setResizable(false);
        host.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Adding buttons
        for(JButton butts: ui.button)
            host.add(butts);
        ui.button[1].addActionListener(e -> exitInterface.process());
        ui.button[0].addActionListener(e -> startGaming.process());

        //Adding the components on the frame
       host.add(ui.welcomeScreen);

       //Adding all components used by the WordWars UI
        host.add(ui.words.ok);
        host.add(ui.words.gameIntro);
       for(JButton wordButtons: ui.words.subAndPass)
       host.add(wordButtons);
       for(JTextField inputs: ui.words.userInputs)
       host.add(inputs);
       for(JLabel textView: ui.words.textView)
       host.add(textView);
       for(JLabel wordsLayout: ui.words.layoutComponents)
       host.add(wordsLayout);


       //Adding all components used by the MathQuiz UI
       host.add(ui.math.ok);
       host.add(ui.math.gameIntro);
       for(JTextField factor: ui.math.factorSettings)
       host.add(factor);
       for(JLabel textViews: ui.math.textView)
       host.add(textViews);
       for(JTextField mathComponents: ui.math.settingsOptions)
       host.add(mathComponents);

       //Another method for the timer options
       for(int i = 4;i<7;i++){
           ui.math.settingsOptions[i].addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               timerChanges(e);
           }
       });}

       for(JLabel mathLayout: ui.math.layoutComponents)
       host.add(mathLayout);
       for(JLabel mainComponents: ui.components)
       host.add(mainComponents);


       //The common display that will be used between the two programs
       host.add(ui.testDisplay);
       //Opening the testDisplay for the user
       ui.testDisplay.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                testDisplayPressed(e);
            }
        });

       //Adding the timer
       mathQuizTimer.timer = new Timer(1000,e -> startTimingProcess.process());
       wordsTimer.timer = new Timer(1000,e -> wordsGameTimerDisplay.process());

       //Adding the background color
       host.add(bckgrd);

        QuizUI.centerFrame.accept(host);
        host.setVisible(true);

    }

    //Exiting the interface
    private Procedure exitInterface = () ->{

        if(ui.button[1].getText().equalsIgnoreCase("exit")){
            System.exit(0);
        }
        else if(ui.button[1].getText().equalsIgnoreCase("exit quiz")){
            var response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(response == JOptionPane.YES_OPTION) {
                new FlashCardQuiz().ui.viewMenuPage.process();
                host.dispose();
            }
            else{ return;}
        }
        else if(ui.button[1].getText().equalsIgnoreCase("exit game")){

            var response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(response == JOptionPane.YES_OPTION) {
                new FlashCardQuiz().ui.viewMenuPage.process();
                host.dispose();
            }
            else{ return;}
        }
    };

    private Procedure startGaming = () -> {

        //local members to keep track of score and produce message of results
        int score;
        String message;

        //For the Math Quiz gameplay
        if(ui.button[0].getText().equalsIgnoreCase("start quiz")){
            ui.testDisplay.setEditable(true);
            ui.button[0].setText("STOP QUIZ");
            ui.button[1].setEnabled(false);
            numberTried = 0;
            numberCorrect = 0;
            ui.math.textView[1].setText("0");
            ui.math.textView[2].setText("0");



            ui.testDisplay.setText(getProblem());

            if(!ui.math.settingsOptions[11].isVisible()){

                if(ui.math.settingsOptions[12].isVisible()){
                    problemTime = 0;
                }
                else{
                    problemTime = 30 * 60;
                }
                ui.math.textView[0].setText(mathQuizTimer.getTime(problemTime));
                mathQuizTimer.timer.start();
            }

            if(ui.math.settingsOptions[11].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                    //ui.math.settingsOptions[i].
                }

                for(int i = 11; i<14; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                }
                ui.math.settingsOptions[11].setVisible(true);
            }
            if(ui.math.settingsOptions[12].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                    //ui.math.settingsOptions[i].
                }

                for(int i = 11; i<14; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                }
                ui.math.settingsOptions[12].setVisible(true);
            }
            if(ui.math.settingsOptions[13].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                    //ui.math.settingsOptions[i].
                }

                for(int i = 11; i<14; i++){
                    ui.math.settingsOptions[i].setVisible(false);
                }
                ui.math.settingsOptions[13].setVisible(true);
            }
        }
        else if(ui.button[0].getText().equalsIgnoreCase("stop quiz")){

            if(ui.math.settingsOptions[11].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(true);
                    //ui.math.settingsOptions[i].
                }
                ui.math.settingsOptions[4].setVisible(false);
                ui.math.settingsOptions[11].setVisible(true);

            }
            if(ui.math.settingsOptions[12].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(true);
                    //ui.math.settingsOptions[i].
                }
                ui.math.settingsOptions[5].setVisible(false);
                ui.math.settingsOptions[12].setVisible(true);
            }
            if(ui.math.settingsOptions[13].isVisible()){
                for(int i = 4; i<7; i++){
                    ui.math.settingsOptions[i].setVisible(true);
                    //ui.math.settingsOptions[i].
                }
                ui.math.settingsOptions[6].setVisible(false);
                ui.math.settingsOptions[13].setVisible(true);
            }

            mathQuizTimer.timer.stop();

            ui.testDisplay.setEditable(false);
            ui.button[0].setText("START QUIZ");
            ui.button[1].setEnabled(true);

            if(numberTried > 0){

                score = (int)(100*(double)(numberCorrect)/numberTried);
                message = "Problems Tried: "+ String.valueOf(numberTried) + "\n";
                message += "Problems Correct: "+ String.valueOf(numberCorrect)+ " (" + String.valueOf(score)+ " %)"+"\n";

                if(ui.math.settingsOptions[11].isVisible()){
                    message+= "Timer Off";
                }
                else{
                    if(ui.math.settingsOptions[13].isVisible()){

                        problemTime = 30 * 60 - problemTime;
                    }

                    message += "Elapsed Time: "+ mathQuizTimer.getTime(problemTime) + "\n";
                    message += "Time Per Problem: "+ new DecimalFormat("0.00").format((double)(problemTime)/numberTried).replace(",",".") + " sec";
                }

                JOptionPane.showConfirmDialog(null, message,"Results",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }
        }

        //For the Word Wars gameplay
        else if(ui.button[0].getText().equalsIgnoreCase("start new game")){

            startTime = System.currentTimeMillis();
            ui.words.textView[3].setText("0");
            ui.words.textView[4].setText("0");
            ui.words.player1Score = 0;
            ui.words.player2Score = 0;
            ui.words.wordKeep.clear();
            ui.testDisplay.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");


            var player1Name = ui.words.userInputs[0].getText();
            var player2Name = ui.words.userInputs[1].getText();

            if(player1Name.length() == 0 || player2Name.length() == 0){
                JOptionPane.showConfirmDialog(null, "BOTH players must enter their names before starting the game",
                        "Names",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                ui.words.userInputs[0].requestFocus();
                return;
            }

            else{
                wordsTimer.timer.start();

                ui.words.userInputs[0].setText(player1Name);
                ui.words.userInputs[0].setEditable(false);
                ui.words.userInputs[1].setText(player2Name);
                ui.words.userInputs[1].setEditable(false);

                ui.button[1].setEnabled(false);
                ui.button[0].setText("STOP GAME");

                for (int i = 0; i < 2; i++) {
                    ui.words.subAndPass[i].setEnabled(true);
                    ui.words.subAndPass[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                }
                for (int i = 2; i < 4; i++) {
                    ui.words.subAndPass[i].setEnabled(false);
                    //ui.words.subAndPass[i].setBackground(Colors.GAME_GRAY.getColor());
                }

                ui.words.userInputs[2].requestFocus();
                ui.words.userInputs[3].setText("<<< "+player1Name+"'s Round");
                ui.words.userInputs[3].setEditable(false);
                ui.words.userInputs[2].setText("");
                ui.words.userInputs[2].setEditable(true);
                ui.words.userInputs[3].setFont(new Font("Calibri", Font.BOLD, 12));
            }



        }
        else if(ui.button[0].getText().equalsIgnoreCase("stop game")){

            message = "";
            wordsTimer.timer.stop();


            ui.words.userInputs[0].setEditable(true);
            ui.words.userInputs[1].setEditable(true);

            ui.button[1].setEnabled(true);
            ui.button[0].setText("START NEW GAME");

            for (int i = 0; i < 4; i++) {
                ui.words.subAndPass[i].setEnabled(false);
                ui.words.subAndPass[i].setBackground(Colors.GAME_GRAY.getColor());
            }
            for(int i = 2; i<4; i++){
                ui.words.userInputs[i].setText("");
                ui.words.userInputs[i].setEditable(false);
            }


            if(!ui.words.wordKeep.isEmpty()){
                if (ui.words.player1Score == ui.words.player2Score) {

                    message += "Winner: " + "\t" + "\tTie" + "\n";
                    message += "Total Words Played: " + "\t" + ui.words.wordKeep.size() + "\n";
                    message += "Total Game Time: " + ui.words.textView[2].getText();
                }
                if (ui.words.player1Score > ui.words.player2Score){

                    message += "Winner: "+"\t"+ui.words.userInputs[0].getText()+"\n";
                    message += "Words played: "+"\t"+ui.words.player1Score+"\n";
                    message += "Winning Word: "+"\t"+ui.words.wordKeep.get(ui.words.wordKeep.size() -1)+"\n";
                    message += "Total Game Time: "+"\t"+ui.words.textView[2].getText();
                }
                if (ui.words.player1Score < ui.words.player2Score){

                    message += "Winner: "+"\t"+ui.words.userInputs[1].getText()+"\n";
                    message += "Words played: "+"\t"+ui.words.player2Score+"\n";
                    message += "Winning Word: "+"\t"+ui.words.wordKeep.get(ui.words.wordKeep.size() -1)+"\n";
                    message += "Total Game Time: "+"\t"+ui.words.textView[2].getText();

                }

                JOptionPane.showConfirmDialog(null, message, "Results", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    };

    //Methods needed for the Math Quiz gameplay
    private void testDisplayPressed(KeyEvent e){

        if(ui.button[0].getText().equalsIgnoreCase("start quiz"))
            return;
        //Only allow number keys
        if(e.getKeyChar() >='0' && e.getKeyChar() <='9'){

            yourAnswer += e.getKeyChar();
            ui.testDisplay.setText(problem + yourAnswer);


            if(digitNumber != numberDigit){
                digitNumber++;
                var innerText = ui.testDisplay.getText();
                ui.testDisplay.setText(innerText);
                return;
            }
            else{
                numberTried++;
                //Check answer
                if(Integer.valueOf(yourAnswer).intValue() == correctAnswer){
                    numberCorrect++;
                }
                ui.math.textView[1].setText(String.valueOf(numberTried));
                ui.math.textView[2].setText(String.valueOf(numberCorrect));
                ui.testDisplay.setText(getProblem());

            }
        }
    }
    private String getProblem(){

        int pType,p,number,factor;
        p = 0;

        do{
            pType = mathQuizRandom.nextInt(4)+1;
            if(pType == 1 && ui.math.settingsOptions[7].isVisible()){

                //Addition
                p = pType;
                number = mathQuizRandom.nextInt(10);
                factor = getFactor(1);
                correctAnswer = number + factor;
                problem = String.valueOf(number)+ " + "+String.valueOf(factor)+ " = ";
            }
            else if(pType == 2 && ui.math.settingsOptions[8].isVisible()){

                //Subtraction
                p = pType;
                factor = getFactor(2);
                correctAnswer = mathQuizRandom.nextInt(10);
                number = correctAnswer + factor;
                problem = String.valueOf(number)+ " - "+String.valueOf(factor)+ " = ";
            }
            else if(pType == 3 && ui.math.settingsOptions[9].isVisible()){

                //Multiplication
                p = pType;
                number = mathQuizRandom.nextInt(10);
                factor = getFactor(3);
                correctAnswer = number * factor;
                problem = String.valueOf(number)+ " x "+String.valueOf(factor)+ " = ";
            }
            else if(pType == 4 && ui.math.settingsOptions[10].isVisible()){

                //Division
                p = pType;
                factor = getFactor(4);
                correctAnswer = mathQuizRandom.nextInt(10);
                number = correctAnswer * factor;
                problem = String.valueOf(number)+ " / "+String.valueOf(factor)+ " = ";

            }

        }while(p == 0);

        yourAnswer = "";
        digitNumber = 1;
        ui.testDisplay.requestFocus();

        if(correctAnswer < 10){
            numberDigit = 1;
            return (problem);
        }
        else{
            numberDigit = 2;
            return(problem);
        }
    }
    private int getFactor(int p){

        if(ui.math.factorSettings[21].isVisible()){

            //random
            if(p == 4)
                return (mathQuizRandom.nextInt(9) + 1);
            else
                return(mathQuizRandom.nextInt(10));

        }
        else{

                if(ui.math.factorSettings[11].isVisible()){
                    return (0);
                }
                if(ui.math.factorSettings[12].isVisible()){
                    return (1);
                }
                if(ui.math.factorSettings[13].isVisible()){
                    return (2);
                }
                if(ui.math.factorSettings[14].isVisible()){
                    return (3);
                }
                if(ui.math.factorSettings[15].isVisible()){
                    return (4);
                }
                if(ui.math.factorSettings[16].isVisible()){
                    return (5);
                }
                if(ui.math.factorSettings[17].isVisible()){
                    return (6);
                }
                if(ui.math.factorSettings[18].isVisible()){
                    return (7);
                }
                if(ui.math.factorSettings[19].isVisible()){
                    return (8);
                }
                if(ui.math.factorSettings[20].isVisible()){
                    return (9);
                }
                else{
                return (0);
            }
        }

    }
    private void timerChanges(MouseEvent e){

        if(e.getSource() == ui.math.settingsOptions[4]){
            ui.math.textView[0].setText("--:--");
        }
        if(e.getSource() == ui.math.settingsOptions[5]){
            problemTime = 0;
            ui.math.textView[0].setText(mathQuizTimer.getTime(problemTime));
        }
        if(e.getSource() == ui.math.settingsOptions[6]){
            problemTime = 30*60;
            ui.math.textView[0].setText(mathQuizTimer.getTime(problemTime));
        }

    }

    private Procedure startTimingProcess = () ->{

        if(ui.math.settingsOptions[12].isVisible()){
            problemTime++;
            ui.math.textView[0].setText(mathQuizTimer.getTime(problemTime));

            if(problemTime >= 1800){
                ui.button[0].doClick();
                return;
            }
        }

        else{
            problemTime--;
            ui.math.textView[0].setText(mathQuizTimer.getTime(problemTime));

            if(problemTime == 0){
                ui.button[0].doClick();
                return;
            }
        }

    };
    private Procedure wordsGameTimerDisplay = () ->{
        var currentTime = System.currentTimeMillis();
        ui.words.textView[2].setText(wordsTimer.HMSdisplay(currentTime - startTime));
    };

    public static void main(String... quiz){ new FlashCardQuiz();}
}
