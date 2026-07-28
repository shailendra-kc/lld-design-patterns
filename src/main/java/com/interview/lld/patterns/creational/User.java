package com.interview.lld.patterns.creational;

public final class User {
    private final String name;
    private final String email;
    private final boolean active;

    private User(Builder builder) {
        if (builder.name == null || builder.name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = builder.name;
        this.email = builder.email;
        this.active = builder.active;
    }

    public static Builder builder() { return new Builder(); }

    public String name() { return name; }
    public String email() { return email; }
    public boolean active() { return active; }

    public static final class Builder {
        private String name;
        private String email;
        private boolean active;

        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder active(boolean active) { this.active = active; return this; }
        public User build() { return new User(this); }
    }
}
