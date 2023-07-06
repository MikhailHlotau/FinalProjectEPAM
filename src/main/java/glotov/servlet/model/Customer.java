package glotov.servlet.model;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private boolean banned;
    private int bonusPoints;
    private int loyaltyPoints;

    private Customer(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.password = builder.password;
        this.banned = builder.banned;
        this.bonusPoints = builder.bonusPoints;
        this.loyaltyPoints = builder.loyaltyPoints;
    }

    public int getId() {
        return id;
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

    public boolean isBanned() {
        return banned;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String password;
        private boolean banned;
        private int bonusPoints;
        private int loyaltyPoints;

        public Builder() {
            // Установка значений по умолчанию или значения по вашему выбору
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBanned(boolean banned) {
            this.banned = banned;
            return this;
        }

        public Builder setBonusPoints(int bonusPoints) {
            this.bonusPoints = bonusPoints;
            return this;
        }

        public Builder setLoyaltyPoints(int loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", banned=" + banned +
                ", bonusPoints=" + bonusPoints +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}