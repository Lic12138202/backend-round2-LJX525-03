package Package3;

public class ItemStack {
    private Item item;      //定义物品类型（实例字段，每个堆栈各自一份）
    private int quantity;       //当前格子内物品的数量


    public ItemStack(Item item, int quantity) {
        if (quantity < 0) {     //检验物品数量输入是否合法
            throw new IllegalArgumentException("数量不能为负数");
        }
        this.item = item;
        this.quantity = quantity;
    }       //初始化格子属性：对象出生时就定好是什么、有多少

    public Item getItem() {
        return item;
    }       //获取物品类型

    public int getQuantity() {
        return quantity;
    }       //获取当前物品堆积数量

    public int setQuantity(int i) {
        this.quantity = i;
        return quantity;
    }

    public boolean full() {
        return quantity >= item.getMaxStackSize();//判断物品是否还能容纳
    }

    public int RemainingSpace() {
        return item.getMaxStackSize() - quantity;       //计算剩余可存放数量
    }

    @Override
    public String toString() {
        return item.getName() + "*" + quantity;     //返回剩余空间
    }
}

