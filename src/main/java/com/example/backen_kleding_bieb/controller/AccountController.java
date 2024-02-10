package com.example.backen_kleding_bieb.controller;

import com.example.backen_kleding_bieb.dto.AccountDto;
import com.example.backen_kleding_bieb.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/accounts")

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
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);//
        } else {
            Long createdId = accountService.createAccount(accountDto);
            URI uri = URI.create(ServletUriComponentsBuilder

                    .fromCurrentRequest()
                    .path("/accounts/" + createdId)

                    .toUriString());


            return ResponseEntity.created(uri).body(createdId);
        }
    }

 private String getErrorString(BindingResult br) {
      return null;
   }

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
    }}

