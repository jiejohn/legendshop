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

import java.util.Locale;
import java.util.ResourceBundle;

import com.done.common.Constants;

import bingosoft.jcf.util.page.PageProvider;
import bingosoft.jcf.util.page.Pager;
import bingosoft.jcf.util.page.PagerUtil;

public class SimplePageProviderImpl extends Pager implements PageProvider {
    //  生成工具条，采用数字形式和POST方式
    @Override
    public String getBar(Locale locale, String url) {
        String path = PagerUtil.getPath();
        StringBuilder str = new StringBuilder();
        if (isFirst()) {
            // str.append(getString(locale, "pager.previous", "Previous"));
        } else {
            str.append("<a class='pagerDirectionLeft' href='").append(url).append("(").append(previous()).append(")'>")
                    .append(getString(locale, "pager.previous", "Previous")).append("</a>");
        }

        int begin = (getCurPageNO() > 10) ? getCurPageNO() - 10 : 1;
        int end = (getPageCount() - getCurPageNO() > 10) ? getCurPageNO() + 10 : getPageCount();

        for (int i = begin; i <= end; i++) {
            if (i == getCurPageNO()) {
                str.append("<span class='pagerSelected'>").append(i).append("</span>");
            } else {
                str.append("<a class='pagerUnselected' href='").append(url).append("(").append(i).append(")'>");
                str.append("[").append(i).append("]</a>");
            }
        }

        if (isLast() || (getRowsCount() == 0)) {
            str.append("&nbsp;&nbsp;");
        } else {
            str.append("<a class='pagerDirectionRight' href='").append(url).append("(").append(next()).append(")'>")
                    .append(getString(locale, "pager.next", "Next")).append("</a>");
        }

        return str.toString();
    }

    @Override
    public String getBar(String url) {
        return getBar(null, url);
    }

    private String getString(Locale locale, String key, String defaultValue) {
        String value;
        try {
            if (locale != null) {
                value = ResourceBundle.getBundle(Constants.LOCALE_FILE, locale).getString(key);
            } else {
                value = ResourceBundle.getBundle(Constants.LOCALE_FILE).getString(key);
            }
            if (value == null) {
                value = defaultValue;
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            value = defaultValue;
        }

        return value;
    }
}
