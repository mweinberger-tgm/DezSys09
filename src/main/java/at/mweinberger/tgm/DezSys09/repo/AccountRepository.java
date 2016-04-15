package at.mweinberger.tgm.DezSys09.repo;

import at.mweinberger.tgm.DezSys09.data.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Persistierung und Verwaltung der Accounts
 */
public interface AccountRepository extends CrudRepository<Account, String> {

    /**
     * Liefert den der Email zugehörigen Account, wenn in Datenbank vorhanden.
     */
    Account findByEmail(String email);

    /**
     * Liefert einer Liste aller Accounts, die in der Datenbank abgelegt wurden
     */
    List<Account> findAll();
}
