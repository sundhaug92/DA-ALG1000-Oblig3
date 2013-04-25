/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.midi.*;
import java.net.URL;

/**
 *
 * @author Martin
 */
public final class exitManager {

    void printSenter(String str) {
        for (int i = (40 - str.length()) / 2; i > 0; i--) {
            System.out.print(" ");
        }
        System.out.println(str);
    }

    exitManager() throws MalformedURLException, IOException, MidiUnavailableException, InvalidMidiDataException {
        try {
            URL url = new URL("http://www.tsr.org/StarWars/multimedia/midi/thronenend.mid");

            System.out.println("Please wait...");
            final Sequence sequence = MidiSystem.getSequence(url);
            final Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
            while (!sequencer.isRunning()) {
                Thread.sleep(1);
            }
            for (int i = 18; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            for (int i = 25; i > 0; i--) {
                System.out.println();
            }
            printSenter("Obligatorisk innlevering 3 avsluttes");
            Thread.sleep(3000);
            printSenter("Semesteret likedan");
            Thread.sleep(3000);
            printSenter("Ny kunnskap tilegnet");
            Thread.sleep(3000);
            printSenter("Kompleksitet beregnet");
            Thread.sleep(3000);
            printSenter("Grafer tegnet");
            Thread.sleep(3000);
            printSenter("Linjer kodet");
            Thread.sleep(3000);
            printSenter("Java i hodet");
            Thread.sleep(3000);
            printSenter("End->shift+opp lett");
            Thread.sleep(3000);
            printSenter("Linje med .NET");
            Thread.sleep(3000);
            printSenter("Merk den og slett");
            Thread.sleep(3000);
            printSenter("Gjennom kildekoden sett");
            Thread.sleep(3000);
            printSenter("Derhvor teksten er tett");
            Thread.sleep(3000);
            printSenter("Programlus blir mett");
            Thread.sleep(3000);
            printSenter("Algoritmen dertil");
            Thread.sleep(3000);
            printSenter("Minst mulig snill");
            Thread.sleep(3000);
            printSenter("Exception-handleren vondt vil");
            Thread.sleep(3000);
            printSenter("Mottageren av brukerens SIGKILL");
            Thread.sleep(3000);
            printSenter("");
            printSenter("");
            printSenter("");
            printSenter("\"Throne Room & The End Titles\" - John Williams (midi by Dolimah Deen)");
            printSenter("Software by Martin Sundhaug");


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
