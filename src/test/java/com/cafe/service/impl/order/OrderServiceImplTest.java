package com.cafe.service.impl.order;

import com.cafe.entity.order.Order;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.table.CafeTable;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.repository.OrderRepository;
import com.cafe.service.core.order.OrderCreationParams;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.table.CafeTableService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private OrderService testSubject;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CafeTableService cafeTableService;

    @BeforeEach
    public void init() {
        testSubject = new OrderServiceImpl(orderRepository, cafeTableService);
    }

    @Test
    public void testFindByIdWhenOrderIsNotFound() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThat(testSubject.findById(1L)).isEqualTo(Optional.empty());
        Mockito.verify(orderRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(orderRepository, cafeTableService);
    }

    @Test
    public void testFindByIdWhenOrderIsFound() {
        Order order = new Order(
                new CafeTable(CafeTableStatusType.FREE, 5, "qwerty"),
                OrderStatusType.OPEN,
                LocalDateTime.MAX
        );
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Assertions.assertThat(testSubject.findById(1L)).isEqualTo(Optional.of(order));
        Mockito.verify(orderRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(orderRepository, cafeTableService);
    }

    @Test
    public void testGetByIdWhenOrderIsNotFound() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> testSubject.getById(1L)).isExactlyInstanceOf(OrderNotFoundException.class);
        Mockito.verify(orderRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(orderRepository, cafeTableService);
    }

    @Test
    public void testGetByIdWhenOrderIsFound() {
        Order order = new Order(
                new CafeTable(CafeTableStatusType.FREE, 5, "qwerty"),
                OrderStatusType.OPEN,
                LocalDateTime.MAX
        );
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Assertions.assertThat(testSubject.getById(1L)).isEqualTo(order);
        Mockito.verify(orderRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(orderRepository, cafeTableService);
    }

    @Test
    public void create() {
        CafeTable cafeTable = new CafeTable(CafeTableStatusType.FREE, 5, "qwerty");
        cafeTable.setId(1L);

        Order order = new Order(cafeTable, OrderStatusType.OPEN, LocalDateTime.MAX);

        Order savedOrder = new Order(cafeTable, OrderStatusType.OPEN, LocalDateTime.MAX);
        savedOrder.setId(1L);

        Mockito.when(cafeTableService.getById(1L)).thenReturn(cafeTable);
        Mockito.when(orderRepository.save(order)).thenReturn(savedOrder);

        Assertions.assertThat(testSubject.create(new OrderCreationParams(
                1L, OrderStatusType.OPEN, LocalDateTime.MAX
        ))).isEqualTo(savedOrder);

        Mockito.verify(cafeTableService).getById(1L);
        Mockito.verify(orderRepository).save(order);
        Mockito.verifyNoMoreInteractions(cafeTableService, orderRepository);
    }
}