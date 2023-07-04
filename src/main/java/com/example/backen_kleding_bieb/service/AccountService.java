package com.example.backen_kleding_bieb.service;


import backen_kleding_bieb.dto.AccountDto;
import backen_kleding_bieb.exceptions.RecordNotFoundException;
import backen_kleding_bieb.models.*;
import backen_kleding_bieb.repository.*;
import com.example.backen_kleding_bieb.dto.AccountDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Account;
import com.example.backen_kleding_bieb.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class AccountService {


    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UploadRepository uploadRepository;
    private final EmailService emailService;


    public AccountService(AccountRepository accountRepository, UserRepository userRepository, ItemRepository itemRepository,
                          OrderRepository orderRepository, SubscriptionRepository subscriptionRepository,
                          UploadRepository uploadRepository, EmailService emailService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.uploadRepository = uploadRepository;
        this.emailService = emailService;

    }


    // GetMapping, method for getting all Accounts
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            AccountDto accountDto = transferAccountToAccountDto(account);
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }

    // GetMapping by id, method for getting a Account by id
    public AccountDto getAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account username = optionalAccount.get();
            return transferAccountToAccountDto(username);
        } else {
            throw new RecordNotFoundException("No account found with id: " + id + ".");
        }
    }

    // , method for adding a Account
    public Long createAccount(AccountDto accountDto) {
        Account newAccount;
        newAccount = transferAccountDtoToAccount(accountDto);
        Account savedAccount = accountRepository.save(newAccount);

     //   addOrderToAccount(accountDto, savedAccount);
     //   addSubscriptionToAccount(accountDto, savedAccount);
      //  addUploadToAccount(accountDto, savedAccount);
      //  addUserToAccount(accountDto, savedAccount);

        return savedAccount.getId();
    }


    ////////////////////////////////////////////////////

    // PutMapping, method for changing a (whole)account
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


    // Patchmapping, method for changing parts of a Account

    public AccountDto patchAccount(Long id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (accountRepository.existsById(id)) {
            Account accountToUpdate = optionalAccount.get();

            Account account1 = transferAccountDtoToAccount(accountDto);
            account1.setId(accountToUpdate.getId());

            if (accountDto.getId() != null) {
                accountToUpdate.setId(accountDto.getId());
            }
            if (accountDto.getUserInfo() != null) {
                accountToUpdate.setUserInfo(accountDto.getUserInfo());
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
    // DeleteMapping, method for deleting a acount
    public String deleteById(Long id) {
        if (accountRepository.existsById(id)) {
            Optional<Account> deletedAccount = accountRepository.findById(id);
            Account account1 = deletedAccount.get();

            // deleting upload, subscriptions  first
        //    for (Upload upload : account1.getUploads()) {
               Upload upload = account1.getUpload();
                uploadRepository.delete(upload);
       //     }
            Subscription subscription = account1.getSubscription();

           // for (Subscription subscription : account1.getSubscriptions()) {
                subscriptionRepository.delete(subscription);
       //     }

            accountRepository.delete(account1);
            return "Account with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Account found with id: " + id + ".");
        }
    }

    //////////////////////

    // Assign photo to a account
     public void assignPhotoToAccount(String fileName, Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
         Optional<Upload> fileUploadResponse = uploadRepository.findByFileName(fileName);
         if (optionalAccount.isPresent() && fileUploadResponse.isPresent()) {
          Upload photo = fileUploadResponse.get();
              Account account = optionalAccount.get();
           account.setUpload(photo);
             accountRepository.save(account);
       }
    }


    //  methods
    //
    //  method from Account to Dto
    private AccountDto transferAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserInfo(account.getUserInfo());
        accountDto.setSubscriptionInfo(account.getSubscriptionInfo());


        if (account.getUserInfo() != null) {
            accountDto.setUserInfo(account.getUserInfo());
        }
        if (account.getUpload() != null) {
            accountDto.setUpload(account.getUpload());
        }
        if (account.getSubscription() != null) {
            accountDto.setSubscription(account.getSubscription());
        }

        return accountDto;
    }
/////////////////////////


    /////////////////////////////////////////////

    //method from Dto to Acount
    public Account transferAccountDtoToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUserInfo(accountDto.getUserInfo());
        account.setSubscriptionInfo(accountDto.getSubscriptionInfo());

        account.setSubscription((Subscription) accountDto.getSubscription());
        account.setUpload((Upload) accountDto.getUpload());

        return account;
    }


/////////////////////////

    public List<Account> transferAccountDtoListToAccountList (List<AccountDto> usersdtos) {
        List<Account> users = new ArrayList<>();
        for (AccountDto usersdto : usersdtos) {
            users.add(transferAccountDtoToAccount(usersdto));
        }
        return users;
    }

    public void getAccount(String userInfo, String subscriptionInfo) {
    }


    // methods to add user, upload , account and subscription to these lists and connect to Account
   // public void addUploadToAccount(AccountDto accountDto, Account account) {
    //    for (Upload upload : accountDto.getUploads()) {
     //       if (!upload.getUpload().isEmpty()) {
       //         upload.setAccount(account);
         //       uploadRepository.save(upload);
          //  }
//}
   // }

   // public void addOrderToAccount(AccountDto accountDto, Account account) {
     //   for (Order order : accountDto.getOrders()) {
     //       if (!order.getOrder().isEmpty()) {
     //           order.setAccount(account);
     //           orderRepository.save(order);
     //       }
   //     }
 //   /}

  //  public void addSubscriptionToAccount(AccountDto accountDto, Account account) {
  ///      for (Subscription subscription : accountDto.getSubscriptions()) {
 //           if (!subscription.getSubscription().isEmpty()) {
 //               subscription.setAccount(account);
   //             subscriptionRepository.save(subscription);
   //         }
   //     }

//    }

  //  public void addUserToAccount(AccountDto accountDto, Account account) {
  //      for (User user : accountDto.getUsers()) {
    //        if (!user.getUser().isEmpty()) {
       //         user.setAccounts(account);
       //         userRepository.save(user);
       //     }
      //  }

  //  }


}
