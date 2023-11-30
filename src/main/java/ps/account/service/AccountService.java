package ps.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.account.model.Account;
import ps.account.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> showAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Integer id_account) {
        return accountRepository.findById(id_account).orElse(null);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);

    }
}
