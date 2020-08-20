package com.lingtu.geoserver.util;

import com.lingtu.geoserver.config.GeoServerProperties;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class PublishShpUtil {
    @Autowired
    private GeoServerProperties geoServerProperties;

//    public void PublishShp() throws Exception{
//        URL url = new URL(geoServerProperties.getEndpoint());
//        System.out.println(url);
//        GeoServerRESTManager manager = new GeoServerRESTManager(url, geoServerProperties.getUsername(), geoServerProperties.getPassword());
//        GeoServerRESTPublisher publisher =  manager.getPublisher();
//        GeoServerRESTReader geoServerRESTReader = new GeoServerRESTReader(geoServerProperties.getEndpoint(),geoServerProperties.getUsername(),geoServerProperties.getPassword());
//        /**工作区名称*/
//        String workplace = geoServerProperties.getWorkspacename();
//        /**必须和zip压缩包的名称一致*/
//        //数据存储的名称
//        String store_name = "zly";
//        /**数据发布的表名，图层数据名称*/
//        String layerName = "nyc_roads";
//        //WGS84坐标系
//        String srs = "EPSG:4326";
//        //压缩文件
//        File file = new File("E:\\zly\\geoserverdata\\nyc_roads.zip");
//        List<String> workspaces = geoServerRESTReader.getWorkspaceNames();
//        if(!workspaces.contains(workplace)){
//            boolean createws = publisher.createWorkspace(workplace);
//            System.out.println("create ws : " + createws);
//        }else {
//            System.out.println("workspace已经存在了,ws :" + workplace);
//        }
//
//        //判断数据存储是否存在；
//        RESTDataStore restStore = manager.getReader().getDatastore(workplace, store_name);
//        if(restStore == null){
//            GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder("nyc_roads",new URL("E:\\zly\\geoserverdata\\nyc_roads\\nyc_roads.shp"));
//            boolean createStore = manager.getStoreManager().create(workplace, store);
//            System.out.println("create store : " + createStore);
//        } else {
//            System.out.println("数据存储已经存在了,store:" + store_name);
//        }
//        RESTLayer layer = manager.getReader().getLayer(workplace, layerName);
//        if(layer == null){
//            //发布图层
//            boolean publish = manager.getPublisher().publishShp(workplace, store_name, layerName, file, srs);
//            System.out.println("publish : " + publish);
////            GSFeatureTypeEncoder pds = new GSFeatureTypeEncoder();
////            pds.setTitle(layerName);
////            pds.setName(layerName);
////            pds.setSRS("EPSG:4326");
////            GSLayerEncoder layerEncoder = new GSLayerEncoder();
////
////            boolean publish = manager.getPublisher().publishDBLayer(workplace, store_name,  pds, layerEncoder);
////            System.out.println("publish : " + publish);
//        }else {
//            System.out.println("表已经发布过了,table:" + layerName);
//        }
//    }
    //发布shapefile数据
    public  void GeoserverPublishShapefileData(String url,String username,String passwd) throws IOException {

        String ws =  geoServerProperties.getWorkspacename();     //待创建和发布图层的工作区名称workspace
        String store_name = "zly" ; //待创建和发布图层的数据存储名称store
        String srs="EPSG:4326";
        //压缩文件的完整路径
        File zipFile=new File("E:\\zly\\geoserverdata\\nyc_roads.zip");
        String layername="nyc_roads";//图层名称
        //shp文件所在的位置
        String urlDatastore="file://E:\\zly\\geoserverdata\\nyc_roads\\nyc_roads.shp";
        //判断工作区（workspace）是否存在，不存在则创建
        URL u = new URL(url);
        //获取管理对象
        GeoServerRESTManager manager = new GeoServerRESTManager(u, username, passwd);
        //获取发布对象
        GeoServerRESTPublisher publisher = manager.getPublisher() ;
        //获取所有的工作空间名称
        List<String> workspaces = manager.getReader().getWorkspaceNames();
        //判断工作空间是否存在
        if(!workspaces.contains(ws)){
            //创建一个新的存储空间
            boolean createws = publisher.createWorkspace(ws);
            System.out.println("create ws : " + createws);
        }else {
            System.out.println("workspace已经存在了,ws :" + ws);
        }

        //判断数据存储（datastore）是否已经存在，不存在则创建
        URL urlShapefile = new URL(urlDatastore);
        RESTDataStore restStore = manager.getReader().getDatastore(ws, store_name);
        if(restStore == null){
            //创建shape文件存储
            GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder(store_name, urlShapefile);
            boolean createStore = manager.getStoreManager().create(ws, store);
            System.out.println("create store : " + createStore);
        } else {
            System.out.println("数据存储已经存在了,store:" + store_name);
        }

        //判断图层是否已经存在，不存在则创建并发布
        RESTLayer layer = manager.getReader().getLayer(ws, layername);
        if(layer == null){
            //发布图层
            boolean publish = manager.getPublisher().publishShp(ws, store_name, layername, zipFile, srs);
            System.out.println("publish : " + publish);
        }else {

            System.out.println("表已经发布过了,table:" + store_name);
        }

    }

}
