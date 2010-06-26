/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.done.util;

import java.util.List;

import com.done.model.Sub;


public class ConvertBeanUtil {
    public static List<Sub> convertShort(List<Sub> list) {
        if (list == null) {
            return null;
        }
        for (Sub sub : list) {
            sub.setSubAdds(getShortString(sub.getSubAdds()));
            sub.setOrderName(getShortString(sub.getOrderName()));
        }
        return list;
    }

    private static String getShortString(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() > 15) {
            return string.substring(0, 15) + "...";
        }
        return string;
    }
}
