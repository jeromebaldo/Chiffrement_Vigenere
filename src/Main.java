import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    //pour chaque action (chiffrement ou dechiffrement)
    //il faut instancier un objet avec la clé de chiffrement et le texte (à décrypter ou à crypter)
    public static void main(String[] args)
    {
        //recuperation de la clé de chiffrement/dechiffrement + texte en clair
        //tabCleText[0] c'est le texte
        //tabCleText[1] c'est la clé de chiffrement/dechiffrement
        String[] tabCleText = RecuperationCleText();

        //chiffrement du texte par transposition
        String INPUT_TEXTCHIF = "Le texte chiffré : ";
        Vigenere vigenereChiffr = new Vigenere(tabCleText[1], tabCleText[0]);
        String texteChiffr = vigenereChiffr.ChiffrMatrice();
        Affichage(INPUT_TEXTCHIF,texteChiffr);

        //dechiffrement du texte par la remise des colonnes
        String INPUT_TEXTDECHIF = "Le texte déchiffré : ";
        Vigenere vigenereDechiffr = new Vigenere(tabCleText[1], texteChiffr);
        String textDechiffr = vigenereDechiffr.DechiffrMatrice();
        Affichage(INPUT_TEXTDECHIF +  textDechiffr);
    }

    public static void Affichage(String intitule)
    {
        System.out.println(intitule);
    }

    public static void Affichage(String intitule, String resultat)
    {
        System.out.println(intitule + resultat);
    }

    public static String[] RecuperationCleText()
    {
        String INPUT_TEXT = "Donnez votre texte à chiffrer";
        String INPUT_KEY = "Donnez votre clé formulée en chiffres";
        String[] tabCleText = new String[2];

        Affichage(INPUT_TEXT);
        Scanner scanner  = new Scanner(System.in);
        tabCleText[0] = scanner.nextLine();
        Affichage(INPUT_KEY);
        tabCleText[1] = scanner.nextLine();

        return tabCleText;
    }
}