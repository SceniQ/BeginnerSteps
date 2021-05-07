import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Popup extends JFrame{

    private final CommonStopwatchUI ui = new CommonStopwatchUI();

    public Popup(){

        this.setTitle("Marked Times");
        this.setSize(300, 400);
        this.setResizable(false);
        this.setVisible(false);

        //Setting the bounds for when the window is called
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(0.5*(screenSize.width - 500)),
                (int)(0.5*(screenSize.height - getHeight())),
                getWidth(), getHeight());

    }
}
