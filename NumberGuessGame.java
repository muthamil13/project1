import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private JTextField startRangeField, endRangeField, guessField;
    private JLabel messageLabel, rangeLabel, guessPromptLabel, attemptsLabel;
    private JButton startButton, guessButton, resetButton;
    private int targetNumber, attempts;
    private Random random;

    public NumberGuessingGame() {
        // Setup JFrame
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        random = new Random();

        // Main panel with GridLayout
        JPanel mainPanel = new JPanel(new GridLayout(6, 1));

        // Range input panel
        JPanel rangePanel = new JPanel();
        rangeLabel = new JLabel("Enter the range (start and end): ");
        startRangeField = new JTextField(5);
        endRangeField = new JTextField(5);
        rangePanel.add(rangeLabel);
        rangePanel.add(startRangeField);
        rangePanel.add(endRangeField);

        // Start button
        startButton = new JButton("Start Game");
        startButton.addActionListener(new StartGameListener());

        // Guess input panel
        JPanel guessPanel = new JPanel();
        guessPromptLabel = new JLabel("Enter your guess: ");
        guessField = new JTextField(5);
        guessButton = new JButton("Submit Guess");
        guessButton.addActionListener(new GuessListener());
        guessPanel.add(guessPromptLabel);
        guessPanel.add(guessField);
        guessPanel.add(guessButton);

        // Labels for messages and attempts
        messageLabel = new JLabel("Welcome to the Number Guessing Game!", JLabel.CENTER);
        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);

        // Reset button
        resetButton = new JButton("Reset Game");
        resetButton.addActionListener(new ResetGameListener());

        // Add components to the main panel
        mainPanel.add(rangePanel);
        mainPanel.add(startButton);
        mainPanel.add(guessPanel);
        mainPanel.add(messageLabel);
        mainPanel.add(attemptsLabel);
        mainPanel.add(resetButton);

        // Add main panel to JFrame
        add(mainPanel, BorderLayout.CENTER);

        // Initialize game state
        resetGame();
    }

    private void resetGame() {
        startRangeField.setText("");
        endRangeField.setText("");
        guessField.setText("");
        messageLabel.setText("Welcome to the Number Guessing Game!");
        attemptsLabel.setText("Attempts: 0");
        targetNumber = 0;
        attempts = 0;
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int startRange = Integer.parseInt(startRangeField.getText().trim());
                int endRange = Integer.parseInt(endRangeField.getText().trim());

                if (startRange >= endRange) {
                    messageLabel.setText("Invalid range! Start should be less than end.");
                } else {
                    targetNumber = random.nextInt(endRange - startRange + 1) + startRange;
                    attempts = 0;
                    messageLabel.setText("Game started! Guess the number.");
                    attemptsLabel.setText("Attempts: 0");
                    guessField.setEnabled(true);
                    guessButton.setEnabled(true);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Please enter valid integers.");
            }
        }
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText().trim());
                attempts++;
                if (guess < targetNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > targetNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    messageLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }
                attemptsLabel.setText("Attempts: " + attempts);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Please enter a valid number.");
            }
        }
    }

    private class ResetGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
                  }
