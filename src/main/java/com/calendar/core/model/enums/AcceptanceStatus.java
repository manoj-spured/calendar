package com.calendar.core.model.enums;

public enum AcceptanceStatus {
    ACCEPTED, REJECTED, TENTATIVE
}
public enum ActionType {

    CREATE(1),
    UPDATE(2),
    DELETE(3),

    REPORT(4),
    VOTE(5),
    FAVORITE(6),

    ANSWER(7);

    private final int id;
    ActionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ActionType getById(int id) {
        for(ActionType accountType : ActionType.values()) {
            if(accountType.id == id) {
                return accountType;
            }
        }
        return null;
    }
}