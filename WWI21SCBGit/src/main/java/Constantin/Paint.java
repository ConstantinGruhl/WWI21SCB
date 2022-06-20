package Constantin;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.function.Consumer;
import processing.core.PApplet;


class Point{
    public int r;
    public int g;
    public int b;
    public int size;
    public int x;
    public int y;


    public Point(int r, int g, int b, int size, int x, int y) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.size = size;
        this.x = x;
        this.y = y;
    }
}


class SwitchPaint{
    private static long lastTime = 0;
    public static boolean change;
    public static boolean sliderGreenboolean= false;
    public static boolean sliderBlueboolean = false;
    public static boolean sliderRedboolean = false;
    public static boolean sliderThiccboolean = false;

    public static boolean SliderGreenHasBeenPressed(){
        return sliderGreenboolean;
    }
    public static boolean SliderBlueHasBeenPressed(){return sliderBlueboolean;}
    public static boolean SliderRedHasBeenPressed(){return sliderRedboolean;}
    public static boolean SliderThiccHasBeenPressed(){
        return sliderThiccboolean;
    }

    public static void setGreenSliderboolean(){
        sliderGreenboolean = true;
    }
    public static void setBlueSliderboolean(){sliderBlueboolean  = true; }
    public static void setRedSliderboolean(){sliderRedboolean = true;}
    public static void setThiccSliderboolean(){sliderThiccboolean = true;}

    public static boolean isValidAction(){
        return(System.currentTimeMillis()%1000==0);
    }
}

class PaintCount{
    public long counter = 0;

    public long getCounter(){
        return counter;
    }

    public void increase(){
        counter++;
    }

}

public class Paint extends PApplet {

    ArrayList<Point> Leinwand = new ArrayList<Point>();

    public boolean button;
    GuiButton button2;
    GuiButton sliderBlue;
    GuiButton sliderGreen;
    GuiButton sliderRed;
    GuiButton sliderThicc;
    GuiButton drawField;
    GuiButton clear;

    private int sliderPosBlue;
    private int sliderPosGreen;
    private int sliderPosRed;
    private int sliderPosThicc;
    private boolean isPressed = false;

    private int xPos = 150;
    private int yPos = 600;

    public static void main(String[] args) {
        PApplet.main("Constantin.Paint");
    }

    public void settings() {
        size(1400, 800);
    }

    public void setup() {
        rectMode(PApplet.CENTER);
        textAlign(PApplet.CENTER, PApplet.CENTER);
        button2 = new GuiButton(100, 100, 175, 50, 0, 0, 0, "Eraser", this);
        sliderGreen = new GuiButton(130, 200, 255, 50, 0, 0, 0, "Slider Green", this);
        sliderBlue = new GuiButton(130, 250, 255, 50, 0,0, 0, "Slider Blue", this);
        sliderRed = new GuiButton(130, 300, 255, 50, 0, 0, 0, "Slider Red", this);
        sliderThicc = new GuiButton(130, 350, 255, 50, 0, 0, 0, "Size", this);
        drawField = new GuiButton(1000,500,1200,1000,255,255,255,"",this);
        clear = new GuiButton(100, 50, 175, 50, 0, 0, 0, "Clear", this);
        button2.onClick = (btn) -> onButtonClick(button2);
        sliderBlue.onClick = (btn) -> onButtonClick(sliderBlue);
        sliderGreen.onClick = (btn) -> onButtonClick(sliderGreen);
        sliderRed.onClick = (btn) -> onButtonClick(sliderRed);
        sliderThicc.onClick = (btn) -> onButtonClick(sliderThicc);
        drawField.onClick = (btn) -> onButtonClick(drawField);
        clear.onClick = (btn) -> onButtonClick(clear);
    }

