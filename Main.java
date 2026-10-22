package Package3;

public class Main {


    public static void main(String[] args) {
        // 定义物品类型
        Item stone = new Item("石头", 64);
        Item plank = new Item("木板", 64);
        Item diamondSword = new Item("钻石剑",1 );
        System.out.println("=== 开始运行测试 ===");
        // 1. 创建一个容量为 36 的背包
        Inventory backpack = new Inventory(36);
        System.out.println("\n[操作 1] 创建容量为36的背包");
        backpack.display();

        // 2. 添加 30 个石头
        System.out.println("\n[操作 2] 添加 30 个石头");
        backpack.addItem(new ItemStack(stone,30));
        backpack.display();

        // 3. 测试堆叠：添加 40 个石头 (预期：64 + 6)
        System.out.println("\n[操作 3] 添加 40 个石头 (测试堆叠)");
        int result1 = backpack.addItem(new ItemStack(stone, 40));
        System.out.println("添加结果返回值: " + result1);
        backpack.display();

        // 4. 添加不可堆叠物品：1 把钻石剑
        System.out.println("\n[操作 4] 添加 1 把钻石剑");
        int result2 = backpack.addItem(new ItemStack(diamondSword, 1));
        System.out.println("添加结果返回值: " + result2);
        backpack.display();

        // 5. 测试溢出：尝试添加 35 堆木板 (每堆64个)
        // 当前已占3个格子，剩余33个。添加 35*64 个木板会溢出。
        System.out.println("\n[操作 5] 尝试添加 35*64 个木板 (测试溢出)");
        int totalPlanks = 35 * 64;
        int result3 = backpack.addItem(new ItemStack(plank, totalPlanks));
        System.out.println("添加结果返回值: " + result3 + " (负数表示溢出数量)");
        backpack.display();

        // 6. 移除物品：从背包中移除 10 个石头
        System.out.println("\n[操作 6] 移除 10 个石头");
        int result4 = backpack.removeItem(new ItemStack(stone,10));
        System.out.println("移除后剩余格子数: " + result4);
        backpack.display();

        System.out.println("\n=== 所有测试完成 ===");
    }
}



