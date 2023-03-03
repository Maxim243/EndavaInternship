package com.spring_rest.jms.service;

import domain.Privilege;
import domain.User;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class UserServiceImpl implements UserService {

    @Override
    public List<String> getFirstNamesReverseSorted(List<User> users) {
        return users.stream()
                .map(User::getFirstName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<User> sortByAgeDescAndNameAsc(final List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed().thenComparing(User::getFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Privilege> getAllDistinctPrivileges(final List<User> users) {
        return users.stream()
                .flatMap(user -> user.getPrivileges().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUpdateUserWithAgeHigherThan(final List<User> users, final int age) {
        return users.stream()
                .filter(user -> user.getPrivileges().contains(Privilege.UPDATE) && user.getAge() > age)
                .findAny();
    }

    @Override
    public Map<Integer, List<User>> groupByCountOfPrivileges(final List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.getPrivileges().size()));
    }

    @Override
    public double getAverageAgeForUsers(final List<User> users) {
        if (users.isEmpty()) return -1;

        return users.stream()
                .map(User::getAge)
                .reduce(1, (x, y) -> x + y / users.size());
    }

    @Override
    public Optional<String> getMostFrequentLastName(final List<User> users) {
        List<Long> longList = users.stream()
                .collect(Collectors.groupingBy(User::getLastName, counting()))
                .values()
                .stream()
                .sorted()
                .collect(toList());

        long max = longList.get(longList.size() - 1);

        return users.stream()
                .collect(Collectors.groupingBy(User::getLastName, counting()))
                .entrySet()
                .stream()
                .filter(stringLongEntry -> stringLongEntry.getValue() == max)
                .map(Map.Entry::getKey)
                .findFirst();
    }


    @Override
    public List<User> filterBy(final List<User> users, final Predicate<User>... predicates) {
//        users.stream()
//                .filter(user -> predicates)
        return null;
    }

    @Override
    public String convertTo(final List<User> users, final String delimiter, final Function<User, String> mapFun) {
        return null;
    }

    @Override
    public Map<Privilege, List<User>> groupByPrivileges(List<User> users) {
        return users.stream()
                .flatMap(user -> user.getPrivileges().stream())
                .collect(Collectors.toMap(Function.identity(), privilege -> users.stream()
                        .filter(user -> user.getPrivileges().contains(privilege))
                        .collect(toList()), (existing, replacement) -> existing));

    }

    @Override
    public Map<String, Long> getNumberOfLastNames(final List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(User::getLastName, counting()));
    }

    @Override
    public User isUserWithCurrentAgeExist(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge().equals(age))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String getUserFirstNameAndLastName(Optional<User> users) {

        return users.filter(user -> user.getFirstName() != null && user.getLastName() != null)
                .map(user -> user.getFirstName().concat(" " + user.getLastName()))
                .orElse("Unknown");
    }


    @Override
    public long countHowMuchPrivilegesAllUsersHave(List<User> users) {
        return users.stream()
                .flatMap(user -> user.getPrivileges().stream())
                .count();
    }


    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        List<Privilege> privileges = new ArrayList<>();
        List<Privilege> privileges1 = new ArrayList<>();
        privileges.add(Privilege.UPDATE);
        privileges1.add(Privilege.DELETE);
        users.add(new User(1L, "tom", "bbqqqqq", 11, privileges));
        users.add(new User(2L, "kate", "a", 20, privileges));
        users.add(new User(3L, "max", "ccc", 30, privileges1));
        users.add(new User(3L, "maxim", "a", 30, privileges1));
        users.add(new User(4L, "jerry", "kkvv", 50, privileges1));

        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5);
        List<Integer> c = Arrays.asList(6, 7, 8);

        Map<Boolean, Set<User>> listMap = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() > 20,
                        Collectors.toSet()));

        System.out.println(listMap);


//        Map<ArrayList<String>, List<User>> collect = users.stream()
//                .collect(groupingBy(x -> {
//                    return new ArrayList<String>(Arrays.asList(x.getFirstName(), x.getLastName()));
//                }));
//        collect.entrySet().stream()
//                .forEach(x ->
//                        {
//                            System.out.println(x.getKey());
//                            System.out.println("-----");
//                            x.getValue().stream().forEach(
//                                    y -> System.out.println("[" + y.getFirstName() + " " + y.getLastName()));
//                            System.out.println();
//                            System.out.println(" NEXT SET");
//
//});
    }
}


