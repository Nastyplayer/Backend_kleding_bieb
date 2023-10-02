package com.example.backen_kleding_bieb.service;

import com.example.backen_kleding_bieb.dto.OrderDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Order;
import com.example.backen_kleding_bieb.repository.OrderRepository;
import com.example.backen_kleding_bieb.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)





public class OrderServiceTest {


    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Captor
    ArgumentCaptor<Order> captor;

    Order order1;
    Order order2;
    OrderDto orderDto1;
    OrderDto orderDto2;


    @BeforeEach
    void setUp() {
        order1 = new Order(1L, "broek",  LocalDate.now(), null, null );
        order2 = new Order(2L, "shirt",  LocalDate.now(), null, null );

        orderDto1 = new OrderDto(1L, "broek",  LocalDate.now() , null, null);
        orderDto2 = new OrderDto(2L, "shirt",  LocalDate.now(), null, null);
    }


    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        List<OrderDto> ordersFound = (orderService.getAllOrders());

        assertEquals(order1.getId(), ordersFound.get(0).getId());
        assertEquals(order1.getItemInfo(), ordersFound.get(0).getItemInfo());
        assertEquals(order1.getDateInfo(), ordersFound.get(0).getDateInfo());
        assertEquals(order1.getItems(), ordersFound.get(0).getItems());
        assertEquals(order1.getUsers(), ordersFound.get(0).getUsers());

        assertEquals(order2.getId(), ordersFound.get(1).getId());
        assertEquals(order2.getItemInfo(), ordersFound.get(1).getItemInfo());
        assertEquals(order2.getDateInfo(), ordersFound.get(1).getDateInfo());
        assertEquals(order2.getItems(), ordersFound.get(1).getItems());
        assertEquals(order2.getUsers(), ordersFound.get(1).getUsers());

    }


    @Test
    void getOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        OrderDto orderDto = orderService.getOrder(1L);

        assertEquals(order1.getId(), orderDto.getId());
    }


    @Test
    void getOrderThrowsExceptionForOrderTest() {
        assertThrows(RecordNotFoundException.class, () -> orderService.getOrder(null));
    }
    @Test
    void createOrder() {

        order1.setId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(order2);

        orderService.createOrder(orderDto1);
        verify(orderRepository, times(1)).save(captor.capture());
        Order order = captor.getValue();

        assertEquals(order1.getId(), order.getId());
        assertEquals(order1.getItemInfo(), order.getItemInfo());
        assertEquals(order1.getDateInfo(), order.getDateInfo());
        assertEquals(order1.getItems(), order.getItems());
        assertEquals(order1.getUsers(), order.getUsers());

    }





    @Test
    void putOrderThrowsExceptionForOrderTest() {
        assertThrows(RecordNotFoundException.class, () -> orderService.putOrder(1L, new OrderDto(100L, "broek", LocalDate.now(), null, null )));
    }
    @Test
    void putOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(any())).thenReturn(order2);

        orderService.putOrder(1L, orderDto1);

        verify(orderRepository, times(1)).save(captor.capture());
        Order captured = captor.getValue();

        assertEquals(order1.getId(), captured.getId());
        assertEquals(order1.getItemInfo(), captured.getItemInfo());
        assertEquals(order1.getDateInfo(), captured.getDateInfo());
        assertEquals(order1.getItems(), captured.getItems());
        assertEquals(order1.getUsers(), captured.getUsers());

    }

    @Test
    void patchOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(any())).thenReturn(order2);

        orderService.patchOrder(1L, orderDto1);

        verify(orderRepository, times(1)).save(captor.capture());
        Order captured = captor.getValue();

        assertEquals(order1.getId(), captured.getId());
        assertEquals(order1.getItemInfo(), captured.getItemInfo());
        assertEquals(order1.getDateInfo(), captured.getDateInfo());
        assertEquals(order1.getItems(), captured.getItems());
        assertEquals(order1.getUsers(), captured.getUsers());

    }




    @Test
    void deleteById() {

        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.findById(1L)).thenReturn(Optional.of( order1));
        orderService.deleteById(1L);

        verify(orderRepository).delete(order1);
    }




    @Test
    void patchOrderThrowsExceptionForOrderTest() {
        assertThrows(RecordNotFoundException.class, () -> orderService.patchOrder(1L, new OrderDto(100L, "broek", LocalDate.now(), null, null)));
    }


    @Test
    void deleteOrderThrowsExceptionForOrderTest() {
        assertThrows(RecordNotFoundException.class, () -> orderService.deleteById(1L));
    }

//    @Test
//    void transferOrderToOrderDto() {
//        OrderDto orderDto1 = orderService.transferOrderToOrderDto(order1);
//        assertEquals(order1.getItems(), orderDto1.getItems());
//    }




}
