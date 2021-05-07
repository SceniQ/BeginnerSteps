/*
	Chapter  	: ---
	Programmer	: Tebogo Assagaai
	Filename	: StopwatchDualMode.java
	Start Date  : 27 April 2021 [20:30]
	Purpose		: - To build a DualMode Stopwatch using Java 8 and 11 Features with a bit of a twist for the watche's
				    functionality. This is the third code write for this project. I needed a final edit before release.
				  - For Java improvement.
	Source		: Java Homework Projects - by Philip Conrod & Lou Tylee
*/

// Import/Package statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StopwatchDualMode extends JFrame {

   //The objects needed to complete the stopwatch app
   private final CommonStopwatchUI ui = new CommonStopwatchUI();
   private final TimingTools timing = new TimingTools();
   private final Popup pop = new Popup();
   private final ArrayList<String> markedTimes = new ArrayList<>();

   //To keep track of the changing times
   private long startTime;
   private long stoppedTime;
   private long stopTime;

    //Constructor
    public StopwatchDualMode(){

        /*
        With the TimingTools and CommonStopwatchUI objects, the developer only has the choice to
        add the components. The complexity of the actionPerformed methods and GUI properties are
        handled by the referenced TimingTools/CommonStopwatchUI object. This the major enhancement of this
        project: turning it into a quick reference tool in case I need a stopwatch in any of my future builds;
        or maybe ist features.
        */

        // Setting the frames minor properties
        this.setTitle("Stop Watch");
        this.setSize(369, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adding components to the frame: The frame will just add the components;
        //while the TimingTools and CommonStopwatchUI objects are highly active during runtime

        //Radio buttons
        this.add(ui.colorChoice[0]);

        this.add(ui.colorChoice[1]);
        this.add(ui.colorChoice[2]);
        this.add(ui.colorChoice[3]);
        this.add(ui.colorChoice[4]);
        this.add(ui.colorChoice[5]);
        this.add(ui.colorChoice[6]);

        //labels
        this.add(ui.label[0]);
        this.add(ui.label[1]);
        this.add(ui.label[2]);
            ui.label[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {showPopup.process();}
            });

        //Buttons: 0 = startMarkContinue button, 1 = stopReset button
        this.add(ui.button[0]);
            ui.button[0].addActionListener((e -> startMarkContinue.process()));
            timing.timer = new Timer(1000,(e) -> timerDisplay.process());
        this.add(ui.button[1]);
            ui.button[1].addActionListener((e -> stopReset.process()));

        //Button shadows
        this.add(ui.buttonShadows[0]);
        this.add(ui.buttonShadows[1]);

        //Text fields
        this.add(ui.textField[0]);
        this.add(ui.textField[1]);

         //Panels
        this.add(new PalettePanel());   //Top panel to 'host' the color radio buttons
        this.add(new BackPan());        //The Back panel to set the Background color
        /*
        Somehow setting the frame's background color using setBackground() method
        doesn't display the white background... I wonder why...
        */

        //Centering the stopwatch frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(0.5*(screenSize.width - getWidth())),
                       (int)(0.5*(screenSize.height - getHeight())),
                        getWidth(), getHeight());

    }

    //Lambda functionality for stopwatch during runtime
    private final Procedure startMarkContinue = () ->{

        if(ui.button[0].getText().equalsIgnoreCase("start")){

         ui.button[0].setText("MARK");
         startTime = System.currentTimeMillis();
         stoppedTime = 0;
         timing.timer.start();

     }
        else if(ui.button[0].getText().equalsIgnoreCase("mark")){

        //The first step is to add the kept times in the array
        markedTimes.add("Runtime: "+ui.textField[0].getText());

     }
        else if(ui.button[0].getText().equalsIgnoreCase("continue")){

         ui.button[0].setText("MARK");
         ui.button[1].setText("STOP");
         //This keeps track of the total time.
         stoppedTime += (System.currentTimeMillis() - stopTime);
         timing.timer.start();
     }

    };
    private final Procedure stopReset = () ->{

      if(ui.button[1].getText().equalsIgnoreCase("stop")){

          ui.button[1].setText("RESET");
          ui.button[0].setText("CONTINUE");
          //The stopTime keeps count of the seconds while timer has 'stopped' for later continuation
          stopTime = System.currentTimeMillis();
          timing.timer.stop();
      }

      else if(ui.button[1].getText().equalsIgnoreCase("reset")){

          ui.button[0].setText("START");
          ui.button[1].setText("STOP");
          ui.textField[0].setText("00:00:00");
          ui.textField[1].setText("00:00:00");
      }
    };

    //The counter will be shown using this process
    private final Procedure timerDisplay = () ->{

        var currentTime = System.currentTimeMillis();
        ui.textField[0].setText(timing.HMSdisplay(currentTime - startTime - stoppedTime));
        ui.textField[1].setText(timing.HMSdisplay(currentTime - startTime));
    };

    //for the Popup display
    private final Procedure showPopup = () ->{

        var count = 1;
        JLabel[] inLabel = new JLabel[markedTimes.size()];

        if(!markedTimes.isEmpty()){
            for(int i = 0; i<markedTimes.size(); i++){

                inLabel[i] = new JLabel( "lap: "+count +" - "+markedTimes.get(i));
                inLabel[i].setBounds(35, (50+i), 120, 35);
                inLabel[i].setFont(new Font("Calibri", Font.BOLD, 22));
                inLabel[i].setForeground(Color.gray);
                inLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
                pop.add(inLabel[i]);
                count++;
            }
            markedTimes.clear();
        }
        pop.setVisible(true);
    };

    public static void main(String[] args) { new StopwatchDualMode();}
}
