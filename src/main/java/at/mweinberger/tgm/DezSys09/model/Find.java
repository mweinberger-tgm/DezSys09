package at.mweinberger.tgm.DezSys09.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Persistierung und Verwaltung der Accounts
 */
public interface Find extends CrudRepository<Account, String> {

    /**
     * Liefert den der Email zugehörigen Account, wenn in Datenbank vorhanden.
     */
    Account findByEmail(String email);

    /**
     * Liefert einer Liste aller Accounts, die in der Datenbank abgelegt wurden
     */
    List<Account> findAll();
}
