package meituan;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ShopServer {


    //zookeerper集群的ip和端口
    private  String connStr = "192.168.204.141:2181,192.168.204.142:2181,192.168.204.143:2181";

    //session 超时时间:时间不宜设置太小，因为zookeeper和加载集群环境会因为性能等原因而延迟略高，
    // 若设置太小，还没创建好客户端就创建节点，就会报错。
    //心急吃不了热豆腐
    private int sessionTimeOut = 60*1000;

    //zookeeper客户端对象
    private ZooKeeper zk;

    //连接zk
    public void connect()throws Exception{
        zk = new ZooKeeper(connStr, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    //注册到zookeeper
    public void register(String shopname)throws Exception{
        String s = zk.create("/meituan/shop", shopname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("["+shopname+"]开始营业"+s);
    }




    public static void main(String[] args) throws Exception{


        //1.连接zookeeper集群(),商家和美团取得联系
        ShopServer shop = new ShopServer();
        shop.connect();

        //2将服务节点注册到zookeeper，入驻美团
        shop.register(args[0]);

        //3.业务逻辑处理（做生意）
        shop.business(args[0]);


    }

    private void business(String arg) throws IOException {
        System.out.println("["+arg+"]正在营业中");
        System.in.read();
    }


}
