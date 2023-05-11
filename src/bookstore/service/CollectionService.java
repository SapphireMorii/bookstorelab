package bookstore.service;

import bookstore.dao.CollectionDao;
import bookstore.domain.PageBeanCollection;
import bookstore.domain.collection;
import bookstore.exception.AddProductException;
import bookstore.exception.FindProductByIdException;

import java.sql.SQLException;
import java.util.List;

public class CollectionService {
    private CollectionDao dao = new CollectionDao();
    // 添加收藏品
    public void addCollection(collection c) throws AddProductException {
        try {
            dao.addCollection(c);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AddProductException("添加收藏品失败");
        }
    }
    // 根据product_id查找商品
    public collection findCollectByProductId(String product_id) throws FindProductByIdException {
        try {
            return dao.findCollectByProductId(product_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据product_id查找商品失败");
        }
    }
    // 根据product_id和user_id查找商品
    public collection findCollectById(String product_id,int user_id) throws FindProductByIdException {
        try {
            return dao.findCollectById(product_id,user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据product_id和user_id查找商品失败");
        }
    }
    //根据user_id查找收藏品
    public List<collection> findCollectByUserId(int id) throws FindProductByIdException {
        try {
            return dao.findCollectByUserId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据User_ID查找商品失败");
        }
    }
    // 分页操作
    public PageBeanCollection findCollectionByPage(int currentPage, int currentCount, int id) {
        PageBeanCollection pbc = new PageBeanCollection();
        // 封装每页显示数据条数
        pbc.setCurrentCount(currentCount);
        // 封装当前页码
        pbc.setCurrentPage(currentPage);
        try {
            // 获取总条数
            int totalCount = dao.findAllCount(id);
            pbc.setTotalCount(totalCount);
            // 获取总页数,向上取整
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            pbc.setTotalPage(totalPage);
            // 获取当前页数据
            List<collection> cs = dao.findByPage(currentPage, currentCount,id);
            pbc.setCs(cs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pbc;
    }
    //后台系统，根据id删除商品信息
    public void deleteCollection(String id) {
        try {
            dao.delCollectionById(id);;
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id删除商品信息失败！");
        }
    }
}

