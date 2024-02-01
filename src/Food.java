import java.util.Objects;

public class Food {
    //食物的名字
    private String name;
    //食物的价格
    private int price;

    //有无参构造和getter and setter
    public Food() {
    }

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //重写equals方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return price == food.price && Objects.equals(name, food.name);
    }

    //重写hashCode方法
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
