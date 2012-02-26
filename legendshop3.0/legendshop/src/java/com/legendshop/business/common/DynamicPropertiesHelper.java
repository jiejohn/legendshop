/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import java.util.List;

import net.sf.json.JSONObject;

import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.dynamic.ModelType;
import com.legendshop.util.AppUtils;

/**
 * The Class DynamicPropertiesHelper.
 */
public class DynamicPropertiesHelper {

	// @RequestMapping("/dynamic/query") for test
	// public String query(HttpServletRequest request, HttpServletResponse
	// response) {
	// String jsonStr =
	// "[{\"id\":\"型号\",\"items\":[{\"key\":\"型号\",\"value\":\"SANXING
	// G2000\"}],\"type\":\"Text\"},{\"id\":\"大小\",\"items\":[{\"key\":\"S\",\"value\":\"\"},{\"key\":\"M\",\"value\":\"\"},{\"key\":\"L\",\"value\":\"\"},{\"key\":\"LX\",\"value\":\"\"}],\"type\":\"CheckBox\"},{\"id\":\"颜色\",\"items\":[{\"key\":\"红色\",\"value\":\"\"},{\"key\":\"黄色\",\"value\":\"\"},{\"key\":\"蓝色\",\"value\":\"\"}],\"type\":\"Radio\"},{\"id\":\"高度\",\"items\":[{\"key\":\"100\",\"value\":\"\"},{\"key\":\"200\",\"value\":\"\"},{\"key\":\"300\",\"value\":\"\"}],\"type\":\"Select\"},{\"id\":\"厂家\",\"items\":[{\"key\":\"厂家\",\"value\":\"三星\"}],\"type\":\"Text\"}]";
	//
	// List<JSONObject> modelList =
	// (List<JSONObject>)JSONArray.fromObject(jsonStr);
	// StringBuffer sb = new StringBuffer();
	// if(AppUtils.isNotBlank(modelList)){
	// for (JSONObject job : modelList) {
	// Model model = (Model)JSONObject.toBean(job, Model.class);
	// if(ModelType.Select.equals(model.getType())){
	// generateSelect(model,sb);
	// }else if(ModelType.Text.equals(model.getType())){
	// generateText(model,sb);
	// }else if(ModelType.CheckBox.equals(model.getType())){
	// generateCheckBox(model,sb);
	// }else if(ModelType.Radio.equals(model.getType())){
	// generateRadio(model,sb);
	// }
	// }
	// }
	// request.setAttribute("dynamicProperties", sb.toString());
	// return "/xml/showdynamic";
	// }

	/**
	 * Gerenate html.
	 * 
	 * @param modelList
	 *            the model list
	 * @return the string
	 */
	public String gerenateHTML(List<JSONObject> modelList) {
		StringBuffer sb = new StringBuffer();
		if (AppUtils.isNotBlank(modelList)) {
			for (JSONObject job : modelList) {
				Model model = (Model) JSONObject.toBean(job, Model.class);
				if (ModelType.Select.equals(model.getType())) {
					generateSelect(model, sb);
				} else if (ModelType.Text.equals(model.getType())) {
					generateText(model, sb);
				} else if (ModelType.CheckBox.equals(model.getType())) {
					generateCheckBox(model, sb);
				} else if (ModelType.Radio.equals(model.getType())) {
					generateRadio(model, sb);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Generate select.
	 * 
	 * @param model
	 *            the model
	 * @param sb
	 *            the sb
	 * @return the string buffer
	 */
	public StringBuffer generateSelect(Model model, StringBuffer sb) {
		if (ModelType.Select.equals(model.getType())) {
			sb.append(model.getId()).append("&nbsp;<select id='").append(model.getId()).append("' class='attrselect'")
					.append(" name='").append(model.getId()).append("'>");
			sb.append("<option value=''>请选择</option>");
			for (Item item : model.getItems()) {
				sb.append("<option value='").append(item.getKey()).append("'>").append(item.getKey()).append(
						"</option>");
			}
			sb.append("</select><br>");
		}
		return sb;
	}

	/**
	 * Generate text.
	 * 
	 * @param model
	 *            the model
	 * @param sb
	 *            the sb
	 * @return the string buffer
	 */
	public StringBuffer generateText(Model model, StringBuffer sb) {
		if (ModelType.Text.equals(model.getType())) {
			if (AppUtils.isNotBlank(model.getItems())) {
				sb.append("<tr><th>").append(model.getId()).append("</th><td>").append(model.getItems()[0].getValue())
						.append("</td></tr>");
			} else {
				sb.append("<div>").append(model.getId()).append("<input id='").append(model.getId()).append(
						"' class='attrtext'").append(" name='").append(model.getId()).append(" value='")
						.append("value").append("'/></div>");
			}

		}
		return sb;
	}

	/**
	 * Generate radio.
	 * 
	 * @param model
	 *            the model
	 * @param sb
	 *            the sb
	 * @return the string buffer
	 */
	public StringBuffer generateRadio(Model model, StringBuffer sb) {
		if (ModelType.Radio.equals(model.getType())) {
			sb.append(model.getId()).append("&nbsp;");
			for (Item item : model.getItems()) {
				sb.append(item.getKey()).append("<input type='radio' id='").append(model.getId()).append(
						"' class='attrradio'").append(" name='").append(model.getId()).append("' value='").append(
						item.getKey()).append("'/>&nbsp;");
			}
			sb.append("<br>");
		}
		return sb;
	}

	/**
	 * Generate check box.
	 * 
	 * @param model
	 *            the model
	 * @param sb
	 *            the sb
	 * @return the string buffer
	 */
	public StringBuffer generateCheckBox(Model model, StringBuffer sb) {
		if (ModelType.CheckBox.equals(model.getType())) {
			sb.append(model.getId()).append("&nbsp;");
			for (Item item : model.getItems()) {
				sb.append(item.getKey()).append("<input type='checkbox' id='").append(item.getKey()).append(
						"' class='attrchx'").append("' name='").append(model.getId()).append("'/>&nbsp;");
			}
			sb.append("<br>");
		}
		return sb;
	}
}
