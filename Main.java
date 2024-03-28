import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Liste von Ziffern erstellen
        List<Integer> optionsInitial = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            optionsInitial.add(i);
        }

        //Liste zur Speicherung aller möglichen Kombinationen erstellen
        List<List<Integer>> allPossibleNumbers = new ArrayList<>();

        //Rekursiv alle Kombinationen generieren
        generateNumberRecursively(optionsInitial, new ArrayList<>(), allPossibleNumbers);

        //Ausgabe aller gültigen Kombinationen
        System.out.println();
        System.out.println("Kombinationen: " + allPossibleNumbers.size() + " (9!)");
        for (List<Integer> number : allPossibleNumbers) {
            if (isValidCombination(number)) {
                System.out.println(numberFromListOfDigits(number));
            }
        }
    }

    //Methode zur Konvertierung einer Liste von Ziffern in eine Zeichenfolge
    public static String numberFromListOfDigits(List<Integer> listOfDigits) {
        StringBuilder sb = new StringBuilder();
        for (Integer digit : listOfDigits) {
            sb.append(digit);
        }
        return sb.toString();
    }

    //Rekursive Methode zur Generierung aller möglichen Kombinationen von Ziffern
    public static void generateNumberRecursively(List<Integer> options, List<Integer> numberSoFarAsList, List<List<Integer>> allResults) {
        //Wenn keine Optionen mehr übrig sind, wird die aktuelle Kombination zur Liste der Ergebnisse hinzugefügt
        if (options.isEmpty()) {
            allResults.add(new ArrayList<>(numberSoFarAsList));
            return; //Rekursion beenden
        }
        //Durch jede verbleibende Option iterieren
        for (Integer option : options) {
            //Eine neue Liste mit der aktuellen Kombination erstellen
            List<Integer> newNumberSoFarAsList = new ArrayList<>(numberSoFarAsList);
            newNumberSoFarAsList.add(option);
            //Eine neue Liste mit verbleibenden Optionen erstellen (ohne die aktuelle Option)
            List<Integer> newOptions = new ArrayList<>(options);
            newOptions.remove(option);
            //Rekursiv die Kombinationen mit den verbleibenden Optionen generieren
            generateNumberRecursively(newOptions, newNumberSoFarAsList, allResults);
        }
    }

    public static boolean isValidCombination(List<Integer> combination) {
        //Die kombinierte Zahl erstellen
        StringBuilder combinedNumber = new StringBuilder();
        for (Integer digit : combination) {
            combinedNumber.append(digit);
        }
        //Überprüfen, ob die kombinierte Zahl durch ihre Positionen ohne Rest teilbar ist
        for (int i = 1; i <= combinedNumber.length(); i++) {
            int num = Integer.parseInt(combinedNumber.substring(0, i));
            if (num % i != 0) {
                return false;
            }
        }
        return true;
    }
}
