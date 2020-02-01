import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class TaxCalculator {
    public static BigInteger calculate(Long taxableIncomeLong, TaxSlab.BUDGET budget){
        return calculate(taxableIncomeLong, budget, false);
    }

    public static BigInteger calculate(Long taxableIncomeLong, TaxSlab.BUDGET budget, Boolean logsRequired){
        System.out.println("Calculating Tax for Taxable Income: " + amountFormat(BigInteger.valueOf(taxableIncomeLong)) + " and Year: " + budget.getYear());
        StringBuilder taxCalculation = new StringBuilder("Tax Calculation: ");
        StringBuilder taxCalculationSimplified = new StringBuilder("= ");

        if (budget.equals(TaxSlab.BUDGET.YEAR_2019) && taxableIncomeLong <= 5_00_000L){
            System.out.println("Note: (Budget 2019) Individual taxpayers having taxable annual income Rs 5 lakh will get full tax rebate and therefore will not be required to pay any income tax.");
            System.out.println("Total Tax: 0");
            return BigInteger.ZERO;
        }

        BigInteger taxableIncome = BigInteger.valueOf(taxableIncomeLong);
        BigInteger totalTax = BigInteger.ZERO;

        Map<Range, BigInteger> taxSlabMap = budget.getTaxSlab();
        for (Map.Entry<Range, BigInteger> taxRange: taxSlabMap.entrySet()){
            if (taxableIncome.compareTo(taxRange.getKey().getTo()) <= 0){
                BigInteger rangeValue = taxableIncome.subtract(taxRange.getKey().getFrom());
                BigInteger tax = rangeValue.multiply(taxRange.getValue()).divide(BigInteger.valueOf(100));
                totalTax = totalTax.add(tax);

                taxCalculation.append(rangeValue)
                        .append("x").append(taxRange.getValue())
                        .append("/100 ");

                taxCalculationSimplified.append(tax).append(" ");
                break;
            } else {
                BigInteger rangeValue = taxRange.getKey().getTo().subtract(taxRange.getKey().getFrom());
                BigInteger tax = rangeValue.multiply(taxRange.getValue()).divide(BigInteger.valueOf(100));
                totalTax = totalTax.add(tax);

                taxCalculation.append(rangeValue)
                        .append("x").append(taxRange.getValue())
                        .append("/100 + ");

                taxCalculationSimplified.append(tax).append(" + ");
            }
        }
        if (logsRequired){
            System.out.println(taxCalculation);
            System.out.println(taxCalculationSimplified.toString() + "= " + totalTax);
        }

        System.out.println("Total Tax: " + amountFormat(totalTax));
        return totalTax;
    }

    private static String amountFormat(BigInteger amount){
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(amount);
    }
}
