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

public class PageProviderImpl extends Pager implements PageProvider {
    //  生成工具条，采用数字形式和POST方式
    @Override
    public String getBar(Locale locale, String url) {
        String path = PagerUtil.getPath();
        StringBuilder str = new StringBuilder();
        if (isFirst()) {
            str.append("<img src=\"").append(path).append("/common/toobar/firstPageDisabled.gif\"><img src=\"").append(
                    path).append("/common/toobar/prevPageDisabled.gif\">&nbsp;");
        } else {
            str.append("<a href='").append(url).append("(1)'><img src=\"").append(path).append(
                    "/common/toobar/firstPage.gif\"></a>&nbsp;").append("<a href='").append(url).append("(").append(
                    previous()).append(")'><img src=\"").append(path)
                    .append("/common/toobar/prevPage.gif\"></a>&nbsp;");
        }
        if (isLast() || (getRowsCount() == 0)) {
            str.append("<img src=\"").append(path).append("/common/toobar/nextPageDisabled.gif\"> <img src=\"").append(
                    path).append("/common/toobar/lastPageDisabled.gif\">&nbsp;");
        } else {
            str.append("<a href='").append(url).append("(").append(next()).append(")'><img src=\"").append(path)
                    .append("/common/toobar/nextPage.gif\"></a>&nbsp;").append("<a href='").append(url).append("(")
                    .append(getPageCount()).append(")'><img src=\"").append(path).append(
                            "/common/toobar/lastPage.gif\"></a>&nbsp;");
        }
        str.append(getString(locale, "pager.from", "  From ")).append("<b>").append(getOffset() + 1).append("</b>")
                .append(getString(locale, "pager.to", " To ")).append("<b>").append(getPageDactSize()).append("</b>");
        str.append(getString(locale, "pager.total", ", Total ")).append("<b>").append(getRowsCount()).append("</b>")
                .append(getString(locale, "pager.items", " Items "));
        str.append("&nbsp;<img src=\"").append(path).append(
                "/common/toobar/gotoPage.gif\">&nbsp;<select name='page' onChange=\"").append(url).append(
                "(this.options[this.selectedIndex].value)\">");
        int begin = (getCurPageNO() > 10) ? getCurPageNO() - 10 : 1;
        int end = (getPageCount() - getCurPageNO() > 10) ? getCurPageNO() + 10 : getPageCount();
        for (int i = begin; i <= end; i++) {
            if (i == getCurPageNO()) {
                str.append("<option value='").append(i).append("' selected>").append(i).append("</option>");
            } else {
                str.append("<option value='").append(i).append("'>").append(i).append("</option>");
            }

        }
        str.append("</select>");
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
