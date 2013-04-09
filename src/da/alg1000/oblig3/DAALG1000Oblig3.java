/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;

import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class DAALG1000Oblig3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        hashTable<String> ht = new hashTable(15);

        for (;;) {
            String key = inp.nextLine();
            String value = inp.nextLine();
            ht.add(key, value);
        }
    }
}
