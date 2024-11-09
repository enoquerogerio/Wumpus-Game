package org.example;

import org.jpl7.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Query consultQuery = new Query("consult('src/main/java/org/example/wumpus.pl')");
        if (consultQuery.hasSolution()) {
            System.out.println("Arquivo Prolog carregado com sucesso.");
        } else {
            System.out.println("Falha ao carregar o arquivo Prolog.");
        }


        SwingUtilities.invokeLater(() -> {
            WumpusGameGUI game = new WumpusGameGUI();
            game.setVisible(true);
        });
    }
}