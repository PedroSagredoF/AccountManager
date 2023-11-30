package ps.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
