import org.example.model.Cuisines;
import org.example.model.Drink;
import org.example.model.Lunch;
import org.example.service.MenuService;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private MenuService menuService;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Lunch> createLunches() {
        List<Lunch> lunches = new ArrayList<>();
        lunches.add(new Lunch("Dish1", "Dessert1", 25.0));
        lunches.add(new Lunch("Dish2", "Dessert2", 35.0));
        return lunches;
    }

    private List<Drink> createDrinks() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Drink1", 5.0));
        drinks.add(new Drink("Drink2", 8.0));
        return drinks;
    }

    @Test
    public void test_take_order_lunch_and_drink() {
        List<Lunch> lunches = createLunches();
        List<Drink> drinks = createDrinks();

        when(menuService.getLunches(any(Cuisines.class))).thenReturn(lunches);
        when(menuService.getDrinks()).thenReturn(drinks);

        when(scanner.nextLine()).thenReturn("lunch", "1", "1", "no", "yes", "1", "yes", "no", "yes");

        orderService.takeOrder();


        verify(menuService, times(1)).getLunches(any(Cuisines.class));
        verify(menuService, times(1)).getDrinks();
    }

    @Test
    public void test_take_order_only_lunch() {
        List<Lunch> lunches = createLunches();

        when(menuService.getLunches(any(Cuisines.class))).thenReturn(lunches);
        when(menuService.getDrinks()).thenReturn(new ArrayList<>());

        when(scanner.nextLine()).thenReturn("lunch", "2", "2", "no", "no");

        orderService.takeOrder();

        verify(menuService, times(1)).getLunches(any(Cuisines.class));
        verify(menuService, never()).getDrinks();
    }

    @Test
    public void test_take_order_only_drink() {
        List<Drink> drinks = createDrinks();

        when(menuService.getLunches(any(Cuisines.class))).thenReturn(new ArrayList<>());
        when(menuService.getDrinks()).thenReturn(drinks);

        when(scanner.nextLine()).thenReturn("drink", "yes", "1", "yes");

        orderService.takeOrder();

        verify(menuService, times(1)).getDrinks();
        verify(menuService, never()).getLunches(any(Cuisines.class));
    }

    @Test
    public void test_take_order_drink_and_extras() {
        List<Drink> drinks = createDrinks();

        when(menuService.getDrinks()).thenReturn(drinks);

        when(scanner.nextLine()).thenReturn("drink", "1", "yes", "yes", "yes");

        orderService.takeOrder();

        verify(menuService, times(1)).getDrinks();
    }

    @Test
    public void test_take_order_double_lunch_without_drinks() {
        List<Lunch> lunches = createLunches();

        when(menuService.getLunches(any(Cuisines.class))).thenReturn(lunches);
        when(menuService.getDrinks()).thenReturn(new ArrayList<>());

        when(scanner.nextLine()).thenReturn("lunch", "2", "2", "yes", "1", "1", "no", "no");

        orderService.takeOrder();

        verify(menuService, times(2)).getLunches(any(Cuisines.class));
        verify(menuService, never()).getDrinks();

    }

    @Test
    public void test_take_order_double_drink_without_lunches() {
        List<Drink> drinks = createDrinks();

        when(menuService.getDrinks()).thenReturn(drinks);

        when(scanner.nextLine()).thenReturn("drink", "1", "yes", "yes", "no", "2", "no", "no", "yes");

        orderService.takeOrder();

        verify(menuService, times(2)).getDrinks();
    }
}


