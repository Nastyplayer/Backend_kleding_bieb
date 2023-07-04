package com.example.backen_kleding_bieb.controller;

import KledingBib.demo.dto.AccountDto;
import KledingBib.demo.repository.AccountRepository;
import KledingBib.demo.repository.SubscriptionRepository;
import KledingBib.demo.service.AccountService;
import KledingBib.demo.service.UploadService;
import KledingBib.demo.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.action.internal.EntityAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "")
public class AccountController {
    private final AccountService accountService;



    private final UploadService UploadService;

    private final UserService UserService;
    private EntityAction account;
    private final SubscriptionRepository subscriptionRepository;
    private final AccountRepository accountRepository;

    public AccountController(AccountService accountService, UploadService uploadService , UserService userService,
                             SubscriptionRepository subscriptionRepository,
                             AccountRepository accountRepository) {
        this.accountService = accountService;
        this.UploadService = uploadService;
        this.UserService = userService;
        this.subscriptionRepository = subscriptionRepository;
        this.accountRepository = accountRepository;
    }


    @GetMapping("/accounts")

    // transactional to link a photo to a account
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/accounts/{id}")

    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            //  return ResponseEntity.badRequest().body(errorString);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);//
        } else {
            Long createdId = accountService.createAccount(accountDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    // URI uri = ServletUriComponentsBuilder   //
                    .fromCurrentRequest()
                    .path("/accounts/" + createdId)
                    //.path("/{id}")
                    // .buildAndExpand(createdId)
                    // .toUri();
                    .toUriString());


            return ResponseEntity.created(uri).body(createdId);  //build(); //
        }
    }
    ///////////////////////////
 private String getErrorString(BindingResult br) {
      return null;
   }
    //////////////////////
    @PutMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.putAccount(id, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }

    @PatchMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> updatePartOfAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.patchAccount(id, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //  link photo to a account
//    @PostMapping("/accounts/{id}/photo")
//    public ResponseEntity<Object> assignPhotoToAccount(@PathVariable("id") Long id, @RequestBody MultipartFile file) {
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
//                .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
//        String photo = UploadService.storeFile(file);
//        accountService.assignPhotoToAccount(photo, id);
//
//        return ResponseEntity.noContent().build();
//    }


}