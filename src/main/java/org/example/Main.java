package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Smartphone", 750.0),
                new Order("Car", 18000.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 400.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        //Группировка заказов по продуктам
        Map<String, Double> totalCostByProduct = orders.stream().collect(Collectors.groupingBy(Order::getProduct,
                Collectors.summingDouble(Order::getCost)));
        System.out.println(totalCostByProduct);

        // Сортировка продуктов по убыванию общей стоимости
        List<Map.Entry<String, Double>> sortedProductsByTotalCost = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
        System.out.println(sortedProductsByTotalCost);

        //3 самых дорогих продукта
        List<Order> top3ExpensiveProducts = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(top3ExpensiveProducts);

        // Общая стоимость 3 самых дорогих продуктов
        int sum = top3ExpensiveProducts.stream().mapToInt(product -> (int) product.getCost()).sum();
        System.out.println(sum);
    }
}