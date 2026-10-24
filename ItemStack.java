package Package3;

public class ItemStack {
    private final Item item;      //定义物品类型
    private int quantity;       //当前格子内物品的数量


    public ItemStack(Item item, int quantity) {
        if(item == null){
            throw new IllegalArgumentException("物品不能为空");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("数量不能为负");
        }
        this.item = item;
        this.quantity = quantity;
    }       //初始化格子属性

    public Item getItem() {
        return item;
    }       //获取物品类型

    public int getQuantity() {
        return quantity;
    }       //获取当前物品堆积数量

    public void setQuantity(int quantity) {
        if (quantity < 0){
            throw new IllegalArgumentException("数量不能为负！");
        }
        this.quantity = quantity;
    }

    public boolean isFull() {
        return quantity >= item.getMaxStackSize();//判断物品是否还能容纳
    }

    public int remainingSpace() {
        return item.getMaxStackSize() - quantity;       //计算剩余可存放数量
    }

    @Override
    public String toString() {
        return item.getName() + "*" + quantity;     //返回剩余空间
    }
}
