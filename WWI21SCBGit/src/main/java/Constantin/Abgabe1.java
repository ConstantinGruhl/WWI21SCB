package Constantin;

import java.util.Scanner;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Abgabe1 {
    public static void main(String[] args) {

        Scanner Scanner = new Scanner(System.in);
        Random random = new Random();

        int laenge;
        String sonderzeichen = "n";
        String Zahlen;

        System.out.println("Hallo und willkommen beim Passwort Generator von Constantin");
        System.out.print("Bitte gebe    ein wie lang das Passwort sein soll:");
        laenge = Scanner.nextInt();
        System.out.println(laenge);
        System.out.print("Sollen Zahlen verwendet werden?(j/n)");
        Zahlen = Scanner.next();

        if (Zahlen.equals("j")) {
            System.out.print("Sollen Sonderzeichen verwendet werden?(j/n)");
            sonderzeichen = Scanner.next();
        }

        char[] kleineBuchstaben = new char[]{'q','w','e','r','t','z','u','i','o','p','a','s','d','f','g','h','j','k','l','y','x','c','v','b','n','m'};
        char[] großeBuchstaben = new char[]{'Q','W','W','R','T','Z','U','I','O','P','A','S','D','F','G','H','J','K','L','Y','X','C','V','B','N','M'};
        char[] zahlen = new char[]{'1','2','3','4','5','6','7','8','9','0'};
        char[] zeichen = new char[]{',','.','#','-','_','*','~','!','>','<'};

        int Passwortkette [] = new int[laenge];
        String Passwort = "";

        // Erstellt einen Array mit Zahlen von 1-4 / 1-3 / 1-2 jewils für die Auswahl des Nutzers ob Zahlen und Zeichen verwendet werden sollen.

        for (int i = 0; i<laenge; i++ ){
            if (sonderzeichen.equals("j") && Zahlen.equals("j")){
                Passwortkette[i] = random.nextInt(3 + 1) + 1;
            } else if (Zahlen.equals("j") && sonderzeichen.equals("n")){
                Passwortkette[i] = random.nextInt(2 + 1) + 1;
            } else if (Zahlen.equals("n") && sonderzeichen.equals("n")){
                Passwortkette[i] = random.nextInt(1 + 1) + 1;
            }
        }
        // Generiert das Passwort. Der Array Passwortkette legt fest, welches "Zeichenart" verwendet wird.
        for (int i = 0; i<laenge; i++ ){
            if(Passwortkette[i]==1){
                int p = random.nextInt(25 + 0) + 0;
                char Buchstabe = kleineBuchstaben[p];
                Passwort= Passwort + Buchstabe;
            }
            if(Passwortkette[i]==2){
                int p = random.nextInt(25 + 0) + 0;
                char Buchstabe = großeBuchstaben[p];
                Passwort= Passwort + Buchstabe;
            }
            if(Passwortkette[i]==3){
                int p = random.nextInt(9 + 0) + 0;
                char zahl = zahlen[p];
                Passwort= Passwort + zahl;
            }
            if(Passwortkette[i]==4){
                int p = random.nextInt(9 + 0) + 0;
                char Zeichen = zeichen[p];
                Passwort= Passwort + Zeichen;
            }
        }
        System.out.println("Dein neues Passwort lautet: " + Passwort);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(Passwort);
        clipboard.setContents(strSel, null);
    }
}
