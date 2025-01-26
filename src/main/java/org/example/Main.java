package org.example;

import org.example.service.OrderService;
import org.example.service.MenuService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();

        Scanner scanner = new Scanner(System.in);

        OrderService orderService = new OrderService(menuService, scanner);

        orderService.takeOrder();

        scanner.close();
    }
}