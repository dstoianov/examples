package unit;


public final class Money {

    public enum Currency {
        EUR,
        USD
    }

    private Double value;

    private Currency currency;

    /**
     * Constructor for class Money.
     *
     * @param value    value
     * @param currency currency
     */
    public Money(Double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    /**
     * Return value passed to the constructor.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Return currency passed to the constructor.
     */
    public Currency getCurrency() {
        return currency;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }

    /**
     * Method multiply that receive
     *
     * @param factor factor
     * @return Money
     */
    public Money multiplyBy(int factor) {
        this.value = value * factor;
        return this;
    }

    /**
     * Example how to use it
     *
     * @param args ars
     */
    public static void main(String[] args) {
        final Money a = new Money(67.89, Currency.EUR); //pass 67.89 EUR as parameters
        final Money b = new Money(98.76, Currency.USD); //pass 98.76 USD as parameters

        Money money = a.multiplyBy(5);
        System.out.println(money);

        Money money1 = b.multiplyBy(10);
        System.out.println(money1);
    }


}
