package Package3;


import java.util.ArrayList;
import java.util.List;      //引入java的表格工具
import java.util.Objects;


public class Inventory {
    int capacity = 36;       //格子的个数
    List<ItemStack> items;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();       //创建物品堆列表
    }


    //向背包中添加物品
    public int addItem(ItemStack ItemStackToAdd) {
        Item newItem = ItemStackToAdd.getItem();        //要用实例方法调取数据！！
        int amountToAdd = ItemStackToAdd.getQuantity();      //amountToAdd要添加的物品数量
        int maxStackSize = ItemStackToAdd.getItem().getMaxStackSize();      //格子最大容纳量

        if (newItem == null || amountToAdd <= 0) {
            return Math.max(amountToAdd, 0);
        }           // 判断输入是否合法

            //遍历列表，在背包中找到同类物品
        for (int i = 0; i < items.size(); i++) {
            ItemStack currentStack = items.get(i);
            int RemainingSpace = currentStack.RemainingSpace();     //当前堆还能装多少
            if (Objects.equals(newItem.getName(), currentStack.getItem().getName())) {
                //如果剩余空间大于0，装入
                if (RemainingSpace > 0) {
                    int put = Math.min(amountToAdd, RemainingSpace);
                    currentStack.setQuantity(currentStack.getQuantity() + put);
                    amountToAdd -= put;     //把一部分物品装入当前格子
                    if (amountToAdd == 0) {
                        return 0;       //全部装完
                    }
                }
            }
        }
        //如果还有多余物品，则开新格子
        while(amountToAdd > 0 && items.size() < capacity) {
            int put = Math.min(amountToAdd,maxStackSize);
            items.add(new ItemStack(ItemStackToAdd.getItem(),put));
            amountToAdd -= put;
        }
        //无空背包，物品溢出
        if (amountToAdd > 0) {      //默认为items.size() >= capacity的情况
            System.out.println("背包已装满！剩余" + amountToAdd + "个" + ItemStackToAdd.getItem().getName() + "未装入");
        }
        return -amountToAdd;
    }

    //从背包中移除物品
    public int removeItem(ItemStack ItemStackToRemove) {
        String itemName = ItemStackToRemove.getItem().getName();   //从入参栈取物品名，不能用类名静态调用
        int amountToRemove = ItemStackToRemove.getQuantity();      //amountToRemove要移除的物品数量
        int maxStackSize = ItemStackToRemove.getItem().getMaxStackSize();


        //遍历列表，在背包中找到相同物品所在格子
        for (int i = 0; i <= items.size(); i++) {
            ItemStack currentStack = items.get(i);
            //先计算背包里到底有多少个这个物品
            int totalAvailable = 0;
            if (currentStack.getItem().getName().equals(itemName)) {
                totalAvailable += currentStack.getQuantity();
                int remainingItems = ItemStackToRemove.getQuantity() - amountToRemove;
                if (amountToRemove <= totalAvailable) {
                    if (ItemStackToRemove.getQuantity() == 0) {
                        break;
                    }
                    if (remainingItems >= 0) {
                        currentStack.setQuantity(currentStack.getQuantity() - amountToRemove);
                        break;
                    } else if (remainingItems < 0) {
                        amountToRemove -= ItemStackToRemove.getQuantity();
                        currentStack.setQuantity(0);
                        break;
                    }
                    return 0;
                }
                if (amountToRemove > totalAvailable) {
                    if (ItemStackToRemove.getQuantity() == 0) {
                        break;
                    }
                    if (remainingItems >= 0) {
                        currentStack.setQuantity(currentStack.getQuantity() - amountToRemove);
                        break;
                    } else if (remainingItems < 0) {
                        amountToRemove -= ItemStackToRemove.getQuantity();
                        currentStack.setQuantity(0);
                        break;
                    }
                    return amountToRemove - totalAvailable;     //返回移除失败的物品个数
                }

            }

        }
        return capacity - items.size();
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
            int totalAvailable1 = 0;
            int totalAvailable2 = 0;
            int totalAvailable3 = 0;

            for (int i = 0; i < items.size(); i++) {
                ItemStack currentStack = items.get(i);

                switch (currentStack.getItem().getName()) {
                    case "石头":
                        System.out.println("格子" + (i + 1) + ":" + "石头*" + currentStack.getQuantity());
                        totalAvailable1 += currentStack.getQuantity();
                        break;
                    case "木板":
                        System.out.println("格子" + (i + 1) + ":" + "木板*" + currentStack.getQuantity());
                        totalAvailable2 += currentStack.getQuantity();
                        break;
                    case "钻石剑":
                        System.out.println("格子" + (i + 1) + ":" + "钻石剑*" + currentStack.getQuantity());
                        totalAvailable3 += currentStack.getQuantity();
                        break;
                    default:
                        System.out.println("格子" + i + ": " + currentStack.getItem().getName() + "*" + currentStack.getQuantity());
                        break;
                }
            }
            System.out.println("背包内一共有" + totalAvailable1 + "个石头。");
            System.out.println("背包内一共有" + totalAvailable2 + "个木板。");
            System.out.println("背包内一共有" + totalAvailable3 + "个钻石剑。");
        }

        System.out.println("已用格子: " + items.size() + "/" + capacity);
        System.out.println("-----------------------------");
    }
}



