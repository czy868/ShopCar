package com.config;

import com.zy.entity.Car;
import com.zy.mapper.ShopCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author czy
 */
@Service("threadUtil")
public class ThreadUtil extends Thread{

    private List<Car> list1;

    ShopCarMapper shopCarMapper;

    HttpSession session;

    public ThreadUtil(){}
    public ThreadUtil(List<Car> idList,ShopCarMapper shopCarMapper,HttpSession session) {
        list1 = idList;
        this.shopCarMapper=shopCarMapper;
        this.session=session;
//        for(int i=0;i<list1.size();i++){
//            System.out.println(Thread.currentThread().getName() + i);
//            System.out.println(list1.get(i).getSingleNumber());
//            System.out.println(list1.get(i).getApplicationSector());
//            System.out.println(list1.get(i).getApplicationData());
//            System.out.println(list1.get(i).getApplicationName());
//            System.out.println(list1.get(i).getProductName());
//            System.out.println(list1.get(i).getSpecification());
//            System.out.println(list1.get(i).getQuantity());
//            System.out.println(list1.get(i).getAddress());
//            System.out.println(list1.get(i).getNeedsTime());
//            System.out.println(list1.get(i).getTotal());
//            shopCarMapper.insertsopcar(list1.get(0));
//        }
    }

    @Override
    public void run() {
        for(int i=0;i<list1.size();i++){
            System.out.println(Thread.currentThread().getName() + i);
            System.out.println(list1.get(i).getSingleNumber());
            System.out.println(list1.get(i).getApplicationSector());
            System.out.println(list1.get(i).getApplicationData());
            System.out.println(list1.get(i).getApplicationName());
            System.out.println(list1.get(i).getProductName());
            System.out.println(list1.get(i).getSpecification());
            System.out.println(list1.get(i).getQuantity());
            System.out.println(list1.get(i).getAddress());
            System.out.println(list1.get(i).getNeedsTime());
            System.out.println(list1.get(i).getTotal());
            System.out.println(list1.get(i));
            System.out.println(list1.size());
            int num= (int) session.getAttribute("num");
            num+=shopCarMapper.insertsopcar(list1.get(i));
            System.out.println(num);
            session.setAttribute("num",num);
        }
    }
}
