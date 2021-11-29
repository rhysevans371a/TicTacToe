package TicTacToe;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JButton[] boardButtons = new JButton[9];
	JButton resetButton = new JButton("Reset");
	JFrame frame = new JFrame("0's and X's");
	private String startGame = "X";

	public static void main(String[] args) {
		TicTacToe start = new TicTacToe();
		start.initBoard();
		
	}

	public TicTacToe() {
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	

	private void initBoard() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gameBoard = new JPanel(new GridLayout(3,3));		

		frame.add(mainPanel);

		gameBoard.setPreferredSize(new Dimension(500,500));

		mainPanel.add(gameBoard, BorderLayout.NORTH);
		mainPanel.add(resetButton);

		resetButton.addActionListener(this);
		
		for(int i=0; i<9; i++) {
			boardButtons[i] = new JButton();
			boardButtons[i].setBackground(Color.BLACK);
			boardButtons[i].setText("");
			boardButtons[i].setVisible(true);

			gameBoard.add(boardButtons[i]); 
			boardButtons[i].addActionListener(this);
			boardButtons[i].setFont(new Font("Tahoma", Font.BOLD, 100));
		}
	}
	
	private void playerTurn() {
		if(startGame.equalsIgnoreCase("x")) {
			startGame = "0";
		}
		else {
			startGame = "X";
		}
	}



	@Override
	public void actionPerformed(ActionEvent action) {
		for (int i=0; i<9; i++) {
			if(action.getSource()==boardButtons[i]) {
				boardButtons[i].setText(startGame);
				if(startGame.equalsIgnoreCase("X")) {
					boardButtons[i].setForeground(Color.RED);
				}
				else {
					boardButtons[i].setForeground(Color.GREEN);
				}
				if (checkWin() == true) {
					JOptionPane.showMessageDialog(frame, "CONGRATULATIONS! Player " + startGame + " WINs!");						
					frame.setTitle("0's and X's: ***** PLAYER WINS *****");
			}
				playerTurn();

		}
		if(action.getSource() == resetButton) {
			for (int j=0; j<9; j++) { 
				boardButtons[j].setText(""); 
				boardButtons[j].setEnabled(true);
				frame.setTitle("0's and X's");
			}
		}
		}
	}
	public boolean checkWin() {
			for (int i=0;i<3;i++) {
				// Check for horizontal and vertical wins
				if (boardButtons[3*i].getText() == startGame && boardButtons[3*i+1].getText()== startGame && 
						boardButtons[3*i+2].getText() == startGame || boardButtons[i].getText() == startGame && 
								boardButtons[i+3].getText() == startGame && boardButtons[i+6].getText() == startGame)
				{
					return true;	
				}
			}
			// Check for diagonal wins
			if (boardButtons[2].getText() == startGame && boardButtons[4].getText() == startGame && 
					boardButtons[6].getText() == startGame || boardButtons[0].getText() == startGame && 
							boardButtons[4].getText() == startGame && boardButtons[8].getText() == startGame) {
				return true;

			}
			return false;
	}
}
