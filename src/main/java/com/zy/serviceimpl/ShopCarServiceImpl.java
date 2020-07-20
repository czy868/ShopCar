package com.zy.serviceimpl;


import com.config.ThreadUtil;
import com.zy.entity.Car;
import com.zy.entity.Commodity;
import com.zy.entity.User;
import com.zy.mapper.ShopCarMapper;
import com.zy.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.Thread.sleep;

/**
 * @author czy
 */
@Service("shopCarServiceImpl")
public class ShopCarServiceImpl implements ShopCarService{

    @Autowired
    ShopCarMapper shopCarMapper;

    HttpSession session;
    List<Car> list1;
    int total;

    /**
     *
     * @param session
     * @return
     * @throws InterruptedException
     */
    @Override
    public String insertSopcar(HttpSession session) throws InterruptedException {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            list.add(car);
        }
        int num=list.size()/10+1;
        total=0;
        session.setAttribute("num",total);
        Thread t = null;
        for(int i=0;i<num;i++){
            list1=list.subList(i*10,Math.min((i+1)*10,list.size()));
            t = new ThreadUtil(list1,shopCarMapper,session);
            t.start();
            sleep(1000);
        }
        t.stop();
        total= (int) session.getAttribute("num");
        if(total==list.size()) {
            return "sucesss";
        }
        else {
            return "error";
        }
    }

    /**
     *
     * @param commodity
     * @param session
     * @return
     */
    @Override
    public String addCommodity(Commodity commodity,HttpSession session) {
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
        return "success";
    }

}
