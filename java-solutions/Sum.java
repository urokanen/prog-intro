public class Sum {
	public static void main(final String[] args) {
		int ans = 0;
		for (int i = 0; i < args.length; i++) {
			int ind = 0;
			String temp = args[i];
			while (ind < temp.length()) {
				while (ind < temp.length() && Character.isWhitespace(temp.charAt(ind)) == true) {
					ind++;
				}
				if (ind == temp.length()) {
					break;
				}
				int ind2 = ind;
				while (ind2 < temp.length() && Character.isWhitespace(temp.charAt(ind2)) == false) {
					ind2++;
				}
				ans += Integer.parseInt(temp.substring(ind, ind2));
				ind = ind2;
			}
		}
		System.out.println(ans);
	}
}