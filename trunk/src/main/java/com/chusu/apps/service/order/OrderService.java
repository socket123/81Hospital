package com.chusu.apps.service.order;

import com.chusu.apps.config.Constants;
import com.chusu.apps.dao.order.OrdersMapper;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.type.Type;
import com.chusu.apps.service.BaseService;
import com.chusu.apps.service.department.DepartMentService;
import com.chusu.apps.service.type.TypeService;
import com.chusu.platform.util.DateUtil;
import com.chusu.platform.util.LinearRegression;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;
import com.chusu.platform.util.excel.ExcelUtil;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService extends BaseService<Orders> {

    private final Logger logger = Logger.getLogger(OrderService.class);

    @Autowired
    OrdersMapper orderMapper;

    @Autowired
    DepartMentService departMentService;

    @Autowired
    TypeService typeService;


    /**
     * 表格 头部
     * @return
     */
    public String[] titles(String pames,String htmls){
        String[] titlelist = htmls.split(",");
        return  titlelist;
    }
    /**
     * 表格 tabel
     * @return
     */
    public List<Orders> objecs(String pames){

        Orders orders = new Orders();
        Department department = new Department();
        List<Orders> objex= new ArrayList<Orders>();
        if(pames.equals("listAll") || pames.equals("ListPolice")){//医院物资统计  报警
            Pager<Orders>  ordersPager = selectOrderListAll(0, orders,"", Constants.type.TYPE_BOOKEAN_FALSE);
            objex = ordersPager.getList();
        }

        if(pames.equals("listHistory")){//历史采购详情
            Pager<Orders>  ordersPager =selectOrderlistHistory(0, orders,null,Constants.type.TYPE_BOOKEAN_FALSE);
            objex = ordersPager.getList();
        }
        if(pames.equals("ListDistribution") || pames.equals("ListDepartment")){//物资分配情况
            Pager<Type>  typePager =typeService.selectTypetList(0, department,null,Constants.type.TYPE_BOOKEAN_FALSE);
           objex =   objecs_typelIst(typePager.getList());
        }
        if(pames.equals("list") ){//科室申请详情
            Pager<Orders>  ordersPager = selectOrderList(0, department,null,Constants.type.TYPE_BOOKEAN_FALSE);
            objex = ordersPager.getList();
        }
        if(pames.equals("list_history") ){//历史消耗情况
            Pager<Orders>  ordersPager = selectOrderListAll_history(0, orders,null,Constants.type.TYPE_BOOKEAN_FALSE);
            objex = ordersPager.getList();
        }
        if(pames.equals("List_purchasingPlan") ){//物资采购计划
            Type type = new Type();
            type.setStatus(Constants.type.TYPE_STATUS_ONE);//
            List<Type> typePageInfo = typeService.selectListByWhere(type);
            Pager<Orders> typePageInfo_order = selectOrderListAll(0, orders,"", Constants.type.TYPE_BOOKEAN_FALSE);
            PageInfo<Type> typePageInfos = new PageInfo<Type>();
            typePageInfos.setList(selectOrderList_purchasingPlan(typePageInfo,typePageInfo_order.getList()));

            objex =objecs_typelIst(typePageInfos.getList());
        }

        return  objex;
    }





    /**
     * 格式转换
     * @param types
     * @return
     */
    public List<Orders> objecs_typelIst(List <Type> types){
        List<Orders> ordersList = new ArrayList<Orders>();
        if(null != types && types.size() > 0){
            for (Type type : types){
                Orders orders = new Orders();
                orders.setNumbers(type.getNumbers());
                orders.setTypeId(type.getId());
                orders.setType_name(type.getTypeName());
                ordersList.add(orders);
            }
        }
        return  ordersList;
    }

    /**
     * 表格导出
     * @param titles
     * @param objecs
     * @param tomcates
     * @return
     */
    public String selectEcler_Tocnat(String[] titles , List<Orders> objecs, String tomcates,String pasres) {


        String url = ExcelUtil.excel(titles ,objecs,tomcates,pasres);

        return  url;
    }



    /**
     * Description: <br>
     * 月份 计算

     */
    public List<String> selectOrderList_mone() {
        List<String> list = new ArrayList<String>();
        DateUtil.getCurrentMonthStartTime();// 本月开始时间
        DateUtil.getCurrentMonthEndTime();// 本月结束时间
        for(int i = 0 ; i < 12 ; i ++ ){
            if(i == 12){
                list.add(DateUtil.getDates(DateUtil.getCurrentMonthStartTime()));
            }else {
                list.add(DateUtil.getDates(DateUtil.sdfd((12-i))));
            }
        }
        return list;
    }
    /**
     * Description: <br>
     * 物资预判  list     * @param pageNo
     * @return <br>
     */
    public List<Type> selectOrderList_TypeList(List<Type> types) {
        Type typels = new Type() ;
        typels.setStatus(Constants.type.TYPE_STATUS_ONE);//
        Map<Integer,int[]> map = selectOrderList_echart(typels);
        List<Type> typeList = new ArrayList<Type>();
        if(null != types && types.size() > 0) {
            for(Type type: types){
                Type type1 = new Type();
                int[] arr1= map.get(type.getId());
                Double  muber= 0.00;
                muber = LinearRegression.prinstd(arr1);

                int   numerslist   =   (new   Double(Math.ceil(muber))).intValue();
//                int   police   =   (new   Double(type.getPolice())).intValue();

                type1.setNumbers(numerslist);
                type1.setId(type.getId());
                type1.setTypeName(type.getTypeName());
                typeList.add(type1);
            }
        }
        return typeList;
    }

    /**
     * Description: <br>
     * 物资 采购计划   list     * @param pageNo
     * if((numerslist - police ) <= 0){
     type1.setStatus(Constants.type.TYPE_STATUS_THREE);
     }
     * @return <br>
     */
    public List<Type> selectOrderList_purchasingPlan(List<Type> types,List<Orders> orders ) {
        Type typels = new Type() ;

        List<Type> typeList = new ArrayList<Type>();
        List<Type> typeLists = new ArrayList<Type>();

        typeList = selectOrderList_TypeList(types);
        if(null != typeList && typeList.size() > 0) {
            for (Type type : typeList) {
                if(null != typeList && typeList.size() > 0) {
                    for (Orders order : orders) {
                        if (order.getTypeId() == type.getId()){
                            if((order.getNumbers() - type.getNumbers() ) <=  order.getPolice()){
                                type.setStatus(Constants.type.TYPE_STATUS_THREE);

                            }else {
                                type.setNumbers(0);
                            }
                        }
                    }
                }
                typeLists.add(type);
            }
        }



        return typeList;
    }


    /**
     * Description: <br>
     * 物资损耗分析图表数  计算     *

     * @return <br>
     */
    public   Map<Integer,int[]>  selectOrderList_echart(Type types) {
        Map<String,Object> map = new HashMap<String,Object>();
        Map<Integer,int[]> maps = new HashMap<Integer,int[]>();



        List<Type> typeList =  typeService.selectListByWhere(types);
        if(null != typeList && typeList.size() > 0) {
            for(Type type: typeList){
                int[] arr1=new int[12];
                maps.put(type.getId(),arr1);
            }
        }
        for(int i = 0 ; i < 12 ; i ++ ){
            List<Orders> ordersList = new ArrayList<Orders>();
            if(i == 11){
//                map.put("startTime",DateUtil.getCurrentMonthStartTime());
//                map.put("endTime",DateUtil.getCurrentMonthEndTime());
                ordersList = list_times(map);
            }else {
              map.put("startTime",DateUtil.sdfd((12-i)));
                 map.put("endTime",DateUtil.yummone((12-i)));

                 if(null != types){
                     map.put("typeId", types.getId());
                 }

            }
            map.put("order_type", Constants.type.ORDER_TYPE_ONE);//医院内部采购
            List<Orders> ordersList1 = list_times_history( orderMapper.selectOrderListAll(map),ordersList);
            if(null != ordersList1 && ordersList1.size() > 0) {
                for(int j = 0 ; j < ordersList1.size() ; j ++ ){
                    Orders orders = ordersList1.get(j);
                    int[] arr1 =  maps.get(orders.getTypeId());
                    arr1[i] = orders.getNumbers();
                   maps.put(orders.getTypeId(),arr1);
                    }
                }
            }
        return  maps;
    }





    /**
     * Description: <br>
     * 月份 计算 报警

     */
    public List<String> selectOrderList_mone_proclic() {
        // 报警 功能
        List<String> list = new ArrayList<String>();
        DateUtil.getCurrentMonthStartTime();// 本月开始时间
        DateUtil.getCurrentMonthEndTime();// 本月结束时间
        for(int i = 0 ; i < 13 ; i ++ ){
            if(i == 12){
                list.add(DateUtil.getDates(DateUtil.getCurrentMonthStartTime()));
            }else {
                list.add(DateUtil.getDates(DateUtil.sdfd((12-i))));
            }
        }
        return list;
    }
    /**
     * Description: <br>
     * 物资损耗分析图表数  计算   报警   *

     * @return <br>
     */
    public   Map<Integer,int[]>  selectOrderList_echart_proclie(Type types,Department department,String name) {
        // 报警 功能
        Map<String,Object> map = new HashMap<String,Object>();
        Map<Integer,int[]> maps = new HashMap<Integer,int[]>();
        List<Department> departmentList =  departMentService.selectListByWhere(department);
        if(null != departmentList && departmentList.size() > 0) {
            for(Department departments: departmentList){
                int[] arr1=new int[13];
                maps.put(departments.getId(),arr1);
            }
        }

        if(null != departmentList){


        for(Department department1 :departmentList){

        for(int i = 0 ; i < 13 ; i ++ ){

            if(null != types){
                map.put("typeId", types.getId());
            }
            if(null != department){
                map.put("departmentId", department1.getId());
            }
            if(StringUtil.isEmpty(name)){

            }else {
                map.put("name", "%"+name+"%");
            }

            List<Orders> ordersList = new ArrayList<Orders>();
            if(i == 12){
                map.put("startTime",DateUtil.getCurrentMonthStartTime());
                map.put("endTime",DateUtil.getCurrentMonthEndTime());
//                ordersList = list_times(map);
            }else {
                map.put("startTime",DateUtil.sdfd((12-i)));
                map.put("endTime",DateUtil.yummone((12-i)));



            }



            map.put("order_type", Constants.type.ORDER_TYPE_ONE);//医院内部采购
            List<Orders> ordersList1 = list_times_history( orderMapper.selectOrderListAll(map),ordersList);
            if(null != ordersList1 && ordersList1.size() > 0) {
                for(int j = 0 ; j < ordersList1.size() ; j ++ ){
                    Orders orders = ordersList1.get(j);
                    logger.info("报警 接口");
                    int[] arr1 =  maps.get(department1.getId());
                    arr1[i] = orders.getNumbers();
                    maps.put(department1.getId(),arr1);
                }
            }
        }

        }
        }
        return  maps;
    }







    /**
     * Description: <br>
     * 订单列表查询
     *
     * @param pageNo
     * @return <br>
     */
    public Pager<Orders> selectOrderList(Integer pageNo, Department department,String name,Boolean falge) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_type", Constants.type.ORDER_TYPE_ONE);
        if(falge){

            map.put("id", department.getId());
            if (StringUtil.isEmpty(name)){

            }else {
                map.put("name", "%"+name+"%");


            }
        }

        int total = orderMapper.selectOrderCount(map);
        Pager<Orders> pages = new Pager<Orders>(total, pageNo);
        if(falge) {
            map.put("offset", pages.getOffset());
            map.put("size", pages.getLimit());
        }
        pages.setList(orderMapper.selectOrderList(map));
        return pages;
    }


    /**
     * Description: <br>
     * 历史采购详情
     *
     * @param pageNo
     * @return <br>
     */
    public Pager<Orders> selectOrderlistHistory(Integer pageNo, Orders orders,String name ,Boolean falge) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(name)){

        }else {
            map.put("name", "%"+name+"%");


        }
        map.put("order_type", Constants.type.ORDER_TYPE_TWO);
