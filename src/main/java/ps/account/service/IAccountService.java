package ps.account.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import ps.account.model.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> showAccount();

    public Account findAccountById(Integer id_account);

    public void saveAccount(Account account);

    public void deleteAccount(Account account);
}
