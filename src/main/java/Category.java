public class Category implements Comparable {
    public String category;
    public int sum;

    public Category(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public void addSum(int sum) {
        this.sum += sum;
    }

    @Override
    public int compareTo(Object o) {
        Category cat = (Category) o;
        Integer i = this.sum;
        Integer k = cat.sum;
        return i.compareTo(k);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", sum=" + sum +
                '}';
    }
}
