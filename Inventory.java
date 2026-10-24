package Package3;


import java.util.*;

public class Inventory {
    private final int capacity;       //格子的个数
    private final List<ItemStack> items;        //泛型 列表里只能放物品堆

    public Inventory(int capacity) {
        if (capacity < 0){
            throw new IllegalArgumentException("容量必须为正数");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();       //创建物品堆列表
    }

    public int getCapacity() {
        return capacity;
    }       //读取背包容量

    public int freeSlots(){
        return capacity - items.size();
    }       //定义空格子数量

    //向背包中添加物品
    public int addItem(ItemStack itemStackToAdd) {

        /**当添加的物品堆为空时
         * 直接返回剩余空格子数量*/
        if(itemStackToAdd == null){
            return freeSlots();
        }

        Item item = itemStackToAdd.getItem();        //要用实例方法调取数据！！
        int amountToAdd = itemStackToAdd.getQuantity();      //amountToAdd要添加的物品数量
        int maxStackSize = item.getMaxStackSize();      //格子最大容纳量
        /**当添加的物品为空或数量为0 等于没加 返回空格子数*/
        if (amountToAdd == 0){
            return freeSlots();
        }

            //遍历列表，在背包中找到同类物品
        for (ItemStack stack : items) {     //把items中的元素依次取出放进stack
            //当要加的物品数量为零时，结束循环
            if(amountToAdd == 0){
                break;
            }
            //不是同类物品时跳过
            if (!item.getName().equals(stack.getItem().getName())) {
                continue;}
            //同类物品背包已满时跳过
            if (stack.isFull()){
                continue;
            }
            //同类物品且背包未满 加东西
            int put =Math.min(amountToAdd, stack.remainingSpace());
            stack.setQuantity(stack.getQuantity() + put);
            amountToAdd -= put;
            }

        //如果还有多余物品，则开新格子
        while(amountToAdd > 0 && items.size() < capacity) {
            int put = Math.min(amountToAdd,maxStackSize);
            items.add(new ItemStack(item,put));
            amountToAdd -= put;
        }
        //无空背包，物品溢出
        if (amountToAdd > 0) {      //默认为items.size() >= capacity的情况
            System.out.println("背包已装满！剩余" + amountToAdd + "个" + item.getName() + "未装入");
            return -amountToAdd;
        }
        return freeSlots();
    }

    //从背包中移除物品
    public int removeItem(String itemName,int amountToRemove) {
        if (itemName == null || amountToRemove <= 0) {
            return freeSlots();
        }
        int remaining = amountToRemove;     //remaining剩余要移除的东西

        Iterator<ItemStack> it = items.iterator();      //使用迭代器删空堆
        while (it.hasNext() && remaining > 0) {
            ItemStack stack = it.next();
            if (!stack.getItem().getName().equals(itemName)) {
                continue;
            }
            int take = Math.min(amountToRemove, stack.getQuantity());
            stack.setQuantity(stack.getQuantity() - take);
            remaining -= take;
            if (stack.getQuantity() == 0) {
                it.remove();
            }
        }
        if (remaining > 0) {
            System.out.println("背包中「" + itemName + "」不足，还差 " + remaining + " 个未移除");
        }
        return freeSlots();
    }
    //获取某种物品的总数量
    public int getItemCount(String itemName) {
        int total = 0;
        for (ItemStack stack : items) {
            if (stack.getItem().getName().equals(itemName)) {
                total += stack.getQuantity();
            }
        }
        return total;
    }

     //显示背包当前状态
        public void display(){
        System.out.println("\n--- 背包状态 (容量: " + capacity + ") ---");
        if (items.isEmpty()) {
            System.out.println("[空]");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("格子" + (i + 1) + "：" + items.get(i));
            }
            //调用LinkedHashMap
            Map<String,Integer> totals = new LinkedHashMap<>();
            for(ItemStack stack : items){
                totals.merge(stack.getItem().getName(), stack.getQuantity(), Integer::sum);
            }
            for (Map.Entry<String,Integer> entry : totals.entrySet()){
                System.out.println("背包内一共有" + entry.getValue() + "个" + entry.getKey() + "。");
            }
        }
        System.out.println("已用格子: " + items.size() + "/" + capacity);
        System.out.println("-----------------------------");
    }
}

