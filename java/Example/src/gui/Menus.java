package gui;

import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class Menus extends JFrame {
	private String[] flavors = {
		"Chocolate", "Strawberry", "Vanilla Fudge Swirl",
		"Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
		"Praline Cream", "Mud Pie"
	};
	private JTextField t = new JTextField("No flavor", 30);
	private JButton b = new JButton("Swap Menus");
	// first menu bar
	private JMenuBar mb1 = new JMenuBar();
	private JMenu
		mFile = new JMenu("File"),
		  mFlavors = new JMenu("Flavors"),
		  mSafety = new JMenu("Safety");
	// Alternative approach:
	private JCheckBoxMenuItem[] mItemSafety = {
		new JCheckBoxMenuItem("Guard"),
		new JCheckBoxMenuItem("Hide")
	};
	private JMenuItem[] mItemFile = { new JMenuItem("Open") };

	// A second menu bar to swap to:
	private JMenuBar mb2 = new JMenuBar();
	private JMenu mFooBar = new JMenu("fooBar");
	private JMenuItem[] mItemFooBar = {
		// Adding a menu shortcut (mnemonic) is very
		// simple, but only JMenuItems can have them
		// in their constructors:
		new JMenuItem("Foo", KeyEvent.VK_F),
		new JMenuItem("Bar", KeyEvent.VK_A),
		new JMenuItem("Baz"),
	};
	private ItemListener safetyCheckBoxListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			JCheckBoxMenuItem target =
				(JCheckBoxMenuItem)e.getSource();
			String actionCommand = target.getActionCommand();
			if(actionCommand.equals("Guard"))
				t.setText("Guard the Ice Cream! " +
						"Guarding is " + target.getState());
			else if(actionCommand.equals("Hide"))
				t.setText("Hide the Ice Cream! " +
						"Is it hidden? " + target.getState());
		}
	};
	private ActionListener mItemFileListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem)e.getSource();
			String actionCommand = target.getActionCommand();
			if(actionCommand.equals("Open")) {
				String s = t.getText();
				boolean chosen = false;
				for(String flavor : flavors)
					if(s.equals(flavor))
						chosen = true;
				if(!chosen)
					t.setText("Choose a flavor first!");
				else
					t.setText("Opening " + s + ". Mmm, mm!");
			}
		}
	};
	private ActionListener mItemFlavorsListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem)e.getSource();
			t.setText(target.getText());
		}
	};
	public Menus() {
		mItemSafety[0].setActionCommand("Guard");
		mItemSafety[0].setMnemonic(KeyEvent.VK_G);
		mItemSafety[0].addItemListener(safetyCheckBoxListener);
		mItemSafety[1].setActionCommand("Hide");
		mItemSafety[1].setMnemonic(KeyEvent.VK_H);
		mItemSafety[1].addItemListener(safetyCheckBoxListener);
		int n = 0;
		// add menu items to "Flavors"
		for(String flavor : flavors) {
			JMenuItem mi = new JMenuItem(flavor);
			mi.addActionListener(mItemFlavorsListener);
			mFlavors.add(mi);
			// Add separators at intervals:
			if((n++ + 1) % 3 == 0)
				mFlavors.addSeparator();
		}
		// add menu items to "File"
		for(JCheckBoxMenuItem mi : mItemSafety)
			mSafety.add(mi);
		mSafety.setMnemonic(KeyEvent.VK_A);
		mFile.add(mSafety);
		mFile.setMnemonic(KeyEvent.VK_F);
		for(int i = 0; i < mItemFile.length; i++) {
			mItemFile[i].addActionListener(mItemFileListener);
			mFile.add(mItemFile[i]);
		}
		mb1.add(mFile);
		mb1.add(mFlavors);
		setJMenuBar(mb1);
		t.setEditable(false);
		add(t, BorderLayout.CENTER);
		// Set up the system for swapping menus:
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(getJMenuBar() == mb1 ? mb2 : mb1);
				validate(); // Refresh the frame
			}
		});
		b.setMnemonic(KeyEvent.VK_S);
		add(b, BorderLayout.NORTH);
		mItemFooBar[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setText("Do the work corresponding to foo");
			}
		});
		mItemFooBar[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setText("Do the work corresponding to bar");
			}
		});
		mItemFooBar[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setText("Do the work corresponding to baz");
			}
		});
		for(JMenuItem mi : mItemFooBar)
			mFooBar.add(mi);
		mFooBar.setMnemonic(KeyEvent.VK_B);
		mb2.add(mFooBar);
	}
	public static void main(String[] args) {
		SwingConsole.run(Menus.class, 300, 200);
	}
}
