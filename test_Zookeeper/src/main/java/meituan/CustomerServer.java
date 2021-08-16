package meituan;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

public class CustomerServer {

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
                //再次获取商家列表
                try {
                    getShopList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception{
        CustomerServer cs = new CustomerServer();

        cs.connect();

        //获取子节点
        cs.getShopList();

        //做生意
        cs.bussiness();


    }

    private void bussiness()throws Exception {
        System.out.println("用户正在浏览商家");
        System.in.read();
    }

    private void getShopList() throws Exception{
        List<String> shops = zk.getChildren("/meituan", true);
        ArrayList<String> shoplist = new ArrayList<>();
        for (String shop : shops) {
            byte[] data = zk.getData("/meituan/" + shop, false, new Stat());
            shoplist.add(new String((data)));
        }
        System.out.println("目前正在营业商家:"+shoplist);
    }


}
