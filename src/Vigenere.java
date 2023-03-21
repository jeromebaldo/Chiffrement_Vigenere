import java.util.Arrays;

public class Vigenere
{
    private int nbrLignes;
    private int nbrColonnes;
    private String cles;
    private String text;

    public Vigenere(String cles, String texte)
    {
        this.cles = cles;
        this.text = texte;
        DetermTailleMatrice();
    }

    public void DetermTailleMatrice()
    {
        int longText = text.length();
        int nbrCol = cles.length();
        //en divisant la longueur du texte par nombre de colonnes pour calibrer le nombre de lignes pour la matrice
        //math.ceil permet d'arrondir le résultat
        int nbrLign = (int) Math.ceil(longText/ (double) nbrCol);

        this.nbrLignes= nbrLign;//fixe l'attribut
        this.nbrColonnes = nbrCol;// fixe  l'attribut
    }

    public char[][] NouvelleMatrice()//initialisation des cases de la matrice
    {
        char[][] matriceNouvelle = new char[nbrLignes][nbrColonnes];
        for (int i = 0; i < nbrLignes; i++)
        {
            Arrays.fill(matriceNouvelle[i], ' ');
        }
        return matriceNouvelle;
    }

    public char[][] RemplissageMatrice()
    {
        char[][] matriceRempli = NouvelleMatrice();
        for (int i = 0; i < text.length(); i++)
        {
            // la division donnera toujours un integer
            // ainsi 1/3 donnera 0 cela permettra de d'avoir la ligne 0 jusque celui-ci remplit
            int ligne = i / nbrColonnes;
            int colonne = i % nbrColonnes; //modulo pour toujours iterer dans la plage 0 à nbrColonnes-1
            matriceRempli[ligne][colonne] = text.charAt(i);
        }
        return matriceRempli;
    }



    public String ChiffrMatrice()
    {
        char[][] matriceChiffr = NouvelleMatrice();
        char[][] matriceAccueil = RemplissageMatrice();

        int d = 0;
        for (char c : cles.toCharArray())
        {
            int colCles = Character.getNumericValue(c) -1;
            for (int indLigne = 0; indLigne < nbrLignes; indLigne++)
            {
                matriceChiffr[indLigne][colCles] = matriceAccueil[indLigne][d];
            }
            d++;
        }

        String textChiffr = TraducStringChiffr(matriceChiffr);

        return textChiffr;
    }


    public char[][] RemplissageMatriceChiffr()
    {
        char[][] matriceRempli = NouvelleMatrice();
        int ligne = 0;
        int colonne = 0;
        int iterText = 0;

        while(iterText < text.length())
        {
            ligne = 0;
            while(ligne < nbrLignes)
            {
                matriceRempli[ligne][colonne] = text.charAt(iterText);
                ligne++;
                iterText++;
            }
            colonne = (colonne+1) % nbrColonnes;// en jouant avec le modulo,colonne itère uniquement dans la plage 0 à nbrColonne-1
        }
        return matriceRempli;
    }

    public String DechiffrMatrice()
    {
        char[][] matriceDechiffr = NouvelleMatrice() ;
        char[][] matriceChiffr = RemplissageMatriceChiffr();//remplissage de la matrice avec le texte chiffre

        //création de la matrice de déchiffrement
        int colonne = 0;
        for (char c : cles.toCharArray())
        {
            //conversion en integer du caractère de la clé avec -1 pour ne pas déborder
            int col = Character.getNumericValue(c) - 1;
            for(int ligne = 0; ligne < nbrLignes; ligne++)
            {
                //on repositionne la colonne selon l'ordre du chiffre dans la clé
                //exemple 24135 => les caractères de la colonne 2 revienne à la colonne 1
                matriceDechiffr[ligne][colonne] = matriceChiffr[ligne][col];
            }
            colonne++;
        }

        String textDechiffr = TraducStringDechiffr(matriceDechiffr);

        return textDechiffr;
    }

    public String TraducStringChiffr(char[][]matrice)//methode pour remettre en string le texte chiffre
    {
        //detail important => on lit les caractères colonne par colonne toujours de haut en bas
        String textToString = "";
        int colon = 0;
        int row = 0;
        while (colon < nbrColonnes)
        {
            while (row < nbrLignes)
            {
                textToString += matrice[row][colon];
                row++;
            }
            colon++;
            row = 0;
        }

        return textToString;
    }

    public String TraducStringDechiffr(char[][]matrice)//methode pour remettre en string le texte dechiffre
    {
        //detail important => on relit dans le sens normal => ligne par ligne
        String textToString = "";
        for (int i = 0; i < nbrLignes; i++)
        {
            for (int j = 0; j < nbrColonnes; j++)
            {
                textToString += matrice[i][j];
            }
        }
        return textToString;
    }
}
