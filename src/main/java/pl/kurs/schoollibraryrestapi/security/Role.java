package pl.kurs.schoollibraryrestapi.security;

public enum Role {
    USER("user"),
    ADMIN("admin");

    public final String label;

    Role(String label) {
        this.label = label;
    }

    public static Role toRole(String label) {
        for (Role e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
