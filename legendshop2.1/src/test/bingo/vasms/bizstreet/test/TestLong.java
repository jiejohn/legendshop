/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package bingo.vasms.bizstreet.test;

import org.springframework.util.StringUtils;

public class TestLong {
    public static void main(String[] args) {
        long l = 0l;
        System.out.println("result = " + (l == 0));

        String path = "D:\\abc\\def";
        System.out.println(path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
        String p = StringUtils.replace(path, "\\", "/");
        System.out.println(p);

    }
}
