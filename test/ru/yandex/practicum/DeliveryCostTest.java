package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;


public class DeliveryCostTest {

    @Test
    void standardParcelCost() {

        StandardParcel parcel =
                new StandardParcel("Книга", 2, "Россия", 5);

        assertEquals(4, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileParcelCost() {

        FragileParcel parcel =
                new FragileParcel("Окно", 20, "СПБ", 2);

        assertEquals(80, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableParcelCost() {

        PerishableParcel parcel =
                new PerishableParcel("Торт", 4, "Москва", 1, 5);

        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void notExpired() {

        PerishableParcel parcel =
                new PerishableParcel("Вода", 2, "СПБ", 5, 3);

        assertFalse(parcel.isExpired(8));
    }

    @Test
    void expired() {

        PerishableParcel parcel =
                new PerishableParcel("Молоко", 2, "Москва", 5, 3);

        assertTrue(parcel.isExpired(9));
    }

    @Test
    void borderCase() {

        PerishableParcel parcel =
                new PerishableParcel("Йогурт", 2, "Москва", 5, 3);

        assertFalse(parcel.isExpired(8));
    }
}