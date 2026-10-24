package Package3;

public class Item {
    private String name;        //引入name属性
    private int maxStackSize;       //引入maxStackSize属性

    public Item(String name, int maxStackSize) {
        //当输入值不合法时抛出异常终止程序
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("物品名称不能为空");
        if(maxStackSize <= 0)
            throw new IllegalArgumentException("最大堆叠数必须为正数");
        this.name = name;
        this.maxStackSize = maxStackSize;
    }

    public String getName() {
        return name;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
@Override
public String toString(){
    return name;
    }
}
