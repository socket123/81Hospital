package com.chusu.apps.controller.order;

import com.chusu.apps.config.Constants;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.model.type.Type;
import com.chusu.apps.service.department.DepartMentService;
import com.chusu.apps.service.order.OrderService;
import com.chusu.apps.service.type.TypeService;
import com.chusu.platform.util.DateUtil;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <Description> <br>
 *   订单管理
 * 
 * @author Administrator<br>
 * @CreateDate 2016年1月12日 <br>
 */
@Controller
@RequestMapping("/admin/order")
public class OrderController extends BaseAction {
    private final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    DepartMentService departMentService;

    @Autowired
    TypeService typeService;

    /**
     * 数据 导出功能 ajxa 请求
     * @param model
     * @param pames
     * @param htmls
     * @return
     */
    @ResponseBody
    @RequestMapping("/List_Excel")
    public String List_Excel(ModelMap model,String pames,String htmls) {
        String [] titles = orderService.titles(pames,htmls);
        List<Orders> objex = orderService.objecs(pames);
        String  urls = orderService.selectEcler_Tocnat(titles,objex,request.getSession().getServletContext().getRealPath("/"),pames);
        return urls ;
    }


    /**
     * Description: <br>
     *  首页  界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/List_platform")
    public String List_platform(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,Type type) {


//        List<Type> typeList = typeService.selectAll();
//        int i = 0;int j = 0 ;
//        for (Type typeList1 : typeList){
//            i ++ ;
//            if(i >= 39){
//                i = 0;
//            }
//            Orders orders = new Orders();
//            orders.setOrderType("1");
//            orders.setDepartmentId(i);
//            orders.setTypeId(typeList1.getId());
//            orders.setNumber(10);
//            if( j == 12){
//                j = 0;
//            }
//            orders.setCreateTime(DateUtil.sdfd((12-j)));
//            orderService.saveOrUpdate(orders);
//        }

        // 消耗排名
        Pager<Orders> pager = orderService.selectOrderListAll_history(currentIndex, order,"",Constants.type.TYPE_BOOKEAN_FALSE);
        List<Orders> listw  = pager.getList();

        Collections.sort(listw, new Comparator<Orders>() {
            public int compare(Orders o1, Orders o2) {
                return o2.getNumbers().compareTo(o1.getNumbers());
            }
        });
        // 报警
        Pager<Orders> pager_procli  =orderService.selectOrderListAll(currentIndex, order,"", Constants.type.TYPE_BOOKEAN_FALSE);


        // 库存 物资
        model.put("page", orderService.selectOrderListAll(currentIndex, order,"", Constants.type.TYPE_BOOKEAN_TRUE));
        model.put("status_biao",status_biao );
        model.put("listall",pager.getList());
        model.put("pager_procli",pager_procli.getList());
//        model.put("status_biao",status_biao );
        return "admin/order/order_list_platform";
    }

    /**
     * Description: <br>
     *  物资  界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/List_echarts")
    public String List_echarts(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,Type type) {

        if(null == type.getId()){
            type.setId(Constants.type.TYPE_STATID);
        }
        type.setStatus(Constants.type.TYPE_STATUS_ONE);//
        List<String> mone = orderService.selectOrderList_mone();
        Map<Integer,int[]> map = orderService.selectOrderList_echart(type);
        model.put("mone",mone );
        Type type1 = new Type();
        type1.setStatus(Constants.type.TYPE_STATUS_ONE);//
        model.put("typeList", typeService.selectListByWhere(type1));
        model.put("typelistss", typeService.selectListByWhere(type));
        model.put("map", map);
        model.put("type", type);
        model.put("status_biao",status_biao );
        return "admin/order/order_highcharts";
    }



    /**
     * Description: <br>
     *  物资预判  界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/List_Anticipation")
    public String List_Anticipation(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,Type type) {

        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        type.setStatus(Constants.type.TYPE_STATUS_ONE);//
        PageInfo<Type> typePageInfo = typeService.selectPageListByWhere(currentIndex, Constants.page.PAGE_SIZE, type);
        typePageInfo.setList(orderService.selectOrderList_TypeList(typePageInfo.getList()));
        model.put("page",typePageInfo);
        model.put("status_biao",status_biao );
        return "admin/order/order_listAnticipation";
    }

    /**
     * Description: <br>
     *  物资采购计划  界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/List_purchasingPlan")
    public String List_purchasingPlan(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,Type type,String name) throws UnsupportedEncodingException {
        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        type.setStatus(Constants.type.TYPE_STATUS_ONE);//
        PageInfo<Type> typePageInfo = typeService.selectPageListByWhere(currentIndex, Constants.page.PAGE_SIZE, type);
        Pager<Orders> typePageInfo_order = orderService.selectOrderListAll(currentIndex, order,"%" +str+"%", Constants.type.TYPE_BOOKEAN_TRUE);
        typePageInfo.setList(orderService.selectOrderList_purchasingPlan(typePageInfo.getList(),typePageInfo_order.getList()));

        Type type1 = new Type();
        type1.setStatus(Constants.type.TYPE_STATUS_ONE);//
        model.put("page",typePageInfo);
        model.put("type",type);
        model.put("typelist",typeService.selectListByWhere(type1));
        model.put("status_biao",status_biao );
        return "admin/order/order_listPurchasingPlan";
    }


    /**
     * Description: <br>
     *  订单 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/list")
    public String selectDepartmentList(ModelMap model, Integer currentIndex,Department department,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;

        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }

        Pager<Orders> pager = orderService.selectOrderList(currentIndex, department,str,Constants.type.TYPE_BOOKEAN_TRUE);
        model.put("page", pager);
        model.put("departMent", departMentService.selectAll());
        model.put("department", department.getId());
        model.put("size",pager.getList().size() );
        model.put("status_biao",status_biao );
        model.put("name", str);
        return "admin/order/order_list";
    }


    /**
     * Description: <br>
     *  历史采购详情  列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/listHistory")
    public String listHistory(ModelMap model, Integer currentIndex,Orders orders,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;

        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }
        Pager<Orders> pager = orderService.selectOrderlistHistory(currentIndex, orders,str,Constants.type.TYPE_BOOKEAN_TRUE);
        model.put("size",pager.getList().size() );
        model.put("page",pager);
        model.put("name", str);
        model.put("status_biao",status_biao );
        return "admin/order/order_listHistory";
    }


    /**
     * Description: <br>
     *  医院物资 统计 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/listAll")
    public String selectOrderListAll(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }


        model.put("page", orderService.selectOrderListAll(currentIndex, order, str, Constants.type.TYPE_BOOKEAN_TRUE));
        model.put("name", str);
        model.put("status_biao",status_biao );
        return "admin/order/order_listAll";
    }


    /**
     * Description: <br>
     *  历史消耗情况   列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/list_history")
    public String selectOrderListAll_history(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        String str = null;
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }
        model.put("page", orderService.selectOrderListAll_history(currentIndex, order,str,Constants.type.TYPE_BOOKEAN_TRUE));
        model.put("name", str);
        model.put("status_biao",status_biao );
        return "admin/order/order_list_history";
    }

    /**
     * Description: <br>
     *  报警  列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/ListPolice")
    public String selectOrderListPolice(ModelMap model, Integer currentIndex,Orders order,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }

        model.put("page", orderService.selectOrderListAll(currentIndex, order,str, Constants.type.TYPE_BOOKEAN_TRUE));

        model.put("name", str);
        model.put("status_biao",status_biao );
        return "admin/order/order_listPolice";
    }

    /**
     * Description: <br>
     *  科室 物资  统计 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/ListDepartment")
    public String ListDepartment(ModelMap model, Integer currentIndex,Department department,Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        String str ="";
        if(StringUtil.isEmpty(name)){

        }else {
             str  =java.net.URLDecoder.decode(name, "utf-8");
        }

        model.put("page", typeService.selectTypetList(currentIndex, department,str,Constants.type.TYPE_BOOKEAN_TRUE));
        model.put("departMent", departMentService.selectAll());
        model.put("department", department.getId());
        model.put("name", str);

        model.put("status_biao",status_biao );
        return "admin/order/order_listDepartment";
    }
    /**
     * Description: <br>
     *   物资 分配情况  统计 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/ListDistribution")
    public String ListDistribution(ModelMap model, Integer currentIndex,Department department, Integer status_biao,String name) throws UnsupportedEncodingException {
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        String str ="";

        if(StringUtil.isEmpty(name)){

        }else {
            str  =java.net.URLDecoder.decode(name, "utf-8");
        }
        Type type = new Type();
        if(null != department.getTypeId()){
            type.setId(department.getTypeId());
//            type.setId(department.getTypeId());
        }else {
            type.setId(Constants.type.TYPE_STATID);
            department.setTypeId(Constants.type.TYPE_STATID);
        }
        model.put("page", typeService.selectTypetList(currentIndex, department,str,Constants.type.TYPE_BOOKEAN_TRUE));
        List<String> mone = orderService.selectOrderList_mone_proclic();
        Map<Integer,int[]> map = orderService.selectOrderList_echart_proclie(type,department,str);
        model.put("mone",mone );
        Type type1 = new Type();
        model.put("typeLists", typeService.selectListByWhere(type));
        model.put("map", map);

//        model.put("orders",orders);
        model.put("typelist",typeService.selectAll());

        model.put("departMent", departMentService.selectAll());
        model.put("departMentlist", departMentService.selectListByWhere(department));
        model.put("department", department);
        model.put("name", str);
        model.put("status_biao",status_biao );
        return "admin/order/order_listDlistribution";
    }

    /**
     * Description: <br>
     * 前往修改或新增  订单 界面
     *
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping("/mergepage")
    public String add(ModelMap model, Integer id) {
        Orders  order = null;
       List<Department> departmentList =  departMentService.selectAll();
       List<Type> types =  typeService.selectAll();
        if (id != null) {
            order = orderService.selectByPrimaryKey(id);
        }
        model.put("Order", order);
        model.put("types", types);
        model.put("departmentList", departmentList);
        return "admin/order/order_edit";
    }

    /**
     * Description: <br>
     * 修改或新增  订单
     *
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(ModelMap model,  Orders  order ) {
        logger.info(" 订单 ");
        return orderService.saveOrUpdate(order);
    }

    /**
     * Description: <br>
     * 删除  订单

     * @param id
     * @return <br>
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {

        return orderService.delete(id);
    }


}
