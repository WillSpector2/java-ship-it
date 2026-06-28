package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(100);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(100);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            showMenu();

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addParcel();
                    break;

                case 2:
                    sendParcels();
                    break;

                case 3:
                    calculateCosts();
                    break;

                case 4:
                    reportStatuses();
                    break;

                case 5:
                    showBoxContent();
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {

        System.out.println("Выберите действие:");
        System.out.println("1 - Добавить посылку");
        System.out.println("2 - Отправить все посылки");
        System.out.println("3 - Посчитать стоимость доставки");
        System.out.println("4 - Обновить статус доставки");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 - Завершить");
    }

    private static void addParcel() {

        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");

        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Описание: ");
        String description = scanner.nextLine();

        System.out.print("Вес: ");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.print("Адрес доставки: ");
        String address = scanner.nextLine();

        System.out.print("День отправки: ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {

            case 1:

                StandardParcel standard =
                        new StandardParcel(description, weight, address, sendDay);

                allParcels.add(standard);
                standardBox.addParcel(standard);

                break;

            case 2:

                FragileParcel fragile =
                        new FragileParcel(description, weight, address, sendDay);

                allParcels.add(fragile);
                fragileBox.addParcel(fragile);
                trackableParcels.add(fragile);

                break;

            case 3:

                System.out.print("Срок хранения: ");

                int ttl = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishable =
                        new PerishableParcel(description, weight, address, sendDay, ttl);

                allParcels.add(perishable);
                perishableBox.addParcel(perishable);

                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sendParcels() {

        if (allParcels.isEmpty()) {
            System.out.println("Посылок нет.");
            return;
        }

        for (Parcel parcel : allParcels) {

            parcel.packageItem();
            parcel.deliver();

        }
    }

    private static void calculateCosts() {

        int total = 0;

        for (Parcel parcel : allParcels) {

            total += parcel.calculateDeliveryCost();

        }

        System.out.println("Общая стоимость доставки: " + total);
    }

    private static void reportStatuses() {

        if (trackableParcels.isEmpty()) {
            System.out.println("Нет отслеживаемых посылок.");
            return;
        }

        System.out.print("Введите новое местоположение: ");

        String location = scanner.nextLine();

        for (Trackable trackable : trackableParcels) {

            trackable.reportStatus(location);

        }
    }

    private static void showBoxContent() {

        System.out.println("Выберите коробку:");
        System.out.println("1 - Стандартные");
        System.out.println("2 - Хрупкие");
        System.out.println("3 - Скоропортящиеся");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {

            case 1:

                for (StandardParcel parcel : standardBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());
                }

                break;

            case 2:

                for (FragileParcel parcel : fragileBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());
                }

                break;

            case 3:

                for (PerishableParcel parcel : perishableBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());
                }

                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }
}