package main.java.pertemuan11.Jfc_Mvc.src.Tugas.main;

import main.java.pertemuan11.Jfc_Mvc.src.Tugas.controller.ProductController;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.controller.ProductControllerLocal;
//import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.ProductMapper;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.ProductMapper;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.ProductMapperLocal;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.view.ProductView;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.util.SSLUtils;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.view.ProductViewLocal;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class main {
    public static void main(String[] args) {

        SSLUtils.disableSSLVerification();

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
//        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        ProductMapperLocal mapper = sqlSession.getMapper(ProductMapperLocal.class);
        ProductViewLocal view = new ProductViewLocal();
//        new ProductController(view, mapper);
        new ProductControllerLocal(view);
        view.setVisible(true);
    }
}