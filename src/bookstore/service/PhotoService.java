package bookstore.service;

import bookstore.dao.PhotoDao;
import bookstore.domain.photo;

import java.sql.SQLException;
import java.util.List;

public class PhotoService {
    private PhotoDao dao = new PhotoDao();
    //后台系统，查询所有图片
    public List<photo> getAllPhotos() {
        try {
            return dao.getAllPhoto();
        } catch (SQLException e) {
            throw new RuntimeException("查询所有的公告失败！");
        }
    }

    //前台系统，查询状态为1的图片地址
    public List<photo> getselectPhotos() {
        try {
            return dao.getselectPhoto();
        } catch (SQLException e) {
            throw new RuntimeException("查询图片失败！");
        }
    }

    // 添加图片
    public void addPhoto(photo p)  {

        try {
            dao.addPhoto(p);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //后台系统，根据id删除图片信息
    public void deletePhoto(String id) {
        try {
            dao.deletePhoto(id);
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id删除商品信息失败！");
        }
    }
    //后台系统，根据id删除商品信息
    public void updatePhoto(String id) {
        try {
            dao.updatePhoto(id);
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id更改图片信息失败！");
        }
    }

}
