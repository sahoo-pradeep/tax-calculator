import java.math.BigInteger;
import java.util.Objects;

public class Range {
    private BigInteger from;
    private BigInteger to;

    private Range(BigInteger from, BigInteger to) {
        this.from = from;
        this.to = to;
    }

    public static Range of(BigInteger from, BigInteger to){
        return new Range(from, to);
    }

    public BigInteger getFrom() {
        return from;
    }

    public BigInteger getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(from, range.from) &&
                Objects.equals(to, range.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
