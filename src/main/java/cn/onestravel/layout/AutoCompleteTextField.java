package cn.onestravel.layout;

import cn.onestravel.view.JTextFieldMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AutoCompleteTextField extends JPanel {
    private JPanel panel1;
    private JComboBox cbInputHint;
    private JTextFieldMenu tfInput;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public AutoCompleteTextField() {
        initViewData();
    }

    public void initViewData() {

//        ArrayList<String> items = new ArrayList<String>();
//        Map map = getStationMap(StationConstant.stationString1,
//                StationConstant.stationString2);
//        Iterator iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            String stationName = iterator.next().toString();
//            items.add(stationName);
//        }
        tfInput.setColumns(30);
        cbInputHint.setSize(300,0);
    }

    /**
     * 设置输入框文本
     *
     * @return
     */
    public void setText(String text) {
        if (tfInput != null) {
            tfInput.setText(text);
        }
    }

    /**
     * 获取输入框文本
     *
     * @return
     */
    public String getText() {
        if (tfInput != null) {
            return tfInput.getText();
        }
        return "";
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public void setAutoCompleteData(final ArrayList<String> items) {
        setAutoCompleteData(tfInput, items);
    }

    public void setAutoCompleteData(final JTextField txtInput,
                                    final ArrayList<String> items) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        cbInputHint.setModel(model);
        cbInputHint.setPreferredSize(getPreferredSize());
        cbInputHint = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInputHint, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInputHint.setSelectedItem(null);
        cbInputHint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInputHint)) {
                    if (cbInputHint.getSelectedItem() != null) {
                        txtInput.setText(cbInputHint.getSelectedItem().toString());
                    }
                }
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInputHint, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInputHint.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER
                        || e.getKeyCode() == KeyEvent.VK_UP
                        || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInputHint);
                    cbInputHint.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtInput.setText(cbInputHint.getSelectedItem().toString());
                        cbInputHint.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInputHint.setPopupVisible(false);
                }
                setAdjusting(cbInputHint, false);
            }
        });
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                try {
                    setAdjusting(cbInputHint, true);
                    model.removeAllElements();
                    String input = txtInput.getText();
                    if (!input.isEmpty()) {
                        for (String item : items) {
                            if (item.toLowerCase().startsWith(input.toLowerCase())) {
                                model.addElement(item);
                            }
                        }
                    }
                    if (model.getSize() > 0) {
                        cbInputHint.setPopupVisible(true);
                    } else {
                        cbInputHint.setPopupVisible(false);
                    }
                    setAdjusting(cbInputHint, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInputHint, BorderLayout.SOUTH);
    }

    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 0);
    }

}
