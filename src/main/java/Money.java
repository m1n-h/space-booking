public final class Money {

    public static final Money ZERO = new Money(0);
    private final int amount;

    private Money(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("금액은 음수일 수 없습니다: " + amount);

        this.amount = amount;
    }

    public static Money won(int amount) {
        return new Money(amount);
    }

    public Money plus(Money other) {
        return new Money(amount + other.amount);
    }

    public int getAmount() { return amount; }

    @Override
    public String toString() { return String.format("%,d원", amount); }
}
