package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;

public class ParcelBoxTest {

    @Test
    void addParcel() {

        ParcelBox<StandardParcel> box = new ParcelBox<>(20);

        box.addParcel(new StandardParcel("Лампа", 10, "Москва", 1));

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addTooHeavyParcel() {

        ParcelBox<StandardParcel> box = new ParcelBox<>(15);

        box.addParcel(new StandardParcel("Компьютер", 10, "Москва", 1));
        box.addParcel(new StandardParcel("Телефон", 10, "Москва", 1));

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void borderCase() {

        ParcelBox<StandardParcel> box = new ParcelBox<>(20);

        box.addParcel(new StandardParcel("Книга", 10, "СПБ", 1));
        box.addParcel(new StandardParcel("Телефон", 10, "Москва", 1));

        assertEquals(2, box.getAllParcels().size());
    }
}