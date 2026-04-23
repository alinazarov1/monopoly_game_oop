import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class SpaceTest {
    @Test
    void propertyParsesPriceAndRentFromDescription() {
        Space space = new Space("BALTIC AVENUE", "Property", "Price: $60, Rent: $20");

        assertEquals(60, space.getPrice());
        assertEquals(20, space.getRent());
        assertNull(space.getOwner());
    }

    @Test
    void nonPropertySpaceKeepsZeroPriceAndRent() {
        Space space = new Space("GO", "Corner", "Collect $200 as you pass GO.");

        assertEquals(0, space.getPrice());
        assertEquals(0, space.getRent());
    }
}
