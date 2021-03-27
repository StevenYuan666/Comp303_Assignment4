import java.util.ArrayList;

public class Driver {
	
	//makeAnagram
	public static ArrayList<String> makeAnagram(String s){
		ArrayList<String> result = new ArrayList<>();
		//Base case
		if(s.length() == 1) {
			result.add(s);
			return result;
		}
		//Inductive step
		for(int i = 0; i < s.length(); i ++) {
			char c = s.charAt(i);
			String tmp = s.substring(0, i) + s.substring(i + 1);
			ArrayList<String> tmp2 = makeAnagram(tmp);
			for(String aString : tmp2) {
				result.add(c + aString);
			}
		}
		return result;
		/*
		 * "sim"
		 * "s" "im"
		 * ["im", "mi"]
		 * sim ism ims     smi msi mis
		 * "i" "sm"
		 * ["sm", "ms"]
		 * ism sim smi     ims mis msi
		 * "m" "si"
		 * ["si", "is"]
		 * msi smi sim     mis ims ism
		 */
		
	}

	public static void main(String[] args) {
		String s1 = "s";
		String s2 = "si";
		String s3 = "sim";
		String ss= "simple";
		System.out.println(makeAnagram(s1).toString());
		System.out.println(makeAnagram(s2).toString());
		System.out.println(makeAnagram(s3).toString());
		//System.out.println(makeAnagram(ss).toString());
	}

}
