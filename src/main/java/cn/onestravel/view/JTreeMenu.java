package cn.onestravel.view;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class JTreeMenu extends JTree {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;

    public JTreeMenu() {
        super();
        root = this.loadJsonData("");
        model = new DefaultTreeModel(root);
        model.setRoot(root);
        this.setModel(model);
        //设置是否显示根节点的“展开/折叠”图标,默认是false
        this.setShowsRootHandles(true);
        //设置节点是否可见,默认是true
        this.setRootVisible(true);
        this.addMouseListener(new LinktoEvent());
        this.setCellRenderer(new CellRender());
        expandAll(new TreePath(root), true);
    }

    public JTreeMenu(DefaultMutableTreeNode root, DefaultTreeModel model) {
        this.root = root;
        this.model = model;
    }

    public JTreeMenu(Object[] value, DefaultMutableTreeNode root, DefaultTreeModel model) {
        super(value);
        this.root = root;
        this.model = model;
    }

    public JTreeMenu(Vector<?> value, DefaultMutableTreeNode root, DefaultTreeModel model) {
        super(value);
        this.root = root;
        this.model = model;
    }

    public JTreeMenu(Hashtable<?, ?> value, DefaultMutableTreeNode root, DefaultTreeModel model) {
        super(value);
        this.root = root;
        this.model = model;
    }

    public JTreeMenu(TreeNode root, DefaultMutableTreeNode root1, DefaultTreeModel model) {
        super(root);
        this.root = root1;
        this.model = model;
    }

    public JTreeMenu(TreeNode root, boolean asksAllowsChildren, DefaultMutableTreeNode root1, DefaultTreeModel model) {
        super(root, asksAllowsChildren);
        this.root = root1;
        this.model = model;
    }

    public JTreeMenu(TreeModel newModel, DefaultMutableTreeNode root, DefaultTreeModel model) {
        super(newModel);
        this.root = root;
        this.model = model;
    }


    public void setData(String json) {
        reBuild(json);
    }

    private DefaultMutableTreeNode loadJsonData(String json) {
        Hashtable table = new Hashtable();
        Object object = JSON.parse(json);
        return analysisJson("Root", object);
    }


    /**
     * 将JSONArray 数据装入Hashtable
     *
     * @param objJson
     */
    public DefaultMutableTreeNode analysisJson(String keyNode, Object objJson) {
        DefaultMutableTreeNode fujiedian = new DefaultMutableTreeNode("" + keyNode + "");
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                fujiedian.add(analysisJson(i + "", objArray.get(i)));
            }
        } else if (objJson instanceof JSONObject) {//如果为json对象
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    fujiedian.add(analysisJson(key, objArray));
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    fujiedian.add(analysisJson(key, (JSONObject) object));
                }
                //如果key中是其他
                else {
                    System.out.println("[" + key + "]:" + object.toString() + " ");
                    DefaultMutableTreeNode parentNode = addParentNode(fujiedian, "" + key + "");
                    //是文件的话直接生成节点，并把该节点加到对应父节点上
//                    addChildNode(fujiedian,"" + key + ":" + object.toString()+"");
//                    fujiedian.add(temp);
                    addChildNode(parentNode, "" + object.toString() + "");
                }
            }
        }
        return fujiedian;
    }

    // 展开树的所有节点的方法
    public void expandAll(TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(path, expand);
            }
        }
        if (expand) {
            this.expandPath(parent);
        } else {
            this.collapsePath(parent);
        }
    }


    public DefaultMutableTreeNode addParentNode(String nodeData) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeData);
        ((DefaultMutableTreeNode) model.getRoot()).add(newNode);
        return newNode;
    }


    public DefaultMutableTreeNode addParentNode(DefaultMutableTreeNode parentNode,
                                                String nodeData) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeData);
        parentNode.add(newNode);
        return newNode;
    }


    public void addChildNode(DefaultMutableTreeNode node, String nodeData) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(nodeData);
        node.add(childNode);
    }


    private void reBuild(String json) {
        try {
            root.removeAllChildren();
            root = this.loadJsonData(json);
            model = new DefaultTreeModel(root);
            model.setRoot(root);
            this.setModel(model);
            expandAll(new TreePath(root), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class LinktoEvent extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {
            DefaultMutableTreeNode node;
            TreePath selectedPath = JTreeMenu.this.getPathForLocation(me.getX(), me.getY());
            if (selectedPath != null) {
                node = (DefaultMutableTreeNode)
                        selectedPath.getLastPathComponent();
//                if (node != null && node.isLeaf()) {
//                    String nd = (String) node.getUserObject();
//                    //The leaf that has url will be display a dialog.
//                        JOptionPane.showConfirmDialog(JTreeMenu.this, nd);
//                        //You can implment your function.
//                }
            }
        }
    }
}
