import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) {
        //创建食物对象
        Food shrimpDumplings=new Food("虾饺皇",23);
        Food porkKnuckleRice=new Food("隆江猪脚饭",20);
        Food hamburg=new Food("麦当劳",15);
        Food gruel=new Food("厚弥粥",10);
        Food salad=new Food("轻食沙拉",12);
        Food sushi=new Food("鳗鱼寿司",30);
        Food chickenRice=new Food("黄焖鸡米饭",14);
        Food crockpotRice=new Food("瓦罐饭",18);

        //将食物对象添加到集合中
        ArrayList<Food>foodList=new ArrayList<>();
        Collections.addAll(foodList,shrimpDumplings,porkKnuckleRice,hamburg,gruel,salad,sushi,chickenRice,crockpotRice);

        //生成并打印本周食物清单
        printFoodList(foodList);

    }



    //获取并打印一周的食物清单的方法
    public static void printFoodList(ArrayList<Food> foodList) {
        //创建三个常量来限制食物出现次数
        final int X = 15;
        final int N = 7;
        final int M = 5;

        //创建变量记录一周内价格超过X元的食物的次数
        int overPriceNum = 0;

        //创建食物计数器
        HashMap<Food, Integer> count = new HashMap<>();

        //创建一周食物清单
        ArrayList<Food[]> weekFoodList = new ArrayList<>();

        //创建随机数用于生成食物
        Random random = new Random();

        //多次循环完成本周食物清单
        for (int i = 0; i < 7; i++) {
            //创建数组存储当天的食物
            Food[] dailyFood = new Food[2];
            //通过循环获取当天的两次食物
            for (int j = 0; j < 2; j++) {
                //通过循环获得符合要求的食物
                Food f;
                do {
                    //随机生成索引并获取该索引的食物
                    int foodIndex = random.nextInt(0, foodList.size());
                    f = foodList.get(foodIndex);

                    //统计该食物的价格
                    int price = f.getPrice();
                    //判断一：是否满足一周内吃价格超过X元的食物的次数不能超过N次
                    // 如果该食物价格超过X元
                    if (price > X) {
                        //如果本周价格超过X元的食物次数没有超标，则次数加1
                        if (overPriceNum < N) {
                            overPriceNum++;
                        }
                        //如果超标了，则继续循环获取食物
                        else {
                            continue;
                        }
                    }

                    //判断二：是否满足一周内吃重复食物的次数不能超过M次
                    // 如果计数器中存在该种食物，判断重复次数是否合格
                    if (count.containsKey(f)) {
                        //获取该食物的出现次数
                        int num = count.get(f);
                        //如果次数合格，则次数加一,并跳出循环，说明已经获得了符合要求的食物
                        if (num < M) {
                            num++;
                            count.put(f, num);
                            break;
                        }
                    }
                    //如果计数器中不存在该种食物，则添加该食物，并跳出循环，说明已经获得了符合要求的食物
                    else {
                        count.put(f, 1);
                        break;
                    }
                } while (true);

                //将获得的符合要求的食物添加到当天食物的数组中
                dailyFood[j] = f;
            }

            //将当天的食物数组添加到本周食物清单中
            weekFoodList.add(dailyFood);
        }

        //此时已经生成了本周食物清单，下面对本周食物清单进行打印
        //打表
        String[] week = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

        //打印本周食物清单
        System.out.println("----本周食物清单----");
        for (int i = 0; i < weekFoodList.size(); i++) {
            //获取当日食物
            Food[] foods = weekFoodList.get(i);
            System.out.println(week[i] + " —— 午餐：" + foods[0].getName() + " " + foods[0].getPrice() + "元" +
                    "  晚餐：" + foods[1].getName() + " " + foods[1].getPrice() + "元");
        }
        System.out.println();

        //打印部分统计数据
        System.out.println("----本周计划前瞻----");
        count.forEach(new BiConsumer<Food, Integer>() {
            @Override
            public void accept(Food food, Integer integer) {
                System.out.println(food.getName()+" "+integer+"次");
            }
        });
        System.out.println("本周计划吃超过"+X+"元的食物 "+overPriceNum+"次");
    }
}
