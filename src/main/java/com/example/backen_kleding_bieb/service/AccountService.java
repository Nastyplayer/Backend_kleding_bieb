package com.example.backen_kleding_bieb.service;

import com.example.backen_kleding_bieb.dto.AccountDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Account;
import com.example.backen_kleding_bieb.models.Subscription;
import com.example.backen_kleding_bieb.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class AccountService {


    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            AccountDto accountDto = transferAccountToAccountDto(account);
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }


    public AccountDto getAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account username = optionalAccount.get();
            return transferAccountToAccountDto(username);
        } else {
            throw new RecordNotFoundException("No account found with id: " + id + ".");
        }
    }


    public Long createAccount(AccountDto accountDto) {
        Account newAccount;
        newAccount = transferAccountDtoToAccount(accountDto);
        Account savedAccount = accountRepository.save(newAccount);

        return savedAccount.getId();
    }


    ////////////////////////////////////////////////////


    public AccountDto putAccount(Long id, AccountDto accountDto) {
        if (accountRepository.findById(id).isPresent()) {
            Account accountToChange = accountRepository.findById(id).get();
            Account account1 = transferAccountDtoToAccount(accountDto);
            account1.setId(accountToChange.getId());

            accountRepository.save(account1);
            return transferAccountToAccountDto(account1);
        } else {
            throw new RecordNotFoundException("No Account found with id: " + id + ".");
        }
    }


    //////////////////////////////////////////////////////


    public AccountDto patchAccount(Long id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (accountRepository.existsById(id)) {
            Account accountToUpdate = optionalAccount.get();

            Account account1 = transferAccountDtoToAccount(accountDto);
            account1.setId(accountToUpdate.getId());

            if (accountDto.getId() != null) {
                accountToUpdate.setId(accountDto.getId());
            }

            if (accountDto.getSubscriptionInfo() != null) {
                accountToUpdate.setSubscriptionInfo(accountDto.getSubscriptionInfo());
            }


            Account savedAccount = accountRepository.save(accountToUpdate);
            return transferAccountToAccountDto(savedAccount);
        } else {
            throw new RecordNotFoundException("No Account found with id " + id);

        }
    }


    ///////////////////////////////////////////////////////////

    public String deleteById(Long id) {
        if (accountRepository.existsById(id)) {
            Optional<Account> deletedAccount = accountRepository.findById(id);
            Account account1 = deletedAccount.get();

            accountRepository.delete(account1);
            return "Account with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Account found with id: " + id + ".");
        }
    }


    private AccountDto transferAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserInfo(account.getUserInfo());
        accountDto.setSubscriptionInfo(account.getSubscriptionInfo());
        accountDto.setEmail(account.getEmail());
        accountDto.setUser(account.getUser());
        accountDto.setComment(account.getComment());

        if (account.getUserInfo() != null) {
            accountDto.setUserInfo(account.getUserInfo());
        }
        if (account.getEmail() != null) {
            accountDto.setEmail(account.getEmail());
        }
        if (account.getComment() != null) {
            accountDto.setComment(account.getComment());
        }

        if (account.getSubscriptionInfo() != null) {
            accountDto.setSubscriptionInfo(account.getSubscriptionInfo());
        }

        return accountDto;
    }
/////////////////////////

    public Account transferAccountDtoToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUserInfo(accountDto.getUserInfo());
        account.setSubscriptionInfo(accountDto.getSubscriptionInfo());
        account.setEmail(accountDto.getEmail());
        account.setComment(accountDto.getComment());
        account.setSubscription((Subscription) accountDto.getSubscription());
//        account.setUpload((Upload) accountDto.getUpload());
        account.setUser(accountDto.getUser());
        return account;
    }

}
