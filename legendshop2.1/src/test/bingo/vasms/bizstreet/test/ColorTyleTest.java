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

import java.text.SimpleDateFormat;
import java.util.Date;

public class ColorTyleTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String name = "123done";
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        String s = simpledateformat.format(new Date());
        System.out.println(s);
        System.out.println(name.hashCode());
        long i = (Long.valueOf(s) + Long.valueOf(name.hashCode())) % 5;
        System.out.println(i);
    }
}
