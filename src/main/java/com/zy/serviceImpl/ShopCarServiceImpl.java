package com.zy.serviceImpl;


import com.config.ThreadUtil;
import com.zy.entity.Car;
import com.zy.entity.Commodity;
import com.zy.entity.User;
import com.zy.mapper.ShopCarMapper;
import com.zy.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.ThreadGroupUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

@Service("shopCarServiceImpl")
public class ShopCarServiceImpl implements ShopCarService{

    @Autowired
    ShopCarMapper shopCarMapper;

    HttpSession session;
    List<Car> list1;
    int total;

//    @Override
//    public void run() {
//        for(int i=0;i<list1.size();i++){
//            System.out.println(list1.get(i).getAddress());
//            total+=shopCarMapper.insertsopcar(list1.get(i));
//        }
//    }

    @Override
    public String insertsopcar(HttpSession session) throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String applicationData = df.format(new Date()).substring(0,10);
        User user = (User) session.getAttribute("user");
        String singleNumber=UUID.randomUUID().toString();
        String address="地址";
        String needsTime="几天";
        HashMap<Commodity,Integer> hashshopcar = (HashMap<Commodity, Integer>) session.getAttribute("shopcar");
        if(hashshopcar==null){
            hashshopcar = new HashMap<>();
            session.setAttribute("shopcar",hashshopcar);
        }
        int total=0;
        for(Commodity key:hashshopcar.keySet())
        {
            total+=hashshopcar.get(key);
        }
        List<Car> list=new ArrayList<>();
        for(Commodity key:hashshopcar.keySet())
        {
            Car car = new Car();
            car.setSingleNumber(singleNumber);
            car.setApplicationSector(user.getSector());
            car.setApplicationData(applicationData);
            car.setApplicationName(user.getName());
            car.setProductName(key.getProductName());
            car.setSpecification(key.getSpecification());
            car.setQuantity(key.getQuantity());
            car.setAddress(address);
            car.setNeedsTime(needsTime);
            car.setTotal(total);
            //total=shopCarMapper.insertsopcar(car);
            System.out.println(car.getAddress());
            list.add(car);
        }
        int num=list.size()/10+1;
        total=0;
        session.setAttribute("num",total);
        System.out.println(num);
        Thread t = null;
        for(int i=0;i<num;i++){
            list1=list.subList(i*10,Math.min((i+1)*10,list.size()));
            t = new ThreadUtil(list1,shopCarMapper,session);
            t.start();
            sleep(1000);
        }
        t.stop();
        System.out.println("aaaaaaaaaa");
        total= (int) session.getAttribute("num");
        System.out.println(total+" "+list.size());
        if(total==list.size()) {
            return "sucesss";
        }
        else {
            return "error";
        }
    }

    @Override
    public String addcommodity(Commodity commodity,HttpSession session) {
        HashMap<Commodity,Integer> hashshopcar = (HashMap<Commodity, Integer>) session.getAttribute("shopcar");
        if(hashshopcar==null){
            hashshopcar = new HashMap<>();
            session.setAttribute("shopcar",hashshopcar);
        }
        if(hashshopcar.containsKey(commodity)){
            int num=hashshopcar.get(commodity)+1;
            hashshopcar.put(commodity,num);
        }
        else{
            hashshopcar.put(commodity,1);
        }
        for(Commodity key:hashshopcar.keySet()){
            System.out.println("ProductName:"+key.getProductName());
        }
        return "success";
    }

}
