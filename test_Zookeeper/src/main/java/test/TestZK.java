package test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZK {

    //zookeerper集群的ip和端口
    private  String connStr = "192.168.204.141:2181,192.168.204.142:2181,192.168.204.143:2181";

    //session 超时时间:时间不宜设置太小，因为zookeeper和加载集群环境会因为性能等原因而延迟略高，
    // 若设置太小，还没创建好客户端就创建节点，就会报错。
    //心急吃不了热豆腐
    private int sessionTimeOut = 40*1000;

    //zookeeper客户端对象
    private ZooKeeper zk;


    /*
    初始化对象：创建客户端对象
     */
    @Before
    public void init() throws IOException {
        zk = new ZooKeeper(connStr, sessionTimeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("得到监听反馈，进行业务处理");
            }
        });
    }

    /*
    创建节点
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        //acl:用于设置 当前节点的权限，通常使用OPEN_ACL_UNSAFE
        String s = zk.create("/lagou", "yelihao".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
    }

    @Test
    //获取节点上的值
    public void getNodeData() throws IOException, KeeperException, InterruptedException {
        byte[] data = zk.getData("/lagou", false, new Stat());
        String str = new String(data);
        System.out.println(str);
    }

    //修改节点
    @Test
    public void updateNodeData()throws  Exception{
        Stat stat = zk.setData("/lagou", "yelihao2".getBytes(), 0);
        System.out.println(stat);
    }

    //删除节点
    @Test
    public void delete() throws KeeperException, InterruptedException {
        zk.delete("/lagou",1);
    }

    //获取子节点
    @Test
    public void getChild() throws KeeperException, InterruptedException {
        List<String> list = zk.getChildren("/china", false);
        for (String s : list) {
            System.out.println(s);
        }
    }

    //监听跟节点下的变化
    @Test
    public void watchNode() throws Exception{
        List<String> list = zk.getChildren("/", true);
        for (String s : list) {
            System.out.println(s);
        }
        //让线程无限等待
        System.in.read();
    }

    //判断节点是否存在
    @Test
    public void judgeNode() throws Exception{
        Stat lagou = zk.exists("/lagou", false);
        if (lagou==null){
            System.out.println("不存在");
        }else {
            System.out.println("存在");
        }

    }





}
