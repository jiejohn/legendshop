package com.done.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.done.base.BaseSpringController;
import com.done.entity.Item;
import com.done.entity.Model;
import com.done.entity.ModelType;
import com.done.model.Ad;

/**
 * @author He-WenQiang. Create in 2009-10-08 20:40:06. 产品的动态属性配置
 */
@Controller
public class XmlController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(XmlController.class);

    @RequestMapping("/dynamic/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Ad ad) {
        Model mod1 = new Model();
        mod1.setId("1");
        mod1.setType(ModelType.Text);

        Item item1 = new Item();
        item1.setNullEnabled(true);
        item1.setValue(null);
        item1.setKey("key1");

        Item item2 = new Item();
        item2.setNullEnabled(true);
        item2.setValue("value2");
        item2.setKey("key2");
        mod1.setItems(new Item[] { item1, item2 });

        Model mod2 = new Model();
        mod2.setId("2");
        mod2.setType(ModelType.CheckBox);

        Item item3 = new Item();
        item3.setNullEnabled(true);
        item3.setValue("value3");
        item3.setKey("key3");
        mod2.setItems(new Item[] { item1, item2, item3 });

        Model mod3 = mod2.clone();
        mod3.setType(ModelType.Radio);
        Model mod4 = mod2.clone();
        mod4.setType(ModelType.Select);

        Model[] model = { mod1, mod2, mod3, mod4 };
        request.setAttribute("model", model);
        return "/xml/dynamic";
    }

    @RequestMapping(value = "/dynamic/save")
    public String save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("save calling");
        return "forward:/dynamic/query.c";
    }

}
