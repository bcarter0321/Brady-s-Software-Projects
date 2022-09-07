import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Disproves the charming theory by using the de Jager formula.
 *
 * @author Brady Carter
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean loop = false;
        String input;
        double a = 0;
        //loops until the number is positive then breaks the loop
        while (!loop) {
            out.print("Please enter a constant: ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                a = Double.parseDouble(input);
                if (a >= 0) {
                    loop = true;
                }
            }
        }
        return a;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        boolean loop = false;
        String input;
        double b = 0;
        //loops until the number is positive and not one then breaks the loop
        while (!loop) {
            out.print("Please enter a personal number that is not one: ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                b = Double.parseDouble(input);
                if (b > 1) {
                    loop = true;
                }
            }
        }
        return b;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        double constantU = getPositiveDouble(in, out);

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        //creates an array of the 17 numbers for a, b, c , d
        double[] jagerPowers = new double[] { -5, -4, -3, -2, -1, -1.0 / 2,
                -1.0 / 3, -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4,
                5 };
        // checkstyle for magic numbers, not sure what that means

        double a, b, c, d;
        double bestExp1 = 0, bestExp2 = 0, bestExp3 = 0, bestExp4 = 0;
        double bestApprox = 0;

        int i = 0;
        while (i < jagerPowers.length - 1) {
            a = (Math.pow(w, jagerPowers[i]));
            i++;

            int j = 0;
            while (j < jagerPowers.length - 1) {

                b = (Math.pow(x, jagerPowers[j]));
                j++;

                int k = 0;
                while (k < jagerPowers.length - 1) {

                    c = (Math.pow(y, jagerPowers[k]));
                    k++;

                    int l = 0;
                    while (l < jagerPowers.length - 1) {

                        d = (Math.pow(z, jagerPowers[l]));
                        double currentEstimate = a * b * c * d;

                        if (Math.abs(constantU - currentEstimate) < Math
                                .abs(constantU - bestApprox)) {
                            bestApprox = currentEstimate;
                            bestExp1 = jagerPowers[i];
                            bestExp2 = jagerPowers[j];
                            bestExp3 = jagerPowers[k];
                            bestExp4 = jagerPowers[l];
                        }
                        l++;
                    }
                }
            }

        }

        double relativeError = Math.abs((constantU - bestApprox) / constantU);
        out.println("Best exponents for w, x, y and z: " + bestExp1 + " ,"
                + bestExp2 + " ," + bestExp3 + " ," + bestExp4);

        out.println("Best approximation: " + bestApprox);

        out.print("Relative error: ");
        out.print(relativeError, 2, true);

        in.close();
        out.close();

    }
}
