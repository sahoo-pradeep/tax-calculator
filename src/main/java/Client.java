public class Client {
    public static void main(String[] args) {
        Long taxableIncome = 4_50_000L;

        for (TaxSlab.BUDGET budget :TaxSlab.BUDGET.values()){
            TaxCalculator.calculate(taxableIncome, budget, true);
            System.out.println();
        }
    }
}