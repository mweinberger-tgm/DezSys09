package at.mweinberger.tgm.DezSys09.repo;

import at.mweinberger.tgm.DezSys09.data.Account;

/**
 * Diese Klasse ist für die Ueberpruefung der Benutzerdaten da.
 *
 * @author mweinberger
 */
public class AccountAcceptance {

    /**
     * Ueberprueft ob alle Daten von einem Account ausgefuellt wurden.
     *
     * @param account Account
     * @return boolean
     */
    public static boolean hasFilledOut(Account account) {
        // Wenn alle Felder ausgefuellt sind return true
        if (account != null && account.getEmail() != null && account.getUsername() != null && account.getPassword() != null) {
            return true;
        }
        return false;
    }

    /**
     * Ueberprueft ob der gegebenen Account eine passende Email und ein passendes Passwort hat.
     *
     * @param account Account
     * @return boolean
     */
    public static boolean hasLoginData(Account account) {
        // Wenn alle Felder vorhanden sind return true
        if (account != null && account.getEmail() != null && account.getPassword() != null) {
            return true;
        }
        return false;
    }
}
