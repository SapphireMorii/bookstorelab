package bookstore.service;

import bookstore.domain.PageBean;
import bookstore.domain.PageBeanOrder;
import bookstore.domain.orders;
import bookstore.domain.products;

import java.util.ArrayList;
import java.util.List;

public class PagebeanService {
    //传入当前页和每页数据ps，封装bean(order)
    public PageBeanOrder FindOrderByPage(int CurrentPage1, List<orders> ps) {

        PageBeanOrder bean = new PageBeanOrder();
        // 封装每页显示数据条数
        bean.setCurrentCount(6);
        // 封装当前页码
        bean.setCurrentPage(CurrentPage1);
        // 封装总条数
        bean.setTotalCount(ps.size());
        // 封装总页码
        bean.setTotalPage((int)Math.ceil(ps.size()*1.0/6));//向上取整
        //将orders分为每页的数据
        int begin,end;
        begin = (int)(bean.getCurrentPage()-1)*bean.getCurrentCount();
        end   =  (int)bean.getCurrentPage()*bean.getCurrentCount();
        if(end>bean.getTotalCount()){// 当最后一页显示的数据少于6个时
            end = begin+bean.getTotalCount()-(end-5);
        }
        if(end<begin)
        {
            return null;
        }
        List<orders> ps1 =new ArrayList<orders>(ps.subList(begin,end));
        // 封装数据
        bean.setPs(ps1);
        return bean;
    }
    //传入当前页和每页数据ps，封装bean(product)
    public PageBean FindProductByPage(int CurrentPage1, List<products> ps){

        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(6);
        // 封装当前页码
        bean.setCurrentPage(CurrentPage1);
        // 封装总条数
        bean.setTotalCount(ps.size());
        // 封装总页码

        bean.setTotalPage((int)Math.ceil(ps.size()*1.0/6));//向上取整
        //将ps分为每页的数据
        int begin,end;
        begin = (int)(bean.getCurrentPage()-1)*bean.getCurrentCount();
        end   =  (int)bean.getCurrentPage()*bean.getCurrentCount();
        if(end>bean.getTotalCount()){// 当最后一页显示的数据少于6个时
            end = begin+bean.getTotalCount()-(end-6);
        }
        List<products> ps1 =new ArrayList<products>(ps.subList(begin,end));//下分为每页自begin到end数据
        // 封装数据
        bean.setPs(ps1);
        return bean;
    }
}