import javax.swing.*;
import java.awt.*;

//For the button colors
enum Colors {

    YELLOW(254, 244, 51),
    GREEN(10, 105, 81),
    BLUE(17, 10, 106),
    PURPLE(88, 9, 92),
    RED(211, 31, 37),
    ORANGE(248, 109, 28),
    BROWNISH(219, 164, 108),
    BLUEISH_WHITE(217, 218, 220),
    TEXT_BORDER(183,184,185),
    MY_GRAY(153,153,153),

    ORANGEISH(199,145,42),
    DARKER_ORANGE(181,119,31),
    LIGHTER_ORANGE(247,192,86),
    GAME_GRAY(210,210,210),
    FACTOR_DEF(251,226,195),
    FACTOR_PRESSED(249,210,153),

    EXAM_BLUE(54,72,95),
    EXAM_LIGHTBLUE(107,221,201),
    EXAM_DARKBLUE(40,54,71);

    private int r, g, b;

    Colors(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color getColor(){
        return new Color(r, g, b);
    }
}

enum StopwatchButtonShadPal {

    //Shadow file names and their location on the drive(or within the package)
    DEFAULT_SHAD(new ImageIcon("defaultShad.png")),
    YELLOW_SHAD(new ImageIcon("yellowShad.png")),
    GREEN_SHAD(new ImageIcon("greenShad.png")),
    BLUE_SHAD(new ImageIcon("blueShad.png")),
    PURPLE_SHAD(new ImageIcon("purpleShad.png")),
    RED_SHAD(new ImageIcon("redShad.png")),
    ORANGE_SHAD(new ImageIcon("orangeShad.png"));

    private ImageIcon imgLoc;

    StopwatchButtonShadPal(ImageIcon imgLoc){
        this.imgLoc = imgLoc;
    }

    public ImageIcon getShadow(){
        return imgLoc;
    }
}

enum RadioButtonTheme {

    selector(new ImageIcon("selector.png")),
    yellowChoice(new ImageIcon("yellowChoice.png")),
    greenChoice(new ImageIcon("greenChoice.png")),
    blueChoice(new ImageIcon("blueChoice.png")),
    purpleChoice(new ImageIcon("purpleChoice.png")),
    redChoice(new ImageIcon("redChoice.png")),
    orangeChoice(new ImageIcon("orangeChoice.png"));

    private ImageIcon colorType;

    RadioButtonTheme(ImageIcon colorType){
        this.colorType = colorType;
    }

    public ImageIcon getColorType(){
        return colorType;
    }
}

enum LoanFunctionComponents {

    BACKGROUND(new ImageIcon("background.png")),
    LOGO(new ImageIcon("logo.png")),
    PROFILE_ICON(new ImageIcon("profIcon.png")),
    EXIT_ICON(new ImageIcon("exitIcon.png")),
    RESULT_DISPLAY(new ImageIcon("resultDisplay.png")),
    SUBTITLE(new ImageIcon("subTitle.png")),
    LINE_SEP(new ImageIcon("lineSep.png")),
    BUTT_SHAD(new ImageIcon("buttShad.png")),
    COMPUTE(new ImageIcon("computeButt.png")),
    CANCEL(new ImageIcon("cancelButt.png"));

    private ImageIcon imgLoc;

    LoanFunctionComponents(ImageIcon imgLoc){
        this.imgLoc = imgLoc;
    }

    JLabel getComponent(){
        return new JLabel(imgLoc);
    }
}

enum FlashQuizComponents{

    WELCOME_SCREEN(new ImageIcon("WelcomeScreen.png")),
    BUTTON_SHAD(new ImageIcon("buttonShadow.png")),

    //For the Intro/Menu page
    NOTIFICATION_PANE(new ImageIcon("notificationPane.png")),
    MATH_QUIZ_PANE(new ImageIcon("mathQuizPane.png")),
    WORD_WARS_PANE(new ImageIcon("wordWarsPane.png")),

    //For the MathUI
    MATH_LOGO(new ImageIcon("mathQuizLogo.png")),
    SEP_BAR(new ImageIcon("sepBar.png")),
    SETTINGS_PANE(new ImageIcon("mathSettingsPane.png")),
    RESULTS_BOX(new ImageIcon("mathResBox.png")),
    START_SHADOW(new ImageIcon("startShadow.png")),

    //For the math UI
    ADD_OPT(new ImageIcon("mathQuizAddButt.png")),
    SUB_OPT(new ImageIcon("mathQuizSubButt.png")),
    DIV_OPT(new ImageIcon("mathQuizDivButt.png")),
    MULTI_OPT(new ImageIcon("mathQuizMultiButt.png")),

    BACK(new ImageIcon("mathBack.png")),
    FORWARD(new ImageIcon("mathForward.png")),

    OFF(new ImageIcon("mathQuizTimerOff.png")),
    COUNT_UP(new ImageIcon("mathQuizTimerCountU.png")),
    COUNT_DOWN(new ImageIcon("mathQuizTimerCountD.png")),

    //For the WordWars UI
    WORDS_LOGO(new ImageIcon("wordWarsLogo.png")),
    PLAYER_PANE(new ImageIcon("wordWarsPlayerBox.png")),
    TIMER_BOX(new ImageIcon("wordWarsTimerBox.png")),
    MATH_INS(new ImageIcon("mathQuizInstructions.png")),
    WORD_INS(new ImageIcon("wordWarsInstructions.png"));


    private ImageIcon imgLoc;

    FlashQuizComponents(ImageIcon imgLoc){
        this.imgLoc = imgLoc;
    }

    public JLabel getComponent(){
        return new JLabel(imgLoc);
    }
}

enum ChoiceExam{

    WELCOME(new ImageIcon("welcomeScreen.png")),
    BACKGROUND(new ImageIcon("background.png")),
    DEF_A(new ImageIcon("defAnswerA.png")),
    CORR_A(new ImageIcon("corrAnswerA.png")),
    DEF_B(new ImageIcon("defAnswerB.png")),
    CORR_B(new ImageIcon("corrAnswerB.png")),
    DEF_C(new ImageIcon("defAnswerC.png")),
    CORR_C(new ImageIcon("corrAnswerC.png")),
    DEF_D(new ImageIcon("defAnswerD.png")),
    CORR_D(new ImageIcon("corrAnswerD.png"));

    private ImageIcon imgLoc;

    ChoiceExam(ImageIcon imgLoc){
        this.imgLoc = imgLoc;
    }

    public JLabel getComp(){
        return new JLabel(imgLoc);
    }

}

