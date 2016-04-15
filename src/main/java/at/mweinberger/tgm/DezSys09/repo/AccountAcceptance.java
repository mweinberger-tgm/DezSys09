package at.mweinberger.tgm.DezSys09.repo;

import at.mweinberger.tgm.DezSys09.data.Account;

/**
 * Validierung der Benutzerdaten.
 */
public class AccountAcceptance {

    /**
     * Ueberprueft ob alle erforderlichen Parameter ausgefuellt wurden.
     */
    public static boolean hasFilledOut(Account account) {
        // Sind alle erforderlichen Felder ausgefuellt?
        if (account != null && account.getEmail() != null && account.getUsername() != null && account.getPassword() != null) {
            return true; // -> JA
        }
        return false; // -> NEIN, erneut eingeben
    }

    /**
     * Ueberprueft ob der mitgelieferte Account eine passende Email-Adresse und ein passendes Passwort in der Datenbank hat.
     */
    public static boolean hasLoginData(Account account) {
        // Ist der Account valide?
        if (account != null && account.getEmail() != null && account.getPassword() != null) {
            return true; // -> JA
        }
        return false; // -> NEIN, Fehlermeldung
    }
}
