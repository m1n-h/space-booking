public class FixDiscountPolicy implements DiscountPolicy {

    private final Money discountAmount;

    public FixDiscountPolicy(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Money calculateDiscount(Money basePrice) {
        if (basePrice.getAmount() <= discountAmount.getAmount()) { return Money.ZERO; }

        return Money.won(basePrice.getAmount() - discountAmount.getAmount());
    }
}
