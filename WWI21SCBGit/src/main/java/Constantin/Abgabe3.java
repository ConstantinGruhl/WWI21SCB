package Constantin;

import java.util.function.Consumer;
import processing.core.PApplet;

    class Switch{
        private static long lastTime = 0;
        public static boolean change;
        public static boolean dropdown;
        public static boolean firework;
        public static boolean sliderboolean = false;

        public static void Change(){
            if (isValidAction()){
                change = !change;
                lastTime = System.currentTimeMillis();
            }
        }

        public static void Dropdown(){
            if (isValidAction()){
                dropdown = !dropdown;
                lastTime = System.currentTimeMillis();
            }
        }

        public static boolean SliderHasBeenPressed(){
            return sliderboolean;
        }

        public static void setSliderboolean(){
            sliderboolean = true;
        }

        private static boolean isValidAction(){
            return(System.currentTimeMillis() > (lastTime + 500));
        }
    }

    public class Abgabe3 extends PApplet {

        public boolean button;
        GuiButton button1;
        GuiButton button2;
        GuiButton dropdown;
        GuiButton dropButton1;
        GuiButton dropButton2;
        GuiButton dropButton3;
        GuiButton slider;

        private long counter = 0;

        private int sliderPos = 300;

        private int red = 100;
        private int blue = 100;
        private int green = 100;

        public static void main(String[] args) {
            PApplet.main("Constantin.Abgabe3");

        }

        public void settings() {
            size(800, 600);
        }

        public void setup() {
            rectMode(PApplet.CENTER);
            textAlign(PApplet.CENTER, PApplet.CENTER);
            button1 = new GuiButton(100, 100, 175, 50, red, green, blue, "Click", this);
            button2 = new GuiButton(100, 200, 175, 50, red, green, blue, "Switch", this);
            dropdown = new GuiButton(100, 300, 175, 50, red, green, blue, "Dropdown", this);
            dropButton1 = new GuiButton(100, 350, 175, 50, red, green, blue, "Dropdown", this);
            dropButton2 = new GuiButton(100, 400, 175, 50, red, green, blue, "Dropdown", this);
            dropButton3 = new GuiButton(100, 450, 175, 50, red, green, blue, "Dropdown", this);
            slider = new GuiButton(500, 100, 300, 50, red, green, blue, "Slider", this);
            button1.onClick = (btn) -> onButtonClick(button1);
            button2.onClick = (btn) -> onButtonClick(button2);
            dropdown.onClick = (btn) -> onButtonClick(dropdown);
            dropButton1.onClick = (btn) -> onButtonClick(dropButton1);
            dropButton2.onClick = (btn) -> onButtonClick(dropButton2);
            dropButton3.onClick = (btn) -> onButtonClick(dropButton3);
            slider.onClick = (btn) -> onButtonClick(slider);
        }

        public void draw() {

            background(0);
            button1.render();
            button2.render();
            dropdown.render();
            slider.render();

            fill(sliderPos - 350, sliderPos - 350, sliderPos - 350);
            circle(500, 250, 150);

            if (Switch.change) {
                fill(204, 200, 0);
                circle(300, 200, 50);
            }

            if (Switch.dropdown) {
                dropButton1.render();
                dropButton2.render();
                dropButton3.render();
            }


                if (Switch.SliderHasBeenPressed()) {
                    fill(204, 200, 0);
                    circle(sliderPos, 100, 50);
                    fill(sliderPos - 350, sliderPos - 350, sliderPos - 350);
                    circle(500, 250, 150);
                } else {
                    fill(204, 200, 0);
                    circle(500, 100, 50);
                }
            }

        void Slider() {
            if (mouseX > 350 && mouseX < 650) {
                sliderPos = mouseX;
            }
        }

        void onButtonClick(Object btn) {
            if (btn.equals(button1)) {
                fill(204, 200, 0);
                circle(300, 100, 50);
            } else if (btn.equals(button2)) {
                Switch.Change();
            } else if (btn.equals(dropdown)) {
                Switch.Dropdown();
            } else if (btn.equals(dropButton1)) {
                fill(204, 200, 0);
                circle(300, 350, 50);
            } else if (btn.equals(dropButton2)) {
                fill(204, 200, 0);
                circle(300, 400, 50);
            } else if (btn.equals(dropButton3)) {
                fill(204, 200, 0);
                circle(300, 450, 50);
            } else if (btn.equals(slider)) {
                Slider();
                Switch.setSliderboolean();
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
                parent.fill(100, 0, 0);
                parent.rect(posX, posY, w, h);
                parent.textSize(32);
                parent.fill(200);
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
