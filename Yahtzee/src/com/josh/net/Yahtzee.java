package com.josh.net;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Yahtzee {

	private JFrame frame = new JFrame("Yahtzee");
	private JPanel panel = new JPanel(new BorderLayout());
	private JButton roll = new JButton("Roll");
	private JButton ones;
	private JButton twos;
	private JButton threes;
	private JButton fours;
	private JButton fives;
	private JButton sixes;
	private JButton fullHouseButton;
	private JButton smallStraightButton;
	private JButton largeStraightButton;
	private JButton yahtzeeButton;
	private JButton chanceButton;
	private JButton threeOfAKindButton;
	private JButton fourOfAKindButton;
	private JButton dice1, dice2, dice3, dice4, dice5;
	private JTextArea scoreText;
	private JPanel subPanel = new JPanel();
	private JPanel subPanel2 = new JPanel();

	private static int[] die = new int[5];
	private int oneSum = 0;
	private int twoSum = 0;
	private int threeSum = 0;
	private int fourSum = 0;
	private int fiveSum = 0;
	private int sixSum = 0;
	private int fullHouse = 0;
	private int smallStraight = 0;
	private int largeStraight = 0;
	private int yahtzee = 0;
	private int threeOfAKind = 0;
	private int fourOfAKind = 0;
	private int chance = 0;
	private int score;
	private Integer[] nums = new Integer[6];
	private String[] pic = new String[6];
	private boolean[] used = new boolean[11];
	boolean turnTaken = false;

	public static void main(String[] args) {
		Yahtzee y = new Yahtzee();
		y.start();
	}

	public Yahtzee() {
		setUpWindow();
		paint();

	}

	private void setUpWindow() {
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void repaint() {
		getPic();
		computeScore();
		dice1.setIcon(new ImageIcon(pic[0]));
		dice2.setIcon(new ImageIcon(pic[1]));
		dice3.setIcon(new ImageIcon(pic[2]));
		dice4.setIcon(new ImageIcon(pic[3]));
		dice5.setIcon(new ImageIcon(pic[4]));

		ones.setText("Score ones: " + oneSum);
		twos.setText("Score twos: " + twoSum);
		threes.setText("Score threes: " + threeSum);
		fours.setText("Score fours: " + fourSum);
		fives.setText("Score fives: " + fiveSum);
		sixes.setText("Score sixes: " + sixSum);
		threeOfAKindButton.setText("Score three of a kind: " + threeOfAKind);
		fourOfAKindButton.setText("Score four of a kind: " + fourOfAKind);
		fullHouseButton.setText("Score full house: " + fullHouse);
		yahtzeeButton.setText("Score yahtzee: " + yahtzee);
		chanceButton.setText("Score chance: " + chance);
		scoreText.setText("Score: " + Integer.toString(score));

		ones.repaint();
		twos.repaint();
		threes.repaint();
		fours.repaint();
		fives.repaint();
		sixes.repaint();
		fullHouseButton.repaint();
		yahtzeeButton.repaint();
		chanceButton.repaint();
		scoreText.repaint();

		dice1.repaint();
		dice2.repaint();
		dice3.repaint();
		dice4.repaint();
		dice5.repaint();
	}

	private void paint() {
		getPic();

		dice1 = new JButton(new ImageIcon(pic[0]));
		dice2 = new JButton(new ImageIcon(pic[1]));
		dice3 = new JButton(new ImageIcon(pic[2]));
		dice4 = new JButton(new ImageIcon(pic[3]));
		dice5 = new JButton(new ImageIcon(pic[4]));

		subPanel.add(dice1);
		subPanel.add(dice2);
		subPanel.add(dice3);
		subPanel.add(dice4);
		subPanel.add(dice5);

		ones = new JButton("Score ones: " + oneSum);
		twos = new JButton("Score twos: " + twoSum);
		threes = new JButton("Score threes: " + threeSum);
		fours = new JButton("Score fours: " + fourSum);
		fives = new JButton("Score fives: " + fiveSum);
		sixes = new JButton("Score sixes: " + sixSum);
		threeOfAKindButton = new JButton("Score three of a kind: "
				+ threeOfAKind);
		fourOfAKindButton = new JButton("Score four of a kind: " + fourOfAKind);
		fullHouseButton = new JButton("Score full house: " + fullHouse);
		yahtzeeButton = new JButton("Score yahtzee: " + yahtzee);
		chanceButton = new JButton("Score chance: " + chance);

		subPanel2.add(ones);
		subPanel2.add(twos);
		subPanel2.add(threes);
		subPanel2.add(fours);
		subPanel2.add(fives);
		subPanel2.add(sixes);
		subPanel2.add(threeOfAKindButton);
		subPanel2.add(fourOfAKindButton);
		subPanel2.add(fullHouseButton);
		subPanel2.add(yahtzeeButton);
		subPanel2.add(chanceButton);

		scoreText = new JTextArea("Score: " + Integer.toString(score));

		panel.add(subPanel, BorderLayout.PAGE_START);
		panel.add(subPanel2, BorderLayout.CENTER);
		panel.add(scoreText, BorderLayout.LINE_END);
		panel.add(roll, BorderLayout.SOUTH);
		frame.add(panel);
	}

	private void getPic() {

		for (int i = 0; i < 5; i++) {
			switch (die[i]) {
			case 1:
				pic[i] = "res/Dice" + Integer.toString(1) + ".png";
				break;
			case 2:
				pic[i] = "res/Dice" + Integer.toString(2) + ".png";
				break;
			case 3:
				pic[i] = "res/Dice" + Integer.toString(3) + ".png";
				break;
			case 4:
				pic[i] = "res/Dice" + Integer.toString(4) + ".png";
				break;
			case 5:
				pic[i] = "res/Dice" + Integer.toString(5) + ".png";
				break;
			case 6:
				pic[i] = "res/Dice" + Integer.toString(6) + ".png";
				break;
			default:
				pic[i] = "res/Dice" + Integer.toString(1) + ".png";
				break;
			}
		}
	}

	private void roll() {
		Random r = new Random();

		for (int i = 0; i < 5; i++) {
			die[i] = r.nextInt(6) + 1;
		}

	}

	private void computeScore() {
		oneSum = 0;
		twoSum = 0;
		threeSum = 0;
		fourSum = 0;
		fiveSum = 0;
		sixSum = 0;
		fullHouse = 0;
		smallStraight = 0;
		largeStraight = 0;
		yahtzee = 0;
		threeOfAKind = 0;
		fourOfAKind = 0;
		chance = 0;

		for (int j = 0; j < 6; j++) {
			nums[j] = 0;
		}

		for (int i = 0; i < 5; i++) {
			if (die[i] == 1) {
				oneSum++;
				nums[0]++;
			}
			if (die[i] == 2) {
				twoSum += 2;
				nums[1]++;
			}
			if (die[i] == 3) {
				threeSum += 3;
				nums[2]++;
			}
			if (die[i] == 4) {
				fourSum += 4;
				nums[3]++;
			}
			if (die[i] == 5) {
				fiveSum += 5;
				nums[4]++;
			}
			if (die[i] == 6) {
				sixSum += 6;
				nums[5]++;
			}
			chance += die[i];
		}

		if (Arrays.asList(nums).contains(3)) {
			if (Arrays.asList(nums).contains(2))
				fullHouse = 25;
			if (Arrays.asList(nums[0]).contains(3))
				threeOfAKind = oneSum;
			else if (Arrays.asList(nums[1]).contains(3))
				threeOfAKind = twoSum;
			else if (Arrays.asList(nums[2]).contains(3))
				threeOfAKind = threeSum;
			else if (Arrays.asList(nums[3]).contains(3))
				threeOfAKind = fourSum;
			else if (Arrays.asList(nums[4]).contains(3))
				threeOfAKind = fiveSum;
			else if (Arrays.asList(nums[5]).contains(3))
				threeOfAKind = sixSum;
		}

		if (Arrays.asList(nums).contains(4)) {
			if (Arrays.asList(nums[0]).contains(4))
				fourOfAKind = oneSum;
			else if (Arrays.asList(nums[1]).contains(4))
				fourOfAKind = twoSum;
			else if (Arrays.asList(nums[2]).contains(4))
				fourOfAKind = threeSum;
			else if (Arrays.asList(nums[3]).contains(4))
				fourOfAKind = fourSum;
			else if (Arrays.asList(nums[4]).contains(4))
				fourOfAKind = fiveSum;
			else if (Arrays.asList(nums[5]).contains(4))
				fourOfAKind = sixSum;
		}

		if (Arrays.asList(nums).contains(5))
			yahtzee = 50;
	}

	private void checkStatus() {
		int numUsed = 0;
		for (int i = 0; i < 11; i++) {
			if (used[i])
				numUsed++;
		}
		if (numUsed == 10) {
			repaint();
			JOptionPane.showMessageDialog(null, "Game Over! Your score was: "
					+ score, "Game Over", JOptionPane.OK_CANCEL_OPTION);
			turnTaken = true;
		}
	}

	private void start() {
		roll();
		repaint();
		

		roll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (turnTaken) {
					roll();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null,
							"You've already rolled!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}
				turnTaken = false;
			}
		});
		ones.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[0] && !turnTaken) {
					score += oneSum;
					turnTaken = true;
					ones.setBackground(Color.RED);
					ones.setForeground(Color.WHITE);
					checkStatus();
					used[0] = true;
					repaint();
				} else if (used[0]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		twos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[1] && !turnTaken) {
					score += twoSum;
					turnTaken = true;
					twos.setBackground(Color.RED);
					twos.setForeground(Color.WHITE);
					checkStatus();
					used[1] = true;
					repaint();
				} else if (used[1]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		threes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[2] && !turnTaken) {
					score += threeSum;
					turnTaken = true;
					threes.setBackground(Color.RED);
					threes.setForeground(Color.WHITE);
					checkStatus();
					used[2] = true;
					repaint();
				} else if (used[2]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		fours.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[3] && !turnTaken) {
					score += fourSum;
					turnTaken = true;
					fours.setBackground(Color.RED);
					fours.setForeground(Color.WHITE);
					checkStatus();
					used[3] = true;
					repaint();
				} else if (used[3]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		fives.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[4] && !turnTaken) {
					score += fiveSum;
					turnTaken = true;
					fives.setBackground(Color.RED);
					fives.setForeground(Color.WHITE);
					checkStatus();
					used[4] = true;
					repaint();
				} else if (used[4]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		sixes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[5] && !turnTaken) {
					score += sixSum;
					turnTaken = true;
					sixes.setBackground(Color.RED);
					sixes.setForeground(Color.WHITE);
					checkStatus();
					used[5] = true;
					repaint();
				} else if (used[5]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		threeOfAKindButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[6] && !turnTaken) {
					score += threeOfAKind;
					turnTaken = true;
					threeOfAKindButton.setBackground(Color.RED);
					threeOfAKindButton.setForeground(Color.WHITE);
					checkStatus();
					used[6] = true;
					repaint();
				} else if (used[6]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		fourOfAKindButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[7] && !turnTaken) {
					score += fourOfAKind;
					turnTaken = true;
					fourOfAKindButton.setBackground(Color.RED);
					fourOfAKindButton.setForeground(Color.WHITE);
					checkStatus();
					used[7] = true;
					repaint();
				} else if (used[7]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		fullHouseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[8] && !turnTaken) {
					score += fullHouse;
					turnTaken = true;
					fullHouseButton.setBackground(Color.RED);
					fullHouseButton.setForeground(Color.WHITE);
					checkStatus();
					used[8] = true;
					repaint();
				} else if (used[8]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		yahtzeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[9] && !turnTaken) {
					score += yahtzee;
					turnTaken = true;
					yahtzeeButton.setBackground(Color.RED);
					yahtzeeButton.setForeground(Color.WHITE);
					checkStatus();
					used[9] = true;
					repaint();
				} else if (used[9]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		chanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!used[10] && !turnTaken) {
					score += chance;
					turnTaken = true;
					chanceButton.setBackground(Color.RED);
					chanceButton.setForeground(Color.WHITE);
					checkStatus();
					used[10] = true;
					repaint();
				} else if (used[10]) {
					JOptionPane.showMessageDialog(null,
							"You've already used this option", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"You have already used your turn!", "Uh oh...",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
	}
}