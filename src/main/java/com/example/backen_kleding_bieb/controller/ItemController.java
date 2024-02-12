package com.example.backen_kleding_bieb.controller;


import com.example.backen_kleding_bieb.dto.ItemDto;
import com.example.backen_kleding_bieb.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "")
public class ItemController {


//    private final AccountService accountService;
//    private final OrderService orderService;
    private final UserService userService;

    private final UploadService UploadService;
    private ItemService itemService;
//    private UploadController uploadController;

    @Autowired
    public ItemController(ItemService itemService,
//                          AccountService accountService, AccountController accountController,
//                          OrderController orderController,
//                          OrderService orderService,
                          UploadController uploadController, UploadService uploadService,
                          UserService userService, ItemService itemService1) {
//        this.accountService = accountService;
//        this.orderService = orderService;
        this.UploadService = uploadService;
        this.userService = userService;
        this.itemService = itemService;
    }



    @GetMapping("/items")
    @Transactional

    public ResponseEntity<List<ItemDto>> getAllItems() {

   return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    @Transactional
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id) {

        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = itemService.createItem(itemDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/Items/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body(createdId);
        }
    }
    ///////////////////////////
    private String getErrorString(BindingResult br) {
        return null;
    }
    //////////////////////
    @PutMapping("/items/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @Valid @RequestBody ItemDto itemDto) {
        ItemDto itemDto1 = itemService.putItem(id, itemDto);
        return ResponseEntity.ok().body(itemDto1);
    }

    @PatchMapping("/items/{id}")
    public ResponseEntity<ItemDto> updatePartOfItem(@PathVariable Long id, @Valid @RequestBody ItemDto itemDto) {
        ItemDto itemDto1 = itemService.patchItem(id, itemDto);
        return ResponseEntity.ok().body(itemDto1);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //  link photo to a item
    @PostMapping("/items/photo")
    public ResponseEntity<Object> assignPhotoToItem(@Valid  @RequestBody MultipartFile file) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
        String photo = UploadService.storeFile(file, url);
        itemService.assignPhotoToItem(photo);

        return ResponseEntity.noContent().build();
    }
}
