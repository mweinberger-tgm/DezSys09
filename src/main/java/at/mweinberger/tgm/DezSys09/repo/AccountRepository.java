package at.mweinberger.tgm.dezsys09.repo;

import at.mweinberger.tgm.dezsys09.data.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository fuer die Persistierung und verwaltung der Accounts
 *
 * @author mweinberger
 */
public interface AccountRepository extends CrudRepository<Account, String> {

    /**
     * Liefert, falls vorhanden, den passenden Account zur angegeben Email
     *
     * @param email String
     * @return Account
     */
    Account findByEmail(String email);

    /**
     * Liefert einer Liste aller Accounts die in dieser Datenbank persisitiert wurden
     *
     * @return List
     */
    List<Account> findAll();
}
