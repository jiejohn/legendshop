/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ---------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class PageGengrator {
	
	/** The log. */
	protected Logger log = LoggerFactory.getLogger(PageGengrator.class);
	
	/** The instance. */
	private static PageGengrator instance = null;
	
	/** The freemarker cfg. */
	private Configuration freemarkerCfg = null;

	/**
	 * Instantiates a new page gengrator.
	 */
	private PageGengrator() {

	}

	/**
	 * Gets the single instance of PageGengrator.
	 * 
	 * @return single instance of PageGengrator
	 */
	public static PageGengrator getInstance() {
		if (instance == null) {
			instance = new PageGengrator();
		}
		return instance;
	}

	/**
	 * 生成静态页面主方法.
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            一个Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静态页面的路径
	 */
	public void crateHTML(ServletContext context, Map<String, Object> data, String templatePath, String targetHtmlPath) {
		try {
			// 指定模版路径
			Template template = getConfiguration(context).getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String htmlPath = context.getRealPath("/html") + "/" + targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the configuration.
	 * 
	 * @param context
	 *            the context
	 * @return the configuration
	 */
	private Configuration getConfiguration(ServletContext context) {
		if (freemarkerCfg == null) {
			freemarkerCfg = new Configuration();
			freemarkerCfg.setServletContextForTemplateLoading(context, "/system/template");
			freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
			freemarkerCfg.setTemplateUpdateDelay(3600);
		}
		return freemarkerCfg;
	}

	/**
	 * Crate html.
	 * 
	 * @param context
	 *            the context
	 * @param filePath
	 *            the file path
	 * @param map
	 *            the map
	 * @param locale
	 *            the locale
	 * @return the string
	 */
	public String crateHTML(ServletContext context, String filePath, Map<String, Object> map, Locale locale) {
		Template template;
		// Locale.setDefault(Locale.ENGLISH);
		try {
			Configuration configuration = getConfiguration(context);
			template = configuration.getTemplate(filePath, locale);
			if (template == null) {
				return "";
			}
			template.setEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			template.process(map, writer);
			return writer.toString();
		} catch (Exception e) {
			log.error("crateHTML error", e);
			return "";
		}
	}

}
