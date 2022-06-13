package Constantin;


public class AufgabeAlgorithmen1 {
    public enum Getränke{
        Bier(9), GIN (14);

        private int preis;

        Getränke(int i) {
            this.preis = i;
        }
        public int getPreis(){
            return preis;
        }
    }

    public static void main(String[] args) {
        Getränke test = Getränke.GIN;
        System.out.println(test.getPreis());
    }


}
