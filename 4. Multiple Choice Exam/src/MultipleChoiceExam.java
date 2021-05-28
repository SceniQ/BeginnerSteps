import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Random;

public class MultipleChoiceExam extends Component {

    private JFrame host = new JFrame("Multiple Choice Exam");
    private ExamUI ui = new ExamUI();
    private MethodTools timer = new MethodTools();

    //Variables needed for reading chisen file
    String examTitle;
    String header1,header2;
    int numberTerms;
    String[] term1 = new String[100];
    String[] term2 = new String[100];

    //two class level variables to keep track of the number of questions
    //tried and the number correct
    int numberTried,numberCorrect;

    //class level variable to identify the array index of the correct answer and a random object to
    //generate random questions
    int correctAnswer;
    Random random = new Random();

    //Class variable for the timer effect
    long startTime;

    public MultipleChoiceExam(){

        host.setSize(415,517);
        host.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        host.setResizable(false);
        host.setVisible(true);

        //Making sure the main button is at the top layer
        host.add(ui.mainButton);
        ui.mainButton.addActionListener(e -> startStopExam.process());
        //Adding the mainExit
        host.add(ui.mainExit);
        //Adding the welcome screen
        host.add(ui.welcome);
        //Adding the menu Items
        for(JMenu menus: ui.openAndOptions)
        host.add(menus);
        //Adding all possible answer labels
        for(JLabel answers: ui.possibleAnswers)
        host.add(answers);
        for(int i = 1; i<5; i++)
        ui.possibleAnswers[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseAnswer(e);
            }
        });
        //Adding the subheadings
        for(JLabel headings: ui.subHeadings)
        host.add(headings);
        timer.timer = new Timer(1000,e ->timerDisplay.process());
        //Adding the text field for bottom answering/commentary
        host.add(ui.commentSection);
        //Including the inner buttons with custom icons
        for(JButton innerButtons: ui.innerControls)
        host.add(innerButtons);
        ui.innerControls[0].addActionListener(e -> openFile.process());
        ui.innerControls[2].addActionListener(e -> nextButton.process());
        //Starting with the background of the application
        host.add(ui.backGround);

        ui.centerFrame.accept(host);
    }

    private Procedure openFile = () ->{

        String myLine;

        JFileChooser open = new JFileChooser();
        open.setDialogType(JFileChooser.OPEN_DIALOG);
        open.setDialogTitle("Open Exam File");
        open.addChoosableFileFilter(new FileNameExtensionFilter("Exam Files","csv"));

        if(open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            try(BufferedReader inputFile = new BufferedReader(new FileReader(open.getSelectedFile()))){

                myLine = inputFile.readLine();
                examTitle = parseLeft(myLine);
                myLine = inputFile.readLine();
                header1 = parseLeft(myLine);
                header2 = parseRight(myLine);
                numberTerms = 0;

                do{
                    numberTerms++;
                    myLine = inputFile.readLine();
                    term1[numberTerms -1] = parseLeft(myLine);
                    term2[numberTerms -1] = parseRight(myLine);
                }while(inputFile.ready() && numberTerms < 100);
                if(numberTerms < 5){
                    JOptionPane.showConfirmDialog(null, "Must have at least 5 entries in exam file",
                            "Exam File Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ui.subHeadings[0].setText(examTitle);
                ui.subHeadings[1].setText(header1);
                ui.subHeadings[2].setText(header2);
                ui.commentSection.setText(ui.centerTextArea("File Loaded! Click Start below to start exam."));
                ui.mainButton.setText("START EXAM");

                if(ui.mainButton.getText().equalsIgnoreCase("stop exam")){
                    ui.innerControls[0].setEnabled(false);
                }

            }catch(Exception ex){

                JOptionPane.showConfirmDialog(null,"Error reading inout file -"+"\nMake sure file is correct format",
                        "Multiple Choicer File Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    };
    private Procedure startStopExam = () ->{

        if(ui.mainButton.getText().equalsIgnoreCase("continue")){
            ui.welcome.setVisible(false);
            ui.mainExit.setVisible(false);
            ui.setUI.process();
            ui.mainButton.setText("---");
            ui.commentSection.setFocusable(false);
        }
        else if(ui.mainButton.getText().equalsIgnoreCase("start exam")){

            startTime = System.currentTimeMillis();
            timer.timer.start();

            ui.mainButton.setText("STOP EXAM");
            ui.innerControls[2].setEnabled(false);
            numberTried = 0;
            numberCorrect = 0;
            ui.commentSection.setText("");
            ui.innerControls[0].setEnabled(false);

            this.nextQuestion.process();
        }
        else if(ui.mainButton.getText().equalsIgnoreCase("stop exam")){

            timer.timer.stop();

            var message = "";

            ui.mainButton.setText("START EXAM");
            ui.innerControls[2].setEnabled(false);
            ui.innerControls[0].setEnabled(true);

            if(numberTried > 0){

                message = "Questions Tried: "+String.valueOf(numberTried) + "\n";
                message += "Questions Correct: "+String.valueOf(numberCorrect)+ "\n";
                message += "Total Test Time: "+ui.subHeadings[3].getText()+ "\n\n";
                message += "Your Score: "+new DecimalFormat("0.0").format(100.0 *((double)numberCorrect/numberTried)).replace(",",".")+ " %";

                JOptionPane.showConfirmDialog(null,message,examTitle + " Results",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }

            for(int i = 0; i<5; i++){
                ui.possibleAnswers[i].setText("");
            }

            ui.commentSection.setText("Restart exam/ open a new file.");
        }

    };
    private Procedure nextQuestion = () ->{

        //ui.innerControls[2].setEnabled(true);

        boolean termUsed[] = new boolean[numberTerms];
        int[] index = new int[4];
        int j;

        ui.commentSection.setText("");
        //Generate then next question
        correctAnswer = random.nextInt(numberTerms);
        ui.possibleAnswers[0].setText(term1[correctAnswer]);

        //Multiple choice
        for(int i = 0; i<numberTerms; i++){

            termUsed[i] = false;
        }
        //Pick random possibilities
        for(int i = 0; i<4; i++){

            do{
                j = random.nextInt(numberTerms);
            }while(termUsed[j] || j == correctAnswer);
            termUsed[j] = true;
            index[i] = j;
        }
        //Replace one with correct answer
        index[random.nextInt(4)] = correctAnswer;
        //Display multiple choice answers in label boxes
        ui.possibleAnswers[1].setText(term2[index[0]]);
        ui.possibleAnswers[2].setText(term2[index[1]]);
        ui.possibleAnswers[3].setText(term2[index[2]]);
        ui.possibleAnswers[4].setText(term2[index[3]]);
    };
    private Procedure nextButton = () ->{

        //Generate next question
        ui.innerControls[2].setEnabled(false);
        nextQuestion.process();
    };

    private Procedure timerDisplay = () ->{
        var currentTime = System.currentTimeMillis();
        ui.subHeadings[3].setText(timer.HMSdisplay(currentTime - startTime));
    };

    //General methods to be used
    private String parseLeft(String s){
        var cl = s.indexOf(",");
        return(s.substring(0,cl));
    }
    private String parseRight(String s){
        var cl = s.indexOf(",");
        return(s.substring(cl +1));
    }
    private void chooseAnswer(MouseEvent e){

        var correct = false;
        int labelSelected;

        //Make sure exam has started and question has not been answered
        if(ui.mainButton.getText().equalsIgnoreCase("start exam") ||
            ui.innerControls[2].isEnabled())
            return;

            //Determine which label ws clicked
            //get upper left corner of clicked
            Point p = e.getComponent().getLocation();

            //Determine index based on p
            for(labelSelected = 1; labelSelected<21; labelSelected++){

                if(p.x == ui.possibleAnswers[labelSelected].getX() &&
                   p.y == ui.possibleAnswers[labelSelected].getY())
                    break;
            }
            //If already answered exit
            numberTried++;
            if(ui.possibleAnswers[labelSelected].getText().equals(term2[correctAnswer]))
                correct = true;

            updateScore(correct);

    }
    private void updateScore(boolean correct){



        //Check if answer is correct
        if(correct){
            ui.commentSection.setForeground(Colors.EXAM_LIGHTBLUE.getColor());
          numberCorrect++;
          ui.commentSection.setText("Correct!");
        }
        else{
            ui.commentSection.setForeground(Colors.EXAM_DARKBLUE.getColor());
            ui.commentSection.setText(ui.centerTextArea("Oops... Correct answer is shown"));
        }

        //Displaying the correct answer
        ui.possibleAnswers[1].setText(term2[correctAnswer]);
        ui.possibleAnswers[2].setText("");
        ui.possibleAnswers[3].setText("");
        ui.possibleAnswers[4].setText("");


        ui.mainButton.setEnabled(true);
        ui.innerControls[2].setEnabled(true);
        ui.innerControls[2].requestFocus();
    }

    public static void main(String... exam){
        new MultipleChoiceExam();
    }
}
