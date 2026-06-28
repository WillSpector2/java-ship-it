package ru.yandex.practicum.delivery;

public abstract class Parcel {

    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;

    public Parcel(String description, int weight,
                  String deliveryAddress, int sendDay) {

        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка '" + description + "' упакована");
    }

    public void deliver() {
        System.out.println("Посылка '" + description +
                "' доставлена по адресу " + deliveryAddress);
    }

    public int calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    protected abstract int getBaseCost();

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }
}