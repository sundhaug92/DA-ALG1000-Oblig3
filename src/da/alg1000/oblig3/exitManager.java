/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;
import javax.sound.midi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public final class exitManager {
    Synthesizer synth;
    void tone(int note, double length) throws MidiUnavailableException, InterruptedException{
            MidiChannel[] channels=synth.getChannels();
        int fullNote=1250*4/3, baseNote=60, velocity=100;
            channels[5].noteOn(baseNote+note, velocity);
            Thread.sleep((int)(length*fullNote));
            channels[5].noteOff(baseNote+note);
    }
    exitManager(int instrument) {
        try {
            synth=MidiSystem.getSynthesizer();
            synth.open();
            Instrument[] instr=synth.getDefaultSoundbank().getInstruments();
            synth.getChannels()[5].programChange(instrument);
            if(synth.getChannels()[5].getProgram()==instrument){
            tone(5,0.25f);
            tone(2,0.125f);
            tone(3,0.125f);
            
            tone(4,0.25f);
            tone(3,0.125f);
            tone(2,0.125f);
            
            tone(1,0.25f);
            tone(1,0.125f);
            tone(3,0.125f);
            
            tone(5,0.25f);
            tone(4,0.125f);
            tone(3,0.125f);
            
            tone(2,0.25f);
            tone(2,0.125f);
            tone(3,0.125f);
            
            tone(4,0.25f);
            tone(5,0.25f);
            
            tone(3,0.25f);
            tone(1,0.25f);
            tone(1,0.25f);
            synth.close();
    }
        } catch (Exception ex) {
            Logger.getLogger(exitManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
