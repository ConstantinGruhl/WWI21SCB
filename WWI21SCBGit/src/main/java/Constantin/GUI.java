package Constantin;



import processing.core.*;


public class GUI extends PApplet{

    public static void main(String[] args) {
        PApplet.main("Constantin.GUI");
    }
    public void settings() {
        size(800, 600);
    }

    public void setup(){
        stroke(255,0,0,255);
        textSize(72);
    }
    int bgCol = 0;
    public void draw(){
        background(bgCol);
        text("Hello World",10,300);
        bgCol= (bgCol +1) % 128;
    }
}
