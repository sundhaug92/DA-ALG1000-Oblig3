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
        hashTable<String,Integer> ht = new hashTable<>(15);
        String key;
        
        while (true) {
            System.out.print("> ");
            switch (Integer.parseInt(inp.nextLine())) {
                case 0:
                    System.out.print("Key> ");
                    key = inp.nextLine();
                    System.out.print("Value> ");
                    String value = inp.nextLine();
                    ht.add(key, Integer.parseInt(value));
                    break;
                case 1:
                    System.out.print("Key> ");
                    key = inp.nextLine();
                    System.out.println(ht.get(key));
                    break;
            }
        }
    }
}
