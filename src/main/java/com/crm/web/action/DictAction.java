package com.crm.web.action;

import com.crm.domain.Dict;
import com.crm.service.DictService;
import com.crm.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
/**
 * 字典控制器
 * */

public class DictAction extends ActionSupport implements ModelDriven<Dict> {

    private Dict dict=new Dict();

    public Dict getModel() {
        return dict;
    }

    private DictService dictService;

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 通过字段的type_code值，查询客户级别或者客户的来源
     * */
    public String findByCode(){
        //调用业务层
        List<Dict> list = dictService.findByCode(dict.getDict_type_code());

        //List<Dict> list = dictService.findByCode("006");
        //使用fastjson，把list转换成json字符串
        String jsonString = FastJsonUtil.toJSONString(list);

        //把json字符串写到浏览器
        HttpServletResponse response = ServletActionContext.getResponse();
        //输出
        FastJsonUtil.write_json(response,jsonString);

        return NONE;
    }
}
