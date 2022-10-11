import sun.management.snmp.jvmmib.JvmRTBootClassPathTableMeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Sorting extends JPanel {
    private Random random = new Random();
    private int[] arr = new int[100];
    private int array_index;
    private int compare_index;
    private int width;
    private int height;

    JButton start = new JButton("start");
    JButton reset = new JButton("reset");

    private boolean isRunning;

    public Sorting(int width, int height){
        this.width = width;
        this.height = height;
        this.array_index = 0;
        this.compare_index = Integer.MAX_VALUE;

        this.setArray();

        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    isRunning = true;
                    BubbleSortAnimate();
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        reset.setBackground(Color.white);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setArray();
                compare_index = Integer.MAX_VALUE;
                array_index = 0;
                isRunning = false;
                repaint();
            }
        });

        this.add(start);
        this.add(reset);
    }

    public void setArray(){
        for(int i = 0;i < this.arr.length; i++){
            this.arr[i] = random.nextInt(510) + 40;
        }
    }

    public int[] getArray(){
        return this.arr;
    }

    public void sortOnlyOneItem(){
        if(arr[compare_index] > arr[compare_index + 1]){
            int temp = arr[compare_index];
            arr[compare_index] = arr[compare_index + 1];
            arr[compare_index + 1] = temp;
        }

        if((compare_index + 1) >= (arr.length - array_index - 1)){
            array_index++;
            compare_index = 0;
        }
        else{
            compare_index++;
        }
    }
    public boolean isSorted(){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i +1]){
                return false;
            }
        }
        return true;
    }

    public void BubbleSortAnimate() throws Exception{
        compare_index = 0;

        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSorted()){
                    compare_index = Integer.MAX_VALUE;
                    ((Timer)e.getSource()).stop();
                }
                else{
                    if(isRunning == true){
                        sortOnlyOneItem();
                    }
                    repaint();
                }
            }
        });
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);

        for(int i = 0; i < arr.length; i++){
            g.setColor(Color.WHITE);
            if(i == compare_index || i == compare_index + 1){
                g.setColor(Color.RED);
            }
            g.drawRect(i*(this.width/arr.length),600-arr[i],(this.width/arr.length),arr[i]);
            g.fillRect(i*(this.width/arr.length),600-arr[i],(this.width/arr.length),arr[i]);
        }

    }
}
