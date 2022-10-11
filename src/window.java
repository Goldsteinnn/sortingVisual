import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class window extends JFrame {

    private int width;
    private int height;

    private Sorting panel;
    public window(int width, int height){
        this.width = width;
        this.height = height;
        this.panel = new Sorting(this.width,this.height);
    }

    public void setup() {
       this.setTitle("Sorting Visual");
       this.setSize(this.width,this.height);
       this.getContentPane().add(panel);
       this.setVisible(true);
       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
