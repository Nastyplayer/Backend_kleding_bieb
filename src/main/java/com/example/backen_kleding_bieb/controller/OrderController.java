package com.example.backen_kleding_bieb.controller;

import com.example.backen_kleding_bieb.dto.OrderDto;
import com.example.backen_kleding_bieb.repository.OrderRepository;
import com.example.backen_kleding_bieb.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("")
public class OrderController {



    private final AccountService accountService;
    private final UploadService uploadService;
    private final UserService userService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderService orderService, AccountService accountService, UploadService uploadService,
                           UserService userService, ItemService itemService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.accountService = accountService;
        this.uploadService = uploadService;
        this.userService = userService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")


    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }



    @GetMapping("/orders/{id}")

    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);

            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = orderService.createOrder(orderDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/orders/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body(createdId);

        }

    }
    private String getErrorString (BindingResult br){
        return null;
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        OrderDto orderDto1 = orderService.putOrder(id, orderDto);
        return ResponseEntity.ok().body(orderDto1);
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<OrderDto> updatePartOfOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        OrderDto orderDto1 = orderService.patchOrder(id, orderDto);
        return ResponseEntity.ok().body(orderDto1);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}