package it.unibo.oop.lab.advanced;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int ATTEMPTS = 0;
    private int min;
    private int max;
    private int attempts = 1;
    private  final DrawNumber model;
    private  final DrawNumberView view;

    public void cercaValori() throws IOException {
        final FileReader f = new FileReader("config.yml");
        final BufferedReader b = new BufferedReader(f);
        while (true) {
            String s = b.readLine();
             cercaMinimo(s);
             if ( s == null) {
                 return;
             }
        }
        }
    
    private void cercaMinimo(final String s) {
        if (s.matches("minimum")) {
            String a = s.substring(9);
            this.min = Integer.parseInt(a);
        }
        else {
            cercaMassimo(s);
        }
        
    }
        
    private void cercaMassimo(final String s) {
        if (s.matches("maximum")) {
            String b = s.substring(9);
            this.max = Integer.parseInt(b);        }
        else {
            cercaAttempts(s);
        }
        
    }
    private void cercaAttempts(final String s) {
        if (s.matches("attempts")) {
            String c = s.substring(10);
            this.attempts = Integer.parseInt(c);        }
        else {
            System.out.println("valori non trovati");
        }
    }
     
    /**
     * 
     */
    public DrawNumberApp() {
        cercavalori();
        this.model = new DrawNumberImpl(min, max, attempts);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
