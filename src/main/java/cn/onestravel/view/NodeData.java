package cn.onestravel.view;

public class NodeData  {
    private String name;
    private Object value;
    private boolean isLeaf = false;

    public NodeData() {
    }

    public NodeData(String name) {
        this.name = name;
    }

    public NodeData(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public NodeData(String name, Object value, boolean isLeaf) {
        this.name = name;
        this.value = value;
        this.isLeaf = isLeaf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String toString()
    {
        if(this.isLeaf())
        {
            return String.valueOf(value);
        }
        else
        {
            return this.name;
        }
    }

}