public class Main {
    public static void main(String[] args) {
        MyArrayList<String> strings = new MyArrayList<>();
        strings.add("Maxim");
        strings.add("Java");
        strings.add("Hello");
        strings.clear();
        System.out.println(strings);
    }
}
