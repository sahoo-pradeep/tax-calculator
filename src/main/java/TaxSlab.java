import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

public class TaxSlab {
    private static Map<Range, BigInteger> taxSlab2018;
    private static Map<Range, BigInteger> taxSlab2019;
    private static Map<Range, BigInteger> taxSlab2020;

    enum BUDGET{
        YEAR_2018("2018", taxSlab2018),
        YEAR_2019("2019", taxSlab2019),
        YEAR_2020("2020", taxSlab2020);

        String year;
        Map<Range, BigInteger> taxSlab;

        BUDGET(String year, Map<Range, BigInteger> taxSlab) {
            this.year = year;
            this.taxSlab = taxSlab;
        }

        public String getYear() {
            return year;
        }

        public Map<Range, BigInteger> getTaxSlab() {
            return taxSlab;
        }
    }

    static {
        //2018
        taxSlab2018 = new LinkedHashMap<>();
        taxSlab2018.put(Range.of(BigInteger.valueOf(0), BigInteger.valueOf(2_50_000)), BigInteger.valueOf(0));
        taxSlab2018.put(Range.of(BigInteger.valueOf(2_50_000), BigInteger.valueOf(5_00_000)), BigInteger.valueOf(5));
        taxSlab2018.put(Range.of(BigInteger.valueOf(5_00_000), BigInteger.valueOf(10_00_000)), BigInteger.valueOf(20));
        taxSlab2018.put(Range.of(BigInteger.valueOf(10_00_000), BigInteger.valueOf(Integer.MAX_VALUE)), BigInteger.valueOf(30));
        //2019
        taxSlab2019 = taxSlab2018;
        //2020
        taxSlab2020 = new LinkedHashMap<>();
        taxSlab2020.put(Range.of(BigInteger.valueOf(0), BigInteger.valueOf(5_00_000)), BigInteger.valueOf(0));
        taxSlab2020.put(Range.of(BigInteger.valueOf(5_00_000), BigInteger.valueOf(7_50_000)), BigInteger.valueOf(10));
        taxSlab2020.put(Range.of(BigInteger.valueOf(7_50_000), BigInteger.valueOf(10_00_000)), BigInteger.valueOf(15));
        taxSlab2020.put(Range.of(BigInteger.valueOf(10_00_000), BigInteger.valueOf(12_50_000)), BigInteger.valueOf(20));
        taxSlab2020.put(Range.of(BigInteger.valueOf(12_50_000), BigInteger.valueOf(15_00_000)), BigInteger.valueOf(25));
        taxSlab2020.put(Range.of(BigInteger.valueOf(15_00_000), BigInteger.valueOf(Long.MAX_VALUE)), BigInteger.valueOf(30));
    }
}