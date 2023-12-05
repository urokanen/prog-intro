import java.math.BigInteger;

public class SumBigIntegerSpace {
	public static boolean check(char ch) {
		return Character.getType(ch) == Character.SPACE_SEPARATOR;
	}

	public static void main(final String[] args) {
		BigInteger ans = BigInteger.ZERO;
		for (int i = 0; i < args.length; i++) {
			int ind = 0;
			String temp = args[i];
			while (ind < temp.length()) {
				while (ind < temp.length() && check(temp.charAt(ind))) {
					ind++;
				}
				if (ind == temp.length()) {
					break;
				}
				int begin = ind;
				while (ind < temp.length() && !check(temp.charAt(ind))) {
					ind++;
				}
				ans = ans.add(new BigInteger(temp.substring(begin, ind)));
			}
		}
		System.out.println(ans);
	}
}