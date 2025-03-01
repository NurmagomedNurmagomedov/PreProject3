import model.Book;
import model.BookGenre;
import model.CityStats;
import model.Person;

import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<Person> people = new ArrayList<>();
        people.add(new Person("Алекс", 25, "Москва"));
        people.add(new Person("Борис", 30, "Петербург"));
        people.add(new Person("Вера", 28, "Москва"));
        people.add(new Person("Дима", 25, "Казань"));
        people.add(new Person("Елена", 35, "Петербург"));

        Map<String, CityStats> result = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new CityStats(
                                        list.size(),
                                        list.stream().mapToInt(Person::getAge).average().orElse(0.0),
                                        list.stream()
                                                .filter(p -> p.getAge() > 27)
                                                .map(Person::getName)
                                                .sorted()
                                                .collect(Collectors.toList())
                                )
                        )
                ));
        Map<String, List<Person>> listMap = people.stream()
                .collect(Collectors.groupingBy(
                                Person::getCity,
                                Collectors.toList()
                        )
                );

        System.out.println(result);
        System.out.println(listMap);

        List<Book> books = new ArrayList<>();

        books.add(new Book("Война и мир", "Классика", 8));
        books.add(new Book("1984", "Фантастика", 9));
        books.add(new Book("Анна Каренина", "Классика", 7));
        books.add(new Book("Дюна", "Фантастика", 8));
        books.add(new Book("Мастер и Маргарита", "Магический реализм", 9));


        Map<String, BookGenre> stringListMap = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new BookGenre(
                                        list.size(),
                                        list.stream()
                                                .collect(
                                                        Collectors.averagingDouble(
                                                                Book::getRating)),
                                        list.stream()
                                                .filter(x -> x.getRating() > 7)
                                                .sorted((a, b)-> a.getTitle().compareTo(b.getTitle()))
                                                .map(Book::getTitle)
                                                .collect(Collectors.toList())
                                )
                        )));
        Collector<Character,ArrayList<Integer>, List<Integer>> toMyList = Collector.of(
                () -> new ArrayList<Integer>(),
                (list, item) -> list.add((int)item),
                (list1, list2) -> { list1.addAll(list2); return list1;},
                list -> list
        );

        System.out.println(Stream.of('a', 'b', 'c').collect(toMyList));
        System.out.println(stringListMap);
        BinaryOperator


//        List<Integer> list = IntStream.range(1, 7).boxed().collect(Collectors.toList());
//        for (Integer i : list) {
//            System.out.print(i + " ");
//        }
//
//        Iterator<Integer> iterator = list.iterator();
//
//        while (iterator.hasNext()) {
//            if (iterator.next() % 2 == 0) {
//                iterator.remove();
//            }
//        }
//        System.out.println();
//
//        list.stream().map(s -> s * 2).forEach(s -> System.out.print(s + " "));
//
//        Integer sum;
//        list.stream().map(x -> x * 2).reduce(0, (a,b) -> a + b);


//        ExecutorService executor = Executors.newFixedThreadPool(2);
//
//        Runnable stream1 = () -> {
//            for (int i = 0; i < 10; i++) {
//                if (lock.tryLock()) {
//                    try {
//                        balance += 100;
//                        System.out.println("поток 1: " + balance);
//                    } finally {
//                        lock.unlock();
//                    }
//                } else i--;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//
//        Runnable stream2 = () -> {
//            for (int i = 0; i < 10; i++) {
//                if (lock.tryLock()) {
//                    try {
//                        balance -= 50;
//                        System.out.println("поток 2: " + balance);
//                    } finally {
//                        lock.unlock();
//                    }
//                } else i--;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//
//        executor.execute(stream1);
//        executor.execute(stream2);
//        executor.shutdown();
//
//        while (!executor.isTerminated()) {
//            Thread.sleep(100);
//        }
//        System.out.println("Финальный баланс: " + balance);
    }

//    static class Operation {
//        public static <T extends Number> Optional<T> Calculate(T num1, T num2, BinaryOperator<T> op) {
//            Optional<T> result = Optional.of(op.apply(num1, num2));
//            return result.isPresent() ? result : Optional.empty();
//        }
//    }


}