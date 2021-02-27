package org.example.repo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Account;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class DateBase {
    private static HashMap<UUID, Account> dateBase = new HashMap<>();

    public static Account getAccount(UUID uuid) {
        return dateBase.get(uuid);
    }

    public static void putAccount(UUID uuid, Account account) {
        dateBase.put(uuid, account);
    }
}

