public class StudentDiscountPolicy implements DiscountPolicy {

    private final double discountRate;

    public StudentDiscountPolicy(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public Money calculateDiscount(Money basePrice) {
        int discounted = (int) (basePrice.getAmount() * (1 - discountRate));
        return Money.won(discounted);
    }
}
