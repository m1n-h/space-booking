public interface DiscountPolicy {
    Money calculateDiscount(Money basePrice);
}
