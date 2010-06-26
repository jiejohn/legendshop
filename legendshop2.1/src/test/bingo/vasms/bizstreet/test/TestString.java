package bingo.vasms.bizstreet.test;

public class TestString {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String a = "/cde/secured/abc/test.jpg";
        System.out.println(a + "长度是" + a.length());
        System.out.println(a.substring(1));
        String b = a.substring(0, a.lastIndexOf("/"));
        System.out.println("b = " + b);
        System.out.println(b.indexOf("secured"));

        String c = "123done/hw/A310.jpg";
        String d = c.substring(0, c.lastIndexOf("/"));
        String[] e = c.split("/");
        System.out.println(e.length);
        if (e.length == 3) {
            if (e[1].equals("hw")) {
                System.out.println("1111");
                String f = new StringBuffer().append(e[0]).append("/").append("hws").append("/").append(e[2])
                        .toString();
                System.out.println(f);
            }
        }
    }
}
