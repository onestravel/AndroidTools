package cn.onestravel.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class JTableMenu extends JTable implements MouseListener {

	private static final long serialVersionUID = -2308615404205560110L;

	private JPopupMenu pop = null; // 弹出菜单

	private JMenuItem select = null, copy = null, paste = null, cut = null; // 三个功能菜单

	public JTableMenu() {
		super();
		init();
	}
	
	public JTableMenu(TableModel model) {
		// TODO Auto-generated constructor stub
		super(model);
		init();
		setRawColor();
	}

private void setRawColor() {
	// TODO Auto-generated method stub
	TableColumnModel tcm = getColumnModel();
	for (int j = 0; j < tcm.getColumnCount(); j++) {
		TableColumn tc = tcm.getColumn(j);
		tc.setCellRenderer(new RowColorRenderer());
		
	}
	
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
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JTableMenu.this.getSelectedRow()<0||JTableMenu.this.getSelectedColumn()<0) {
					return;
				}
				JTableMenu.this.editCellAt(JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn());
				JTableMenu.this.setSelectionBackground(Color.WHITE);
				Color background=Color.decode("#33b7f5");//得到所选颜色
				String value =  (String) JTableMenu.this.getModel().getValueAt(JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn());
				JComponent mJComponent = ((JComponent) getCellEditor().getTableCellEditorComponent(JTableMenu.this, value, true, JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn()));
				 mJComponent.setBorder(new LineBorder(background));
				mJComponent.requestFocus();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 if(JTableMenu.this.isCellSelected(JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn())){
					 JTableMenu.this.editCellAt(JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn());
				     Color background=Color.decode("#33b7f5");//得到所选颜色
				     String value =  (String) JTableMenu.this.getModel().getValueAt(JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn());
				     JComponent mJComponent = ((JComponent) getCellEditor().getTableCellEditorComponent(JTableMenu.this, value, true, JTableMenu.this.getSelectedRow(), JTableMenu.this.getSelectedColumn()));
				     mJComponent.setBorder(new LineBorder(background));
				     mJComponent.requestFocus();
				    }
				    
				   }
			
		});
	}

	/**
	 * 菜单动作
	 * 
	 * @param e
	 */
	public void action(ActionEvent e) {
//		String str = e.getActionCommand();
//		if (str.equals(select.getText())) { // 全选
//			this.select(0, this.getText().length());
//		} else if (str.equals(copy.getText())) { // 复制
//			this.copy();
//		} else if (str.equals(paste.getText())) { // 粘贴
//			this.paste();
//		} else if (str.equals(cut.getText())) { // 剪切
//			this.cut();
//		}
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
//		int start = this.getSelectionStart();
//		int end = this.getSelectionEnd();
//		if (start != end)
//			b = true;
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
			
			 int modifiers = e.getModifiers(); 
             modifiers -= MouseEvent.BUTTON3_MASK; 
             modifiers |= MouseEvent.BUTTON1_MASK; 
             MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), modifiers, e.getX(), e .getY(), e.getClickCount(), false); 
             this.dispatchEvent(ne); 
		}
		
	}

	public void mouseReleased(MouseEvent e) {
	}
	
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private class RowColorRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
  
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            Component c = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            ((JComponent) c).setBorder(new LineBorder(Color.decode("#e1e1e1"),1));
//            if (value instanceof Calendar) {
//                Calendar cal = (Calendar) value;
//  
//                if (cal.after(shieldCalendar)) {
//                    setBackground(Color.red);
//                } else if (cal.equals(shieldCalendar)) {
//                    setBackground(Color.blue);
//                } else {
//                    setBackground(Color.green);
//                }
//  
//                DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);
//                String s = f.format(cal.getTime());
//                setText(s);
//  
//            } else {
//                setBackground(Color.cyan);
//            }
            return c;
        }
    }

}