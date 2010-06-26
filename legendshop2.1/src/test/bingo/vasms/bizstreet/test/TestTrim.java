package bingo.vasms.bizstreet.test;

public class TestTrim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "20090314152331730042　";
		System.out.println(a+"长度是"+a.length());
		
		String b= a.trim();
		System.out.println(b+"长度是"+b.length());
		
		
		String name = "hew sdf 水电费是否的 1 3 的 水电费 发生 大幅   风格的歌";
		name = name.replace(" ", " ");//去掉中文的空格
		String[] r = name.split(" ");
		System.out.println(r.length);
		for (String string : r) {
			System.out.println(string);
		}
		
		System.out.println(Math.round(11.5));
		System.out.println(Math.round(-11.5));
		
//		short s1 = 1; 
//		s1 = s1 + 1;
		
		short s1 = 1;
		s1 += 1;
		System.out.println(s1);
		
	}


}
