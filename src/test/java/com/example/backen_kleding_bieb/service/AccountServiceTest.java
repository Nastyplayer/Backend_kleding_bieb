package com.example.backen_kleding_bieb.service;

import com.example.backen_kleding_bieb.dto.AccountDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Account;
import com.example.backen_kleding_bieb.models.User;
import com.example.backen_kleding_bieb.repository.AccountRepository;
import com.example.backen_kleding_bieb.service.AccountService;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Captor
    ArgumentCaptor<Account> captor;

    Account account1;
    Account account2;

    AccountDto accountDto1;

    AccountDto accountDto2;
    private User user1;
    private User user2;


    @BeforeEach
    void setUp() {
        account1 = new Account(1L, "IggyPop", "annual", "iggy@pop.nl", "Hola", null , null,  null);
        account2 = new Account(2L, "BillyIdol", "annual", "billy@idol.nl", "Playa", null, null, null );

        accountDto1 = new AccountDto( 1L, "IggyPop", "annual", "iggy@pop.nl", "Hola", null, null, null );
        accountDto2 = new AccountDto( 2L, "BillyIdol", "annual", "billy@idol.nl", "Playa", null, null, null );
    }


    @Test
    void getAllAccounts() {
       when(accountRepository.findAll()).thenReturn(List.of(account1, account2));
       // when(accountRepository.save(any())).thenReturn(account1);  // Voeg deze regel toe

        List<AccountDto> accountsFound = (accountService.getAllAccounts());

        assertEquals(account1.getId(), accountsFound.get(0).getId());
        assertEquals(account1.getUserInfo(), accountsFound.get(0).getUserInfo());
        assertEquals(account1.getSubscriptionInfo(), accountsFound.get(0).getSubscriptionInfo());
        assertEquals(account1.getEmail(), accountsFound.get(0).getEmail());
        assertEquals(account1.getComment(), accountsFound.get(0).getComment());
        assertEquals(account1.getSubscription(), accountsFound.get(0).getSubscription());
        assertEquals(account1.getUpload(), accountsFound.get(0).getUpload());
        assertEquals(account1.getUser(), accountsFound.get(0).getUser());

        assertEquals(account2.getId(), accountsFound.get(1).getId());
        assertEquals(account2.getUserInfo(), accountsFound.get(1).getUserInfo());
        assertEquals(account2.getSubscriptionInfo(), accountsFound.get(1).getSubscriptionInfo());
        assertEquals(account2.getEmail(), accountsFound.get(1).getEmail());
        assertEquals(account2.getComment(), accountsFound.get(1).getComment());
        assertEquals(account2.getSubscription(), accountsFound.get(1).getSubscription());
        assertEquals(account2.getUpload(), accountsFound.get(1).getUpload());
        assertEquals(account2.getUser(), accountsFound.get(1).getUser());

    }


    @Test
    void getAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        AccountDto accountDto = accountService.getAccount(1L);

        assertEquals(account1.getId(), accountDto.getId());
    }


    @Test
    void getAccountThrowsExceptionForAccountTest() {
        assertThrows(RecordNotFoundException.class, () -> accountService.getAccount(null));
    }


    @Test
    void putAccountThrowsExceptionForAccountTest() {
        assertThrows(RecordNotFoundException.class, () -> accountService.putAccount(1L, new AccountDto(1L, "IggyPop", "annual", "iggy@pop.nl", "Hola", null, null, null)));
    }
    @Test
    void createAccount() {

        account1.setId(1L);
        when(accountRepository.save(any(Account.class))).thenReturn(account2);

        accountService.createAccount(accountDto1);
        verify(accountRepository, times(1)).save(captor.capture());
        Account account = captor.getValue();

        assertEquals(account1.getId(), account.getId());
        assertEquals(account1.getUserInfo(), account.getUserInfo());
        assertEquals(account1.getSubscriptionInfo(), account.getSubscriptionInfo());
        assertEquals(account1.getEmail(), account.getEmail());
        assertEquals(account1.getComment(), account.getComment());
        assertEquals(account1.getSubscription(), account.getSubscription());
        assertEquals(account1.getUpload(), account.getUpload());
        assertEquals(account1.getUser(), account.getUser());

    }

    @Test
    void putOrder() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.save(any())).thenReturn(account2);

        accountService.putAccount(1L, accountDto1);

        verify(accountRepository, times(1)).save(captor.capture());
        Account captured = captor.getValue();

        assertEquals(account1.getId(), captured.getId());
        assertEquals(account1.getUserInfo(), captured.getUserInfo());
        assertEquals(account1.getSubscriptionInfo(), captured.getSubscriptionInfo());
        assertEquals(account1.getEmail(), captured.getEmail());
        assertEquals(account1.getComment(), captured.getComment());
        assertEquals(account1.getSubscription(), captured.getSubscription());
        assertEquals(account1.getUpload(), captured.getUpload());
        assertEquals(account1.getUser(), captured.getUser());

    }


    @Test
    void patchAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.save(any())).thenReturn(account2);

        accountService.patchAccount(1L, accountDto1);

       verify(accountRepository, times(1)).save(captor.capture());

        Account captured = captor.getValue();

        assertEquals(account1.getId(), captured.getId());
        assertEquals(account1.getUserInfo(), captured.getUserInfo());
        assertEquals(account1.getSubscriptionInfo(), captured.getSubscriptionInfo());
        assertEquals(account1.getEmail(), captured.getEmail());
        assertEquals(account1.getComment(), captured.getComment());
        assertEquals(account1.getSubscription(), captured.getSubscription());
        assertEquals(account1.getUpload(), captured.getUpload());
        assertEquals(account1.getUser(), captured.getUser());

    }

    @Test
    void patchAccountThrowsExceptionForAccountTest() {
        assertThrows(RecordNotFoundException.class, () -> accountService.patchAccount(1L, new AccountDto(1L, "IggyPop", "annual", "iggy@pop.nl", "Hola", null, null, null)));
    }

    @Test
    void deleteById() {

        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.findById(1L)).thenReturn(Optional.of( account1));
        accountService.deleteById(1L);

        verify(accountRepository).delete(account1);
    }


    @Test
    void deleteAccountThrowsExceptionForAccountTest() {
        assertThrows(RecordNotFoundException.class, () -> accountService.deleteById(1L));
    }



}
