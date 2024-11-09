package org.example;

import org.jpl7.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Integer;

public class WumpusGameGUI extends JFrame implements ActionListener {
    private int posX;
    private int posY;
    private boolean flechaDisponivel;
    private JButton[][] buttons;
    private JButton btnFlecha;

    public WumpusGameGUI() {
        this.posX = 1; // Posição inicial
        this.posY = 1;
        this.flechaDisponivel = true; // Flecha disponível no início

        setTitle("Mundo Wumpus");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        buttons = new JButton[4][4];
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                buttons[x - 1][y - 1] = new JButton("Sala (" + x + ", " + y + ")");
                buttons[x - 1][y - 1].setActionCommand(x + "," + y);
                buttons[x - 1][y - 1].addActionListener(this);
                buttons[x - 1][y - 1].setBackground(Color.LIGHT_GRAY);
                gridPanel.add(buttons[x - 1][y - 1]);
            }
        }

        btnFlecha = new JButton("Disparar Flecha");
        btnFlecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flechaDisponivel) {
                    shootArrow();
                } else {
                    JOptionPane.showMessageDialog(WumpusGameGUI.this, "Você não tem mais flechas.");
                }
            }
        });

        add(gridPanel, BorderLayout.CENTER);
        add(btnFlecha, BorderLayout.SOUTH);
        updateButtonColors();
    }

    private void updateButtonColors() {
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                if (x == posX && y == posY) {
                    buttons[x - 1][y - 1].setBackground(Color.GREEN); // Posição atual
                } else {
                    buttons[x - 1][y - 1].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] coords = command.split(",");
        int newX = Integer.parseInt(coords[0]);
        int newY = Integer.parseInt(coords[1]);
        attemptMove(newX, newY);
    }

    private void attemptMove(int newX, int newY) {
        Query q = new Query("pode_ir(" + posX + ", " + posY + ", " + newX + ", " + newY + ")");
        if (q.hasMoreSolutions()) {
            posX = newX;
            posY = newY;
            checkForDanger();
            checkForHints();
            updateButtonColors();
        } else {
            JOptionPane.showMessageDialog(this, "Movimento inválido.");
        }
    }

    private void checkForDanger() {
        Query q = new Query("perigo(" + posX + ", " + posY + ")");
        if (q.hasMoreSolutions()) {
            JOptionPane.showMessageDialog(this, "Você morreu! Perigo detectado.");
            System.exit(0); // O jogo termina
        } else {
            JOptionPane.showMessageDialog(this, "Ambiente seguro.");
        }
    }

    private void checkForHints() {
        // Verificar brisa
        Query qBrisa = new Query("brisa(" + posX + ", " + posY + ")");
        if (qBrisa.hasSolution()) {
            JOptionPane.showMessageDialog(this, "Você sente uma brisa. Cuidado com poços próximos!");
        }

        // Verificar fedor
        Query qFedor = new Query("fedor(" + posX + ", " + posY + ")");
        if (qFedor.hasSolution()) {
            JOptionPane.showMessageDialog(this, "Você sente um fedor. O Wumpus está próximo!");
        }

        // Verificar brilho
        Query qBrilho = new Query("brilho(" + posX + ", " + posY + ")");
        if (qBrilho.hasSolution()) {
            JOptionPane.showMessageDialog(this, "Você vê um brilho! Ouro está aqui!");
            // Checar se o jogador está no ouro
            if (posX == 2 && posY == 1) {
                JOptionPane.showMessageDialog(this, "Você encontrou o ouro! Saia agora!");
                // Permitir que o jogador saia
            }
        }
    }

    private void shootArrow() {
        Query q = new Query("disparar_flecha(" + posX + ", " + posY + ")");
        if (q.hasSolution()) {
            flechaDisponivel = false;  // A flecha é usada
            JOptionPane.showMessageDialog(this, "Você disparou a flecha!");

            // Verifica se o disparo atingiu o Wumpus
            Query qMatar = new Query("matar_wumpus(" + posX + ", " + posY + ")");
            if (qMatar.hasSolution()) {
                JOptionPane.showMessageDialog(this, "Você matou o Wumpus!");
            } else {
                JOptionPane.showMessageDialog(this, "A flecha errou o Wumpus.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não pode disparar a flecha daqui.");
        }
    }


}
