package cn.onestravel.view;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class JTextFieldMenu extends JTextField implements MouseListener, KeyListener, UndoableEditListener {

	private static final long serialVersionUID = -2308615404205560110L;

	private JPopupMenu pop = null; // 弹出菜单

	private JMenuItem select = null, copy = null, paste = null, cut = null; // 三个功能菜单
	private UndoManager undoManager = new UndoManager();

	public JTextFieldMenu() {
		super();
		init();
	}

	public JTextFieldMenu(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		init();
	}

	private void init() {
		this.addMouseListener(this);
		pop = new JPopupMenu();
		pop.add(select = new JMenuItem("全选"));
		pop.add(copy = new JMenuItem("复制"));
		pop.add(paste = new JMenuItem("粘贴"));
		pop.add(cut = new JMenuItem("剪切"));
		select.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		this.add(pop);

		this.addKeyListener(this);
		this.getDocument().addUndoableEditListener(this);
	}

	/**
	 * 菜单动作
	 * 
	 * @param e
	 */
	public void action(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(select.getText())) { // 全选
			this.select(0, this.getText().length());
		} else if (str.equals(copy.getText())) { // 复制
			this.copy();
		} else if (str.equals(paste.getText())) { // 粘贴
			this.paste();
		} else if (str.equals(cut.getText())) { // 剪切
			this.cut();
		}
	}

	public JPopupMenu getPop() {
		return pop;
	}

	public void setPop(JPopupMenu pop) {
		this.pop = pop;
	}

	/**
	 * 剪切板中是否有文本数据可供粘贴
	 * 
	 * @return true为有文本数据
	 */
	public boolean isClipboardString() {
		boolean b = false;
		Clipboard clipboard = this.getToolkit().getSystemClipboard();
		Transferable content = clipboard.getContents(this);
		try {
			if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
				b = true;
			}
		} catch (Exception e) {
		}
		return b;
	}

	/**
	 * 文本组件中是否具备复制的条件
	 * 
	 * @return true为具备
	 */
	public boolean isCanCopy() {
		boolean b = false;
		int start = this.getSelectionStart();
		int end = this.getSelectionEnd();
		if (start != end)
			b = true;
		return b;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.requestFocus(false);
			copy.setEnabled(isCanCopy());
			paste.setEnabled(isClipboardString());
			cut.setEnabled(isCanCopy());
			pop.show(this, e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown() && e.getKeyCode() == 'A') {
			System.out.println("Ctrl + A");
			selectAll();
		} else if (e.isControlDown() && e.getKeyCode() == 'C') {
			//todo
			this.copy();

		}  else if (e.isControlDown() && e.getKeyCode() == 'X') {
			//todo
			this.cut();
		} else if (e.isControlDown() && e.getKeyCode() == 'Z') {
			//todo
			if (undoManager.canUndo()) {
				undoManager.undo();
			}
		}else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
			//todo
			if (undoManager.canRedo()) {
				undoManager.redo();
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
	}
}