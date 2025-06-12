import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private char currentPlayer = 'X';
    private JLabel statusLabel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 40);
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(font);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);
        JButton resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if (!btnClicked.getText().equals("")) {
            return; // already clicked
        }
        btnClicked.setText(String.valueOf(currentPlayer));
        if (checkWin()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a Draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s Turn");
        }
    }
    private boolean checkWin() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] wc : winConditions) {
            if (buttons[wc[0]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[wc[1]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[wc[2]].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }
    private boolean isDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    private void disableButtons() {
        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }
    }
    private void resetGame() {
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setEnabled(true);
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's Turn");
    }
}
class Main {
    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