    public void draw() {

        background(255);
        button2.render();
        drawField.render();
        sliderBlue.render();
        sliderGreen.render();
        sliderRed.render();
        sliderThicc.render();
        clear.render();

        for (Point point : Leinwand) {
            fill(point.r, point.g, point.b);
            strokeWeight(0);
            stroke(point.r, point.g, point.b);
            circle(point.x, point.y, point.size);
        }

        if (SwitchPaint.change) {
            fill(204, 200, 0);
            circle(100, 100, 50);
        }

        if (mouseX>400){
            fill(sliderPosRed,sliderPosGreen,sliderPosBlue);
            circle(mouseX,mouseY,sliderPosThicc);
        }
        if (SwitchPaint.SliderGreenHasBeenPressed()) {
            fill(0, sliderPosGreen, 0);
            circle(sliderPosGreen, 200, 50);
        }else {
            fill(0, sliderPosGreen, 0);
            circle(sliderPosGreen, 200, 50);
        }
         if (SwitchPaint.SliderRedHasBeenPressed()) {
            fill(sliderPosRed, 0, 0);
            circle(sliderPosRed, 300, 50);
        }else {
            fill(sliderPosRed, 0, 0);
            circle(sliderPosRed, 300, 50);
        }
        if (SwitchPaint.SliderBlueHasBeenPressed()) {
            fill(0, 0, sliderPosBlue);
            circle(sliderPosBlue, 250, 50);
        }else {
            fill(0, 0, sliderPosBlue);
            circle(sliderPosBlue, 250, 50);
        }
        if (SwitchPaint.SliderThiccHasBeenPressed()) {
            fill(255, 255, 255);
            circle(sliderPosThicc, 350, 50);
        }else {
            fill(255, 255, 255);
            circle(sliderPosThicc, 350, 50);
        }
        fill(sliderPosRed,sliderPosGreen,sliderPosBlue);
        square(310,250,100);

    }

    void SliderGreen() {
        if (mouseX > 0 && mouseX < 255) {
            sliderPosGreen = mouseX;
        }
    }
    void SliderRed() {
        if (mouseX > 0 && mouseX < 255) {
            sliderPosRed = mouseX;
        }
    }
    void SliderBlue() {
        if (mouseX > 0 && mouseX < 255) {
            sliderPosBlue = mouseX;
        }
    }

    void SliderThicc() {
        if (mouseX > 0 && mouseX < 255) {
            sliderPosThicc = mouseX;
        }
    }

    void onButtonClick(Object btn) {
        frameRate(200);
        if(btn.equals(drawField)){
            Leinwand.add(new Point(sliderPosRed,sliderPosGreen,sliderPosBlue,sliderPosThicc,mouseX,mouseY));
        } else if (btn.equals(button2)) {
            sliderPosBlue = 255;
            sliderPosGreen = 255;
            sliderPosRed = 255;
        } else if (btn.equals(sliderGreen)) {
            SliderGreen();
            SwitchPaint.setGreenSliderboolean();
        } else if (btn.equals(sliderRed)) {
            SliderRed();
            SwitchPaint.setRedSliderboolean();
        } else if (btn.equals(sliderBlue)){
            SliderBlue();
            SwitchPaint.setBlueSliderboolean();
        } else if (btn.equals(sliderThicc)){
            SliderThicc();
            SwitchPaint.setThiccSliderboolean();
        } else if (btn.equals(clear)){
            Leinwand.clear();
        }
    }

    class GuiButton {
        Consumer<Object> onClick;
        float posX, posY;
        float w, h;
        int r, g, b;
        String text;
        PApplet parent;

        public GuiButton(float x, float y, float w, float h, int r, int g, int b, String text, PApplet parent) {
            this.posX = x;
            this.posY = y;
            this.w = w;
            this.h = h;
            this.r = r;
            this.g = g;
            this.b = b;
            this.text = text;
            this.parent = parent;
        }

        public void render() {
            parent.fill(r, g, b);
            parent.rect(posX, posY, w, h);
            parent.textSize(32);
            parent.fill(255);
            parent.text(text, posX, posY);

            if (parent.mousePressed == true && isPosInRect(parent.mouseX, parent.mouseY)) {
                if (onClick != null) {
                    onClick.accept(this);
                }
            }
        }

        private boolean isPosInRect(int mouseX, int mouseY) {
            boolean isInWidth = mouseX >= posX - w / 2 && mouseX <= posX + w / 2;
            boolean isInHeight = mouseY >= posY - h / 2 && mouseY <= posY + h / 2;
            return isInHeight && isInWidth;
        }
    }
}