//        map.put("id", orders.getId());
        int total = orderMapper.selectOrderCount(map);
        Pager<Orders> pages = new Pager<Orders>(total, pageNo);
        if (falge) {
            map.put("offset", pages.getOffset());
            map.put("size", pages.getLimit());
        }
        pages.setList(orderMapper.selectOrderList(map));
        return pages;
    }


    /**
     * 医院物资 统计 列表查询
     * @param pageNo
     * @param orders
     * @param falge // 是全选还是分页
     * @return
     */
    public Pager<Orders> selectOrderListAll(Integer pageNo, Orders orders,String name ,Boolean falge ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map_two = new HashMap<String, Object>();
//        Map<String, Object> map_three = new HashMap<String, Object>();
        map.put("order_type", Constants.type.ORDER_TYPE_TWO);//医院外部采购
        map_two.put("order_type", Constants.type.ORDER_TYPE_ONE);//医院内部采购

        if (StringUtil.isEmpty(name)){

        }else {
            map.put("name", "%"+name+"%");

            map_two.put("name", "%"+name+"%");//
        }
        int total = orderMapper.selectOrderListAllCount(map);
        Pager<Orders> pages_one = new Pager<Orders>(total, pageNo);
        Pager<Orders> pages = new Pager<Orders>(total, pageNo);
        Pager<Orders> pages_two = new Pager<Orders>(total, pageNo);
       List<Orders> ordersList = new ArrayList<Orders>();

        if (falge){
            map.put("offset", pages_one.getOffset());
            map.put("size", pages_one.getLimit());
            map_two.put("offset", pages_one.getOffset());
            map_two.put("size", pages_one.getLimit());
        }else {

        }


        pages_one.setList(orderMapper.selectOrderListAll(map));
        pages_two.setList(orderMapper.selectOrderListAll(map_two));
        ordersList = list_times(map_two);
        pages.setList(List_sum(pages_one.getList(),pages_two.getList(),ordersList));
        return pages;
    }

    /**
     * Description: <br>
     * 历史消耗情况 统计 列表查询
     *
     * @param pageNo
     * @return <br>
     */
    public Pager<Orders> selectOrderListAll_history(Integer pageNo, Orders orders,String name ,Boolean falge) {
        Map<String, Object> map_two = new HashMap<String, Object>();

        map_two.put("order_type", Constants.type.ORDER_TYPE_ONE);//医院内部采购

        if (StringUtil.isEmpty(name)){

        }else {
            map_two.put("name", "%"+name+"%");//医院内部采购
        }
        int total = orderMapper.selectOrderListAllCount(map_two);
        List<Orders> ordersList = new ArrayList<Orders>();
        Pager<Orders> pages_two = new Pager<Orders>(total, pageNo);
        if(falge){
            map_two.put("offset", pages_two.getOffset());
            map_two.put("size", pages_two.getLimit());
        }else {
            map_two.put("numbers","numbers");
        }

        pages_two.setList(orderMapper.selectOrderListAll(map_two));
        ordersList = list_times(map_two);
        pages_two.setList(list_times_history(pages_two.getList(),ordersList));
        return pages_two;
    }


    /***
     * 获取 历史消耗 情况
     * @param pages_two
     * @param ordersList
     * @return
     */
    public  List<Orders> list_times_history(List<Orders> pages_two, List<Orders> ordersList){
        List<Orders> ordersLists = new ArrayList<Orders>();
        if(null != pages_two && pages_two.size() > 0) {
            for (int i = 0 ; i < pages_two.size() ; i ++) {
                Orders orderses = pages_two.get(i);
            if(null != ordersList && ordersList.size() > 0) {
                for (Orders orders : ordersList) {
                    if (orders.getTypeId() == orderses.getTypeId()) {

                        orderses.setNumbers(orderses.getNumbers() - orders.getNumber());

                    }

                }
            }

                ordersLists.add(orderses);
            }
        }

        return  ordersLists;
    }



    /**
     * 获取各部门 的种类
     * @return
     */
    public  List<Orders> list_times( Map<String, Object> map_two){
         List<Orders> ordersList = new ArrayList<Orders>();
         List<Department> departmentList =  departMentService.selectAll();
      if(null != departmentList && departmentList.size() > 0){
          for (Department department : departmentList){
              List<Orders> ordersListes = new ArrayList<Orders>();
              map_two.put("id",department.getId());
              ordersList.addAll(orderMapper.selectOrderListTimes(map_two));
//              if(null != ordersListes && ordersListes.size() > 0){
//                  for (Orders orders  : ordersListes){
//                      ordersList.add(orders);
//                  }
//              }
          }
      }
        return  ordersList;
    }

    /**
     * 数据计算 库存数据计算
     * @return
     */
    public List<Orders>  List_sum(List<Orders> pages_one, List<Orders> pages_two, List<Orders> ordersList){
        List<Orders> orderss = new ArrayList<Orders>();
        List<Orders> orderssTime = new ArrayList<Orders>();
        Integer numbers = 0;
        // 算 库存
        if(null != pages_one &&  pages_one.size() > 0){
            for (int i = 0 ; i < pages_one.size() ; i ++){
                Orders orderses = pages_one.get(i);
                if(null != pages_two &&  pages_two.size() > 0){
                       for(Orders orders :pages_two){
                        if(orders.getTypeId() == orderses.getTypeId()){
                            numbers = (orderses.getNumbers() - orders.getNumbers());
//                            if(null == orderses.getPolice() ){
//                                orderses.setPolice(0);
//                            }
//                            if(orderses.getNumbers()  <= orderses.getPolice() ){
//
////                                orderses.setNumbers(0);
//                            }else{
//
//                            }

                            orderses.setNumbers(numbers);
                        }
                      }
                    }
                orderss.add(orderses);
            }
            // 算 部门统计 最新的订单 相加

        for (int i = 0 ; i < orderss.size() ; i ++){
              Orders orderses = pages_one.get(i);
                 if(null != ordersList &&  ordersList.size() > 0){
//                     for(Orders orders_tiem :ordersList){
                         for (int j = 0 ; j < ordersList.size() ; j ++){
                             Orders orders = ordersList.get(j);
                         if(orderses.getTypeId() == orders.getTypeId()){
                             orderses.setNumbers(orderses.getNumbers()+orders.getNumber());
                         }
                     }
                    }
            if(orderses.getNumbers() <= orderses.getPolice()){
                orderses.setStatus(Constants.type.ORDER_STATUS_ONE);
//                                 orderses.setNumbers(0);
            }else{
                orderses.setStatus(Constants.type.ORDER_STATUS_TWO);
            }
            orderssTime.add(orderses);
            }
        }
        return  orderssTime;
    }


    /**
     * 修改或保存
     */
    public String saveOrUpdate(Orders  department ) {
        int count = 0;
        department.setTime(new Date());
        if (department == null) {
            count = -1;
        } else if (null == department.getId()) {
            department.setCreateTime(new Date());
            logger.info("订单 新增");
            count = orderMapper.insertSelective(department);
        } else {
            logger.info("订单 修改");
            count = orderMapper.updateByPrimaryKeySelective(department);
        }
        return count + "";
    }

    /**
     * 根据id删除
     */
    public String delete(Integer id) {
        int count = 0;
        if (id == null) {
            count = -1;
        } else {
            count = orderMapper.deleteByPrimaryKey(id);
        }
        return count + "";
    }
}
