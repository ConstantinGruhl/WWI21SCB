package Constantin;

class Produkte{
    public enum Angebot {

        whiskeysour("Whiskey Sour", 10), beer("Bier", 4), wine("Wein", 6), vodkabull("Vodka-Redbull", 8), gintonic("Gintonic", 9);

        private double price;
        private String name;


        private Angebot(String name, double price) {
            this.price = price;
            this.name = name;
        }

        public double getprice() {
            return price;
        }

        public String getname() {
            return name;
        }

    }
}

public class Kunde{
    String Bestellung;

    private void setBestellung(String Bestellung){
        this.Bestellung = Bestellung;
    }

}

class Bar{


}

class Test{
    public static void main(String[] args) {
        Kunde Kunde1 = new Kunde();

        Bar Birgit = new Bar();


    }
}
