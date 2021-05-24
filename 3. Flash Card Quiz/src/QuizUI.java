import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class QuizUI {

    JLabel welcomeScreen = FlashQuizComponents.WELCOME_SCREEN.getComponent();
    JLabel[] components;
    JTextField testDisplay;
    JButton[] button;


    Math math = new Math();
    WordWars words = new WordWars();

    public QuizUI(){

        //Setting the welcomeScreen
        welcomeScreen.setBounds(0,-25,500,580);

        //Populating the button array
        button = new JButton[]{new JButton("NEXT"), new JButton("EXIT")};
        setMainButtons.process();
        button[0].addActionListener(e -> viewMenuPage.process());

        //Populating the component array
        components = new JLabel[]{FlashQuizComponents.BUTTON_SHAD.getComponent(),

                                //For the Intro/Menu Page
                                 FlashQuizComponents.NOTIFICATION_PANE.getComponent(),
                                 FlashQuizComponents.WORD_WARS_PANE.getComponent(),
                                 FlashQuizComponents.MATH_QUIZ_PANE.getComponent()
                                 };

        setMainComponents.process();

        testDisplay = new JTextField();

        //MathQuiz And WordWars interface
        for(int i = 2; i<4; i++){
            components[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    showGameIntro(e);
                }
            });
        }

    }

    Procedure setMainButtons = () ->{

        //First button view on Welcome screen
        button[0].setForeground(Colors.ORANGEISH.getColor());
        button[0].setBackground(Color.WHITE);
        button[0].setBorder(BorderFactory.createLineBorder(Color.WHITE,0,true));
        button[0].setHorizontalAlignment(SwingConstants.CENTER);
        button[0].setFocusable(true);

        button[0].setFont(new Font("Calibri", Font.BOLD,20));
        button[0].setBounds(156,480,176,38);

        //Exit Button settings
        button[1].setForeground(Color.WHITE);
        button[1].setBackground(Colors.DARKER_ORANGE.getColor());
        button[1].setBorder(BorderFactory.createLineBorder(Colors.ORANGEISH.getColor(),0,true));
        button[1].setHorizontalAlignment(SwingConstants.CENTER);
        button[1].setFocusable(true);

        button[1].setFont(new Font("Calibri", Font.PLAIN,15));
        button[1].setBounds(400,10,70,30);
    };
    Procedure setMainComponents = () ->{

        components[0].setBounds(124,450,240,104);

        //For the Intro/Menu Page
        components[1].setBounds(-5,0,500,580);
        components[3].setBounds(20,230,218,309);
        components[2].setBounds(250, 230, 218,309);
        for(int i = 1; i<4; i++){components[i].setVisible(false);}

    };

    //When the 'NEXT' button is pressed
    Procedure viewMenuPage = () ->{

        if(button[0].getText().equalsIgnoreCase("next")){
            welcomeScreen.setVisible(false);
            button[0].setVisible(false);
            button[1].setBackground(Colors.LIGHTER_ORANGE.getColor());
            components[0].setVisible(false);

            //For the Intro/Menu Page
            for (int i = 1; i < 4; i++) {
                components[i].setVisible(true);
            }
        }
    };
    //When exercise options are pressed
    Procedure disableMenuPage = () ->{
        for(int i = 1; i<4; i++){components[i].setVisible(false);}
    };

    private void showGameIntro(MouseEvent e){

        if(e.getSource() == components[3]){
            math.showGameIntro.process();
            disableMenuPage.process();
        }
        else if(e.getSource() == components[2]){
            words.showGameIntro.process();
            disableMenuPage.process();
        }
    }
    static Consumer<JFrame> centerFrame = (e) ->{

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        e.setBounds((int)(0.5*(screenSize.width - e.getWidth())),
                (int)(0.5*(screenSize.height - e.getHeight())),
                e.getWidth(), e.getHeight());
    };

    //Types of UI used within the applet
    class Math{

        JLabel[] layoutComponents;
        JTextField[] settingsOptions;
        JLabel[] textView;
        JTextField[] factorSettings;
        JLabel gameIntro;
        JButton ok;

        public Math(){

            gameIntro = FlashQuizComponents.MATH_INS.getComponent();
            ok = new JButton("CONTINUE");
            ok.addActionListener( e -> setUI.process());



            //Ensuring the layout labels are visible
            layoutComponents = new JLabel[]{
                    //For the MathUI
                    FlashQuizComponents.MATH_LOGO.getComponent(),
                    FlashQuizComponents.SEP_BAR.getComponent(),
                    FlashQuizComponents.SETTINGS_PANE.getComponent(),
                    FlashQuizComponents.RESULTS_BOX.getComponent(),
                    FlashQuizComponents.START_SHADOW.getComponent()
            };
            //Populating the settingOptions arr
            settingsOptions = new JTextField[]{
                    //Components for the 'type' pane
                    new JTextField("ADDITION"),
                    new JTextField("SUBTRACTION"),
                    new JTextField("MULTIPLICATION"),
                    new JTextField("DIVISION"),


                    //Components for the 'timer' pane
                    new JTextField("OFF"),
                    new JTextField("COUNT UP"),
                    new JTextField("COUNT DOWN"),

                    //Components for the selected 'type' pane
                    new JTextField("ADDITION"),
                    new JTextField("SUBTRACTION"),
                    new JTextField("MULTIPLICATION"),
                    new JTextField("DIVISION"),

                    //Components for the selected 'timer' pane
                    new JTextField("OFF"),
                    new JTextField("COUNT UP"),
                    new JTextField("COUNT DOWN")

            };

            factorSettings = new JTextField[]{
                    //Components for the 'factor' pane
                    new JTextField("0"),new JTextField("1"),
                    new JTextField("2"),new JTextField("3"),
                    new JTextField("4"),new JTextField("5"),
                    new JTextField("6"),new JTextField("7"),
                    new JTextField("8"),new JTextField("9"),
                    new JTextField("Random"),

                    //Components for the 'factor' pane when selected
                    new JTextField("0"),new JTextField("1"),
                    new JTextField("2"),new JTextField("3"),
                    new JTextField("4"),new JTextField("5"),
                    new JTextField("6"),new JTextField("7"),
                    new JTextField("8"),new JTextField("9"),
                    new JTextField("Random")
            };

            //Populating the textView Arr
            textView = new JLabel[]{
                    new JLabel("--:--"),
                    new JLabel("0"), new JLabel("0")
            };

            //Adding the mouse listener for the settings pane
            for(int i = 0; i<4; i++){
                settingsOptions[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    typeSettings(e);
                }
                });
            }
            for(int i = 7; i<11; i++){
                settingsOptions[i].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        typeSettings(e);
                    }
                });
            }
            for (JTextField factorSetting : factorSettings) {

                factorSetting.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        factorSettings(e);
                    }
                });
            }
            for(int i = 4; i<7; i++){
                settingsOptions[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        timerSettings(e);
                    }
                });
            }

        }

        Procedure setUI = ()->{
            gameIntro.setVisible(false);
            ok.setVisible(false);

            //this.setExit.process();
            this.setMathComponents.process();
            this.setSettingsOptions.process();
            this.setFactors.process();
            this.setMathTextField.process();
            this.setStartButton.process();
            this.setTextView.process();
        };
        //Setting the math UI
        Procedure setExit = ()->{
            button[1].setBounds(350,10,122,30);
            button[1].setText("EXIT QUIZ");
            button[1].setBackground(Colors.LIGHTER_ORANGE.getColor());
        };
        Procedure setMathComponents = () ->{

            layoutComponents[0].setBounds(10,15,80,18);
            layoutComponents[1].setBounds(17,216,449,11);
            layoutComponents[2].setBounds(-9,222,500,230);
            layoutComponents[3].setBounds(270,452,200,67);
            layoutComponents[4].setBounds(11,469,174,74);

        };
        Procedure setSettingsOptions = () ->{

            //Settings for the 'type' pane
            for(int i = 0; i<4; i++){

                settingsOptions[i].setBackground(Colors.FACTOR_PRESSED.getColor());
                settingsOptions[i].setForeground(Color.WHITE);
                settingsOptions[i].setFont(new Font("Calibri", Font.BOLD,12));
                settingsOptions[i].setHorizontalAlignment(SwingConstants.CENTER);
                settingsOptions[i].setEditable(false);
                settingsOptions[i].setFocusable(false);
                settingsOptions[i].setBorder(BorderFactory.createLineBorder(Colors.LIGHTER_ORANGE.getColor(),0,true));

            }
            settingsOptions[0].setVisible(false);
            settingsOptions[0].setBounds(29,275,113,25);
            settingsOptions[1].setBounds(29,305,113,25);
            settingsOptions[2].setBounds(29,335,113,25);
            settingsOptions[3].setBounds(29,365,113,25);
            for(int i = 7; i<11; i++){
                settingsOptions[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                settingsOptions[i].setForeground(Color.WHITE);
                settingsOptions[i].setFont(new Font("Calibri", Font.BOLD,12));
                settingsOptions[i].setHorizontalAlignment(SwingConstants.CENTER);
                settingsOptions[i].setEditable(false);
                settingsOptions[i].setFocusable(false);
                settingsOptions[i].setBorder(BorderFactory.createEmptyBorder());
                settingsOptions[i].setVisible(false);
            }
            settingsOptions[7].setVisible(true);
            settingsOptions[7].setBounds(29,275,113,25);
            settingsOptions[8].setBounds(29,305,113,25);
            settingsOptions[9].setBounds(29,335,113,25);
            settingsOptions[10].setBounds(29,365,113,25);

            //Settings for the 'timer' pane
            for(int i = 4; i<7; i++){
                settingsOptions[i].setBackground(Colors.FACTOR_PRESSED.getColor());
                settingsOptions[i].setForeground(Color.WHITE);
                settingsOptions[i].setFont(new Font("Calibri", Font.BOLD,12));
                settingsOptions[i].setHorizontalAlignment(SwingConstants.CENTER);
                settingsOptions[i].setEditable(false);
                settingsOptions[i].setFocusable(false);
                settingsOptions[i].setBorder(BorderFactory.createEmptyBorder());
            }
            settingsOptions[4].setVisible(false);
            settingsOptions[4].setBounds(341,285,113,25);
            settingsOptions[5].setBounds(341,315,113,25);
            settingsOptions[6].setBounds(341,345,113,25);

            //when timer is selected
            for(int i = 11; i<14; i++){
                settingsOptions[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                settingsOptions[i].setForeground(Color.WHITE);
                settingsOptions[i].setFont(new Font("Calibri", Font.BOLD,12));
                settingsOptions[i].setHorizontalAlignment(SwingConstants.CENTER);
                settingsOptions[i].setEditable(false);
                settingsOptions[i].setFocusable(false);
                settingsOptions[i].setBorder(BorderFactory.createEmptyBorder());
                settingsOptions[i].setVisible(false);
            }
            settingsOptions[11].setVisible(true);
            settingsOptions[11].setBounds(341,285,113,25);
            settingsOptions[12].setBounds(341,315,113,25);
            settingsOptions[13].setBounds(341,345,113,25);



        };
        Procedure setFactors = () ->{
            //Settings for the 'factor' pane
            for(int i = 0; i<11; i++){

                factorSettings[i].setBackground(Colors.FACTOR_PRESSED.getColor());
                factorSettings[i].setForeground(Color.WHITE);
                factorSettings[i].setBorder(BorderFactory.createEmptyBorder());
                factorSettings[i].setFocusable(false);
                factorSettings[i].setEditable(false);
                factorSettings[i].setFont(new Font("Calibri", Font.BOLD, 18));
                factorSettings[i].setHorizontalAlignment(SwingConstants.CENTER);
            }
            factorSettings[10].setVisible(false);
            factorSettings[0].setBounds(179,278,41,30);
            factorSettings[1].setBounds(220,278,41,30);
            factorSettings[2].setBounds(261,278,41,30);
            factorSettings[3].setBounds(179,308,41,30);
            factorSettings[4].setBounds(220,308,41,30);
            factorSettings[5].setBounds(261,308,41,30);
            factorSettings[6].setBounds(179,338,41,30);
            factorSettings[7].setBounds(220,338,41,30);
            factorSettings[8].setBounds(261,338,41,30);
            factorSettings[9].setBounds(179,368,41,30);
            factorSettings[10].setBounds(220,368,82,30);


            //when factor is selected
            for(int i = 11; i<22; i++){
                factorSettings[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                factorSettings[i].setForeground(Color.WHITE);
                factorSettings[i].setBorder(BorderFactory.createEmptyBorder());
                factorSettings[i].setFocusable(false);
                factorSettings[i].setEditable(false);
                factorSettings[i].setFont(new Font("Calibri", Font.BOLD, 18));
                factorSettings[i].setHorizontalAlignment(SwingConstants.CENTER);
                factorSettings[i].setVisible(false);
            }
            factorSettings[21].setVisible(true);
            factorSettings[11].setBounds(179,278,41,30);
            factorSettings[12].setBounds(220,278,41,30);
            factorSettings[13].setBounds(261,278,41,30);
            factorSettings[14].setBounds(179,308,41,30);
            factorSettings[15].setBounds(220,308,41,30);
            factorSettings[16].setBounds(261,308,41,30);
            factorSettings[17].setBounds(179,338,41,30);
            factorSettings[18].setBounds(220,338,41,30);
            factorSettings[19].setBounds(261,338,41,30);
            factorSettings[20].setBounds(179,368,41,30);
            factorSettings[21].setBounds(220,368,82,30);


        };
        Procedure setMathTextField = () ->{
            testDisplay.setText("---");
            testDisplay.setBackground(Color.WHITE);
            testDisplay.setBorder(BorderFactory.createLineBorder(Colors.MY_GRAY.getColor(),2,true));
            testDisplay.setFont(new Font("Calibri", Font.BOLD,70));
            testDisplay.setForeground(Colors.LIGHTER_ORANGE.getColor());
            testDisplay.setHorizontalAlignment(SwingConstants.CENTER);
            testDisplay.setOpaque(true);
            testDisplay.setBounds(17,54,450,150);
        };
        Procedure setStartButton = () ->{

            button[0].setText("START QUIZ");
            button[0].setFont(new Font("Calibri", Font.PLAIN,15));
            button[0].setForeground(Color.WHITE);
            button[0].setBackground(Colors.LIGHTER_ORANGE.getColor());
            button[0].setBounds(35,470,129,34);
            button[0].setVisible(true);

        };
        Procedure setTextView = () ->{

            //Timer text
            textView[0].setFont(new Font("Calibri", Font.BOLD,20));
            textView[0].setForeground(Colors.MY_GRAY.getColor());
            textView[0].setHorizontalTextPosition(SwingConstants.CENTER);
            textView[0].setBounds(377,375,90,35);


            //Results text
            textView[1].setFont(new Font("Calibri", Font.PLAIN,15));
            textView[1].setForeground(Colors.DARKER_ORANGE.getColor());
            textView[1].setHorizontalTextPosition(SwingConstants.CENTER);
            textView[1].setBounds(363,451,200,67);

            textView[2].setFont(new Font("Calibri", Font.PLAIN,15));
            textView[2].setForeground(Colors.DARKER_ORANGE.getColor());
            textView[2].setHorizontalTextPosition(SwingConstants.CENTER);
            textView[2].setBounds(433,451,200,67);

        };
        Procedure showGameIntro = () ->{

            gameIntro.setBounds(-4,-25,500,580);
            gameIntro.setVisible(true);
            ok.setBackground(Colors.LIGHTER_ORANGE.getColor());
            ok.setForeground(Color.WHITE);
            ok.setFocusable(true);
            ok.requestFocus();
            ok.setBorder(BorderFactory.createEmptyBorder());
            ok.setHorizontalAlignment(SwingConstants.CENTER);
            ok.setFont(new Font("Calibri", Font.PLAIN, 15));
            ok.setBounds(302,482,99,32);
            ok.setVisible(true);
            setExit.process();
        };


       private void typeSettings(MouseEvent e){

           //Addition
           if(e.getSource() == settingsOptions[0]){
               settingsOptions[0].setVisible(false);
               settingsOptions[7].setVisible(true);
           }
           if(e.getSource() == settingsOptions[7]){
               settingsOptions[7].setVisible(false);
               settingsOptions[0].setVisible(true);
           }

           //Subtraction
           if(e.getSource() == settingsOptions[1]){
               settingsOptions[1].setVisible(false);
               settingsOptions[8].setVisible(true);
           }
           if(e.getSource() == settingsOptions[8]){
               settingsOptions[8].setVisible(false);
               settingsOptions[1].setVisible(true);
           }

           //Multiplication
           if(e.getSource() == settingsOptions[2]){
               settingsOptions[2].setVisible(false);
               settingsOptions[9].setVisible(true);
           }
           if(e.getSource() == settingsOptions[9]){
               settingsOptions[9].setVisible(false);
               settingsOptions[2].setVisible(true);
           }

           //Division
           if(e.getSource() == settingsOptions[3]){
               settingsOptions[3].setVisible(false);
               settingsOptions[10].setVisible(true);
               if(!settingsOptions[3].isVisible()){
                   factorSettings[0].setVisible(false);
                   factorSettings[11].setVisible(false);
               }


           }
           if(e.getSource() == settingsOptions[10]){
               settingsOptions[10].setVisible(false);
               settingsOptions[3].setVisible(true);
               if(settingsOptions[3].isVisible()){
                   factorSettings[0].setVisible(true);
                   //factorSettings[11].setVisible(true);
               }
           }


       }
       private void factorSettings(MouseEvent e){

           //For the '0' factor
           if(e.getSource() == factorSettings[0]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[0].setVisible(false);
               factorSettings[11].setVisible(true);
               if(factorSettings[11].isVisible()){
                   settingsOptions[10].setVisible(false);
                   settingsOptions[3].setVisible(true);
               }

               //To keep the initial options visible; from '1'
               for(int i = 1; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
           }
           //For the '1' factor
           if(e.getSource() == factorSettings[1]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[12].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[1].setVisible(false);
           }
           //For the '2' factor
           if(e.getSource() == factorSettings[2]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[13].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[2].setVisible(false);
           }
           //For the '3' factor
           if(e.getSource() == factorSettings[3]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[14].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[3].setVisible(false);
           }
           //For the '4' factor
           if(e.getSource() == factorSettings[4]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[15].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[4].setVisible(false);
           }
           //For the '5' factor
           if(e.getSource() == factorSettings[5]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[16].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[5].setVisible(false);
           }
           //For the '6' factor
           if(e.getSource() == factorSettings[6]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[17].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[6].setVisible(false);
           }
           //For the '7' factor
           if(e.getSource() == factorSettings[7]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[18].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[7].setVisible(false);
           }
           //For the '8' factor
           if(e.getSource() == factorSettings[8]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[19].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[8].setVisible(false);
           }
           //For the '9' factor
           if(e.getSource() == factorSettings[9]){
               for(int i = 11; i<22; i++){
                   factorSettings[i].setVisible(false);
               }

               factorSettings[20].setVisible(true);

               //To keep the initial options visible
               for(int i = 0; i<11; i++){
                   factorSettings[i].setVisible(true);
               }
               factorSettings[9].setVisible(false);
           }
           //For the 'Random' factor
           if(e.getSource() == factorSettings[10]){
                   for(int i = 11; i<22; i++){
                       factorSettings[i].setVisible(false);
                   }

                   factorSettings[10].setVisible(false);
                   factorSettings[21].setVisible(true);

                   //To keep the initial options visible
                   for(int i = 0; i<10; i++){
                       factorSettings[i].setVisible(true);
                   }
           }


       }
       private void timerSettings(MouseEvent e){

           //for the 'off' timer
           if(e.getSource() == settingsOptions[4]){
               for(int i = 11; i<14; i++){
                   settingsOptions[i].setVisible(false);
               }

               settingsOptions[4].setVisible(false);
               settingsOptions[11].setVisible(true);

               //To keep the initial options visible; from '5'
               for(int i = 5; i<7; i++){
                   settingsOptions[i].setVisible(true);
               }
           }
           //for the 'count down' timer
           if(e.getSource() == settingsOptions[5]){
               for(int i = 11; i<14; i++){
                   settingsOptions[i].setVisible(false);
               }

               settingsOptions[12].setVisible(true);

               //To keep the initial options visible; from '5'
               for(int i = 4; i<7; i++){
                   settingsOptions[i].setVisible(true);
               }

               settingsOptions[5].setVisible(false);

           }
           //for the 'count up' timer
           if(e.getSource() == settingsOptions[6]){
               for(int i = 11; i<14; i++){
                   settingsOptions[i].setVisible(false);
               }

               settingsOptions[13].setVisible(true);

               //To keep the initial options visible; from '5'
               for(int i = 4; i<7; i++){
                   settingsOptions[i].setVisible(true);
               }

               settingsOptions[6].setVisible(false);

           }

       }

    }
    class WordWars {

        JLabel gameIntro;
        JButton ok;
        JLabel[] layoutComponents;
        JTextField[] userInputs;
        JButton[] subAndPass;
        JLabel[] textView;
        ArrayList<String> wordKeep = new ArrayList<>();
        int player1Score, player2Score;


        public WordWars() {

            gameIntro = FlashQuizComponents.WORD_INS.getComponent();
            ok = new JButton("CONTNIUE");
            ok.addActionListener( e -> setUI.process());

            //Ensuring the layout labels are visible
            layoutComponents = new JLabel[]{
                    //For the WordWars UI
                    FlashQuizComponents.WORDS_LOGO.getComponent(),
                    FlashQuizComponents.PLAYER_PANE.getComponent(),
                    FlashQuizComponents.TIMER_BOX.getComponent(),
                    FlashQuizComponents.START_SHADOW.getComponent()
            };

            //Populating the textView Arr
            textView = new JLabel[]{
                    new JLabel("ENTER WORD"), new JLabel("ENTER WORD"),
                    new JLabel("--:--:--"), new JLabel("0"), new JLabel("0")
            };

            userInputs = new JTextField[]{new JTextField(), new JTextField(),
                    new JTextField(), new JTextField()};

            subAndPass = new JButton[]{new JButton("SUBMIT"), new JButton("PASS"),
                    new JButton("SUBMIT"), new JButton("PASS")};

            //Button methods for pass buttons
            subAndPass[1].addActionListener(e -> playerOnePass.process());
            subAndPass[3].addActionListener(e -> playerTwoPass.process());
            subAndPass[0].addActionListener(e -> playerOneSub.process());
            subAndPass[2].addActionListener(e -> playerTwoSub.process());
        }

        Procedure setUI = () -> {
            gameIntro.setVisible(false);
            ok.setVisible(false);

            //this.setExit.process();
            this.setStartButton.process();
            this.setTestDisplay.process();
            this.setWordWarsComponents.process();
            this.setTextView.process();
            this.setUserInputs.process();
            this.setSubAndPass.process();
        };
        //Setting the math UI
        Procedure setExit = () -> {
            button[1].setBounds(350, 10, 122, 30);
            button[1].setText("EXIT GAME");
            button[1].setBackground(Colors.LIGHTER_ORANGE.getColor());
        };
        Procedure setTestDisplay = () -> {
            testDisplay.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            testDisplay.setBackground(Color.WHITE);
            testDisplay.setBorder(BorderFactory.createLineBorder(Colors.GAME_GRAY.getColor(), 2, true));
            testDisplay.setFont(new Font("Calibri", Font.BOLD, 25));
            testDisplay.setForeground(Colors.LIGHTER_ORANGE.getColor());
            testDisplay.setHorizontalAlignment(SwingConstants.CENTER);
            testDisplay.setOpaque(true);
            testDisplay.setEditable(false);
            testDisplay.setBounds(17, 54, 450, 150);
        };
        Procedure setWordWarsComponents = () -> {
            layoutComponents[0].setBounds(15, 15, 80, 18);
            layoutComponents[1].setBounds(-7, 190, 500, 305);
            layoutComponents[2].setBounds(270, 452, 200, 67);
            layoutComponents[3].setBounds(11, 469, 174, 74);

        };
        Procedure setStartButton = () -> {
            button[0].setText("START NEW GAME");
            button[0].setFont(new Font("Calibri", Font.PLAIN, 15));
            button[0].setForeground(Color.WHITE);
            button[0].setBackground(Colors.LIGHTER_ORANGE.getColor());
            button[0].setBounds(35, 470, 129, 34);
            button[0].setVisible(true);
        };
        Procedure setTextView = () -> {

            for (int i = 0; i < 2; i++) {
                textView[i].setForeground(Colors.MY_GRAY.getColor());
                textView[i].setFont(new Font("Calibri", Font.BOLD, 10));
            }

            textView[0].setBounds(25, 243, 90, 20);
            textView[1].setBounds(259, 243, 90, 20);

            textView[2].setForeground(Colors.DARKER_ORANGE.getColor());
            textView[2].setFont(new Font("Calibri", Font.PLAIN, 15));
            textView[2].setBounds(380, 472, 50, 30);

            for (int i = 3; i < 5; i++) {
                textView[i].setForeground(Colors.DARKER_ORANGE.getColor());
                textView[i].setFont(new Font("Calibri", Font.BOLD, 30));
            }
            textView[3].setBounds(175, 385, 80, 50);
            textView[4].setBounds(410, 385, 80, 50);


        };
        Procedure setUserInputs = () -> {

            //userName inputs
            for (int i = 0; i < 2; i++) {

                userInputs[i].setBackground(Color.WHITE);
                userInputs[i].setForeground(Colors.MY_GRAY.getColor());
                userInputs[i].setBorder(BorderFactory.createEmptyBorder());
                userInputs[i].setHorizontalAlignment(SwingConstants.RIGHT);
                userInputs[i].setFont(new Font("Calibri", Font.BOLD, 13));
                userInputs[0].requestFocus();
            }

            userInputs[0].setBounds(94, 218, 130, 20);
            userInputs[1].setBounds(328, 218, 130, 20);

            //userWord inputs
            for (int i = 2; i < 4; i++) {
                userInputs[i].setBorder(BorderFactory.createLineBorder(Colors.MY_GRAY.getColor(), 1, true));
                userInputs[i].setBackground(Color.WHITE);
                userInputs[i].setForeground(Colors.MY_GRAY.getColor());
                userInputs[i].setFont(new Font("Calibri", Font.BOLD, 15));
                userInputs[i].setHorizontalAlignment(SwingConstants.CENTER);
                userInputs[i].setEditable(false);
            }
            userInputs[2].setBounds(25, 259, 200, 45);
            userInputs[3].setBounds(259, 259, 200, 45);


        };
        Procedure setSubAndPass = () -> {

            //For user gameplay
            for (JButton wordButtons : subAndPass) {

                wordButtons.setBackground(Colors.MY_GRAY.getColor());
                wordButtons.setForeground(Color.WHITE);
                wordButtons.setFocusable(true);
                wordButtons.setFont(new Font("Calibri", Font.BOLD, 12));
                wordButtons.setBorder(BorderFactory.createEmptyBorder());
                wordButtons.setEnabled(false);
            }

            subAndPass[0].setBounds(37, 322, 85, 30);
            subAndPass[1].setBounds(125, 322, 85, 30);
            subAndPass[2].setBounds(273, 322, 85, 30);
            subAndPass[3].setBounds(362, 322, 85, 30);
        };
        Procedure showGameIntro = () ->{

            gameIntro.setBounds(-4,-25,500,580);
            gameIntro.setVisible(true);
            ok.setBackground(Colors.LIGHTER_ORANGE.getColor());
            ok.setForeground(Color.WHITE);
            ok.setFocusable(true);
            ok.setBorder(BorderFactory.createEmptyBorder());
            ok.setHorizontalAlignment(SwingConstants.CENTER);
            ok.setFont(new Font("Calibri", Font.PLAIN, 15));
            ok.setBounds(302,482,99,32);
            ok.setVisible(true);
            setExit.process();
        };

        //Methods to change features during gameplay
        private String passMessage = "Are you sure you want to pass this round?";
        private Procedure playerOnePass = () -> {

            var response = JOptionPane.showConfirmDialog(null, passMessage,
                    "Pass Round", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < 2; i++) {
                    subAndPass[i].setEnabled(false);
                    subAndPass[i].setBackground(Colors.MY_GRAY.getColor());
                }
                for (int i = 2; i < 4; i++) {
                    subAndPass[i].setEnabled(true);
                    subAndPass[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                }

                userInputs[2].setText(userInputs[1].getText() + "'s Round >>>");
                userInputs[2].setEditable(false);
                userInputs[3].setText("");
                userInputs[3].setEditable(true);
                userInputs[3].requestFocus();
                userInputs[2].setFont(new Font("Calibri", Font.BOLD, 12));

            } else {
                return;
            }
        };
        private Procedure playerTwoPass = () -> {

            var response = JOptionPane.showConfirmDialog(null, passMessage,
                    "Pass Round", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < 2; i++) {
                    subAndPass[i].setEnabled(true);
                    subAndPass[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                }
                for (int i = 2; i < 4; i++) {
                    subAndPass[i].setEnabled(false);
                    subAndPass[i].setBackground(Colors.MY_GRAY.getColor());
                }

                userInputs[3].setText("<<< " + userInputs[0].getText() + "'s Round");
                userInputs[3].setEditable(false);
                userInputs[2].setText("");
                userInputs[2].setEditable(true);
                userInputs[2].requestFocus();
                userInputs[3].setFont(new Font("Calibri", Font.BOLD, 12));
            } else {
                return;
            }
        };
        private Procedure playerOneSub = () -> {
            subAndPass[2].setFocusable(true);
            //initial word submission
            var word = userInputs[2].getText();

            if(!wordKeep.contains(word)){

                var response = JOptionPane.showConfirmDialog(null, "Do both players agree with the word"+" '"+word+"' ?",
                        "Word Play",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){


                    wordKeep.add(word);
                    var innerChange = word.toUpperCase();

                    for(int i = 0; i<innerChange.length(); i++){
                        var c = innerChange.charAt(i);

                        if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                            continue;
                        }
                        else{
                            testDisplay.setText(testDisplay.getText().replace(String.valueOf(c),"-"));
                        }
                    }

                    for (int i = 0; i < 2; i++) {
                        subAndPass[i].setEnabled(false);
                        subAndPass[i].setBackground(Colors.MY_GRAY.getColor());
                    }
                    for (int i = 2; i < 4; i++) {
                        subAndPass[i].setEnabled(true);
                        subAndPass[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                    }

                    userInputs[2].setText(userInputs[1].getText() + "'s Round >>>");
                    userInputs[2].setEditable(false);
                    userInputs[3].setText("");
                    userInputs[3].setEditable(true);
                    userInputs[3].requestFocus();
                    userInputs[2].setFont(new Font("Calibri", Font.BOLD, 12));

                    textView[3].setText(String.valueOf(++player1Score));
                }

                else{
                    return;
                }
            }
            else{
                JOptionPane.showConfirmDialog(null, "This word has been played."+"\nPlay another one", "Word repeat",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        };
        private Procedure playerTwoSub = () -> {

            subAndPass[0].setFocusable(true);
            //initial word submission
            var word = userInputs[3].getText();

            if(!wordKeep.contains(word)){

                var response = JOptionPane.showConfirmDialog(null, "Do both players agree with the word"+" '"+word+"' ?",
                        "Word Play",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){

                    wordKeep.add(word);
                    var innerChange = word.toUpperCase();

                    for(int i = 0; i<innerChange.length(); i++){
                        var c = innerChange.charAt(i);

                        if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                            continue;
                        }
                        else{
                            testDisplay.setText(testDisplay.getText().replace(String.valueOf(c),"-"));
                        }
                    }

                    for (int i = 0; i < 2; i++) {
                        subAndPass[i].setEnabled(true);
                        subAndPass[i].setBackground(Colors.LIGHTER_ORANGE.getColor());
                    }
                    for (int i = 2; i < 4; i++) {
                        subAndPass[i].setEnabled(false);
                        subAndPass[i].setBackground(Colors.MY_GRAY.getColor());
                    }

                    userInputs[3].setText("<<< " + userInputs[0].getText() + "'s Round");
                    userInputs[3].setEditable(false);
                    userInputs[2].setText("");
                    userInputs[2].setEditable(true);
                    userInputs[2].requestFocus();
                    userInputs[3].setFont(new Font("Calibri", Font.BOLD, 12));

                    textView[4].setText(String.valueOf(++player2Score));
                }

                else{
                    return;
                }
            }
            else{
                JOptionPane.showConfirmDialog(null, "This word has been played."+"\nPlay another one", "Word repeat",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        };
    }
}
