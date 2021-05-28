import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.function.Consumer;

public class ExamUI {

    JButton mainExit;
    JLabel welcome;
    JLabel backGround;
    JButton mainButton;
    JButton[] innerControls;
    JTextField commentSection;
    JLabel[] possibleAnswers;
    JLabel[] subHeadings;
    JMenu[] openAndOptions;

    public ExamUI(){

        mainExit = new JButton("EXIT");
        welcome = ChoiceExam.WELCOME.getComp();
        backGround = ChoiceExam.BACKGROUND.getComp();
        mainButton = new JButton("CONTINUE");

        innerControls = new JButton[]{new JButton(new ImageIcon("open.png")),
                        new JButton(new ImageIcon("close.png")),
                        new JButton(new ImageIcon("next.png"))};

        innerControls[1].addActionListener(e -> System.exit(-1));

        commentSection = new JTextField(centerTextArea("Open file to choose which exam to take."));

        subHeadings = new JLabel[]{new JLabel(""),new JLabel("---"),
                                    new JLabel("---"), new JLabel("--:--:--")};

        possibleAnswers = new JLabel[]{ new JLabel(""),
                                        new JLabel("---"),new JLabel("---"),
                                        new JLabel("---"),new JLabel("---"),
                                        ChoiceExam.DEF_A.getComp(), ChoiceExam.DEF_B.getComp(),
                                        ChoiceExam.DEF_C.getComp(), ChoiceExam.DEF_D.getComp()};
        openAndOptions = new JMenu[]{ new JMenu(""),new JMenu("")};

        setMainButton.run();
        setWelcome.run();
    }

    Procedure setUI = () ->{

        backGround.setBounds(0,0,400,480);
        new Thread(this.setInnerControls).run();
        new Thread(this.setCommentSection).run();
        new Thread(this.setSubHeadings).run();
        new Thread(this.setPossibleAnswers).run();

    };

    private Runnable setWelcome = () ->{

        mainExit.setForeground(Color.WHITE);
        mainExit.addActionListener(e -> System.exit(-1));
        mainExit.setBackground(Colors.EXAM_BLUE.getColor());
        mainExit.setFont(new Font("Josefin Sans", Font.TYPE1_FONT,13));
        mainExit.setHorizontalAlignment(SwingConstants.CENTER);
        mainExit.setBorder(BorderFactory.createEmptyBorder());
        mainExit.setFocusable(false);
        mainExit.setBounds(136,2,120,18);
        welcome.setBounds(0,0,400,480);
    };
    private Runnable setMainButton = () ->{
        mainButton.setFont(new Font("Josefin Sans",Font.TYPE1_FONT,14));
        mainButton.setBounds(108,438,170,35);
        mainButton.setBackground(Colors.EXAM_LIGHTBLUE.getColor());
        mainButton.setForeground(Color.WHITE);
        mainButton.setBorder(BorderFactory.createEmptyBorder());
        mainButton.setFocusable(false);
    };
    private Runnable setInnerControls = () ->{
        for(int i = 2; i<3; i++){
            innerControls[i].setBackground(Colors.EXAM_LIGHTBLUE.getColor());
            innerControls[i].setBorder(BorderFactory.createEmptyBorder());
            innerControls[i].setForeground(Color.WHITE);
            innerControls[i].setFont(new Font("Josefin Sans light",Font.TYPE1_FONT,11));
            innerControls[i].setFocusable(false);

        }
        for(int i = 0; i<2; i++){
            innerControls[i].setBackground(Colors.EXAM_DARKBLUE.getColor());
            innerControls[i].setForeground(Color.WHITE);
            innerControls[i].setBorder(BorderFactory.createEmptyBorder());
            innerControls[i].setFont(new Font("Josefin Sans Bold",Font.PLAIN,15));
            innerControls[i].setFocusable(false);
        }

        innerControls[0].setBounds(7,7,32,32);
        innerControls[1].setBounds(359,7,32,32);
        innerControls[2].setBounds(376,246,20,43);
        innerControls[2].setEnabled(false);

    };
    private Runnable setCommentSection = () ->{
        commentSection.setBackground(new Color(79,91,109));
        commentSection.setForeground(Colors.EXAM_DARKBLUE.getColor());
        commentSection.setFont(new Font("Gotham Rounded", Font.TYPE1_FONT,12));
        commentSection.setBorder(BorderFactory.createEmptyBorder());
        commentSection.setHorizontalAlignment(SwingConstants.CENTER);
        commentSection.setEditable(false);
        commentSection.setBounds(20,380,355,40);
    };
    private Runnable setSubHeadings = () ->{

        for(JLabel headings: subHeadings){
            headings.setForeground(new Color(191,193,197));
            headings.setFont(new Font("Josefin Sans Bold",Font.TYPE1_FONT,14));
        }
        subHeadings[0].setBounds(148,26,120,15);
        subHeadings[0].setFont(new Font("Gotham Rounded", Font.TYPE1_FONT,14));
        subHeadings[0].setForeground(Colors.EXAM_LIGHTBLUE.getColor());
        subHeadings[1].setBounds(22,65,80,15);
        subHeadings[2].setBounds(22,155,80,15);

        subHeadings[3].setForeground(Color.WHITE);
        subHeadings[3].setFont(new Font("Josefin Sans", Font.TYPE1_FONT,13));
        subHeadings[3].setHorizontalAlignment(SwingConstants.CENTER);
        subHeadings[3].setBounds(137,1,120,18);
    };
    private Runnable setPossibleAnswers = () ->{
        //Answer label texts
        for(int i = 0; i<5; i++){
            possibleAnswers[i].setFont(new Font("Josefin Sans", Font.TYPE1_FONT,15));
            possibleAnswers[i].setForeground(new Color(191,193,197));
        }
        possibleAnswers[0].setFont(new Font("Josefin Sans",Font.TYPE1_FONT,25));
        possibleAnswers[0].setHorizontalAlignment(SwingConstants.CENTER);
        possibleAnswers[0].setBounds(20,88,360,60);

        possibleAnswers[1].setBounds(86,175,260,35);
        possibleAnswers[2].setBounds(86,225,260,35);
        possibleAnswers[3].setBounds(86,275,260,35);
        possibleAnswers[4].setBounds(86,325,260,35);

        //Default back
        possibleAnswers[5].setBounds(36,175,320,35);
        possibleAnswers[6].setBounds(36,225,320,35);
        possibleAnswers[7].setBounds(36,275,320,35);
        possibleAnswers[8].setBounds(36,325,320,35);

        //Correct back

    };

    //General methods used to center the textfield
    String centerTextArea(String s){

        //Centers up to two lines in text area
        var charsPerLine = 33;
        var sOut = "";
        var j = s.indexOf("\n");
        int nSpaces;

        if(j == -1){

            //Single line
            sOut = "\n" + spacePadding((int)((charsPerLine - s.length())/2)) + s;
        }
        else{

            //First line
            var l = s.substring(0,j);
            sOut = "\n" + spacePadding((int)((charsPerLine - l.length())/2)) + 1;

            //Second line
            l = s.substring(j + 1);
            sOut += "\n" + spacePadding((int)((charsPerLine - l.length())/2)) + 1;
        }

        return(sOut);
    }
    private String spacePadding(int n){
        var s = "";
        if(n != 0)
            for(int i = 0; i<n; i++)
                s += " ";

            return(s);
    }


    static Consumer<JFrame> centerFrame = (e) -> {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        e.setBounds((int) (0.5 * (screenSize.width - e.getWidth())),
                (int) (0.5 * (screenSize.height - e.getHeight())),
                e.getWidth(), e.getHeight());
    };
}
