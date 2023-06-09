package bookstore.service;

import bookstore.dao.ProductDao;
import bookstore.domain.PageBean;
import bookstore.domain.products;
import bookstore.exception.AddProductException;
import bookstore.exception.FindProductByIdException;
import bookstore.exception.ListProductException;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao dao = new ProductDao();
    // 添加商品
    public void addProduct(products p) throws AddProductException {
        try {
            dao.addProduct(p);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AddProductException("添加商品失败");
        }
    }
    // 查找所有商品信息
    public List<products> listAll() throws ListProductException {
        try {
            return dao.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ListProductException("查询商品失败");
        }
    }
    // 分页操作
    public PageBean findProductByPage(int currentPage, int currentCount,
                                      String category) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        // 封装当前查找类别
        bean.setCategory(category);
        try {
            // 获取总条数
            int totalCount = dao.findAllCount(category);
            bean.setTotalCount(totalCount);
            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            // 获取当前页数据
            List<products> ps = dao.findByPage(currentPage, currentCount,
                    category);
            bean.setPs(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
    // 根据id查找商品
    public products findProductById(String id) throws FindProductByIdException {
        try {
            return dao.findProductById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据ID查找商品失败");
        }
    }
    /********秦*********/
    // 根据name查找商品
    public products findProductByName(String name) throws FindProductByIdException {
        try {
            return dao.findProductByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据ID查找商品失败");
        }
    }
    /********秦*********/
    // 下载销售榜单
    public List<Object[]> download(String year, String month) {
        List<Object[]> salesList = null;
        try {
            salesList = dao.salesList(year, month);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
    /*******销售榜单按商品名和时间********/
    public List<Object[]> downloadByManyConditions(String year, String month, String name, String category) {
        List<Object[]> salesList = null;
        try {
            salesList = dao.salesListByManyConditions(year, month, name, category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
    /*********/
    // 多条件查询
    public List<products> findProductByManyCondition(String id, String name,
                                                    String category, String minprice, String maxprice) {
        List<products> ps = null;
        try {
            ps = dao.findProductByManyCondition(id, name, category, minprice,
                    maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // 修改商品信息
    public void editProduct(products p) {
        try {
            dao.editProduct(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //前台，获取本周热销商品
    public List<Object[]> getWeekHotProduct() {
        try {
            return dao.getWeekHotProduct();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台获取本周热销商品失败！");
        }
    }
    //前台，用于搜索框根据书名来模糊查询相应的图书
    public PageBean findBookByName(int currentPage, int currentCount,
                                   String searchfield, String category) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        // 封装模糊查询的图书名
        bean.setSearchfield(searchfield);
        try {
            // 获取总条数
            int totalCount;
            if(category.equals("全部商品")){
                /******1.查找范围为全部图书*****/
                totalCount = dao.findBookByNameAllCount(searchfield);
                bean.setTotalCount(totalCount);
                /******  自加    *****/
                //满足条件的图书
                List<products> ps = dao.findBookByName(currentPage,currentCount,searchfield);
                bean.setPs(ps);
            }else{
                /******2.查找范围为当前页面（类别）*******/
                totalCount = dao.findBookByNameCategory(searchfield,category);
                bean.setTotalCount(totalCount);
                /******  自加    *****/
                //满足条件的图书
                List<products> ps = dao.findBookByNameCategory(currentPage,currentCount,searchfield,category);
                bean.setPs(ps);

            }
            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            return bean;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台搜索框根据书名查询图书失败！");
        }
    }
    //后台系统，根据id删除商品信息
    public void deleteProduct(String id) {
        try {
            dao.deleteProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id删除商品信息失败！");
        }
    }
}