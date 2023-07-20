package glotov.servlet.model;
public class Customer {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Role role;
    private boolean banned;
    private int bonusPoints;
    private int loyaltyPoints;

    private Customer() {
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isBanned() {
        return banned;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public static class CustomerBuilder {
        private Customer customer;

        public CustomerBuilder() {
            customer = new Customer();
        }

        public CustomerBuilder withId(int id) {
            customer.id = id;
            return this;
        }

        public CustomerBuilder withLogin(String login) {
            customer.login = login;
            return this;
        }

        public CustomerBuilder withFirstName(String firstName) {
            customer.firstName = firstName;
            return this;
        }

        public CustomerBuilder withLastName(String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public CustomerBuilder withPhone(String phone) {
            customer.phone = phone;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            customer.email = email;
            return this;
        }

        public CustomerBuilder withPassword(String password) {
            customer.password = password;
            return this;
        }

        public CustomerBuilder withRole(Role role) {
            customer.role = role;
            return this;
        }

        public CustomerBuilder withBanned(boolean banned) {
            customer.banned = banned;
            return this;
        }

        public CustomerBuilder withBonusPoints(int bonusPoints) {
            customer.bonusPoints = bonusPoints;
            return this;
        }

        public CustomerBuilder withLoyaltyPoints(int loyaltyPoints) {
            customer.loyaltyPoints = loyaltyPoints;
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}
