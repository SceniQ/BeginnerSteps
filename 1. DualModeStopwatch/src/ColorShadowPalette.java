import javax.swing.*;
import java.awt.*;
import java.io.File;

//For the button colors
enum ColorPalette {

    YELLOW(254, 244, 51),
    GREEN(10, 105, 81),
    BLUE(17, 10, 106),
    PURPLE(88, 9, 92),
    RED(211, 31, 37),
    ORANGE(248, 109, 28);

    private int r, g, b;

    ColorPalette(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color getColor(){
        return new Color(r, g, b);
    }
}

//For the shadow buttons

enum ShadowPalette {

    //Shadow file names and their location on the drive(or within the package)
    DEFAULT_SHAD(new ImageIcon("defaultShad.png")),
    YELLOW_SHAD(new ImageIcon("yellowShad.png")),
    GREEN_SHAD(new ImageIcon("greenShad.png")),
    BLUE_SHAD(new ImageIcon("blueShad.png")),
    PURPLE_SHAD(new ImageIcon("purpleShad.png")),
    RED_SHAD(new ImageIcon("redShad.png")),
    ORANGE_SHAD(new ImageIcon("orangeShad.png"));

    private ImageIcon imgLoc;

    ShadowPalette(ImageIcon imgLoc){
        this.imgLoc = imgLoc;
    }

    public ImageIcon getShadow(){
        return imgLoc;
    }
}

enum ColorChoice{

    selector(new ImageIcon("selector.png")),
    yellowChoice(new ImageIcon("yellowChoice.png")),
    greenChoice(new ImageIcon("greenChoice.png")),
    blueChoice(new ImageIcon("blueChoice.png")),
    purpleChoice(new ImageIcon("purpleChoice.png")),
    redChoice(new ImageIcon("redChoice.png")),
    orangeChoice(new ImageIcon("orangeChoice.png"));

    private ImageIcon colorType;

    ColorChoice(ImageIcon colorType){
        this.colorType = colorType;
    }

    public ImageIcon getColorType(){
        return colorType;
    }
}


