import netty.server.DouyuDanmuClient;

/**
 * @Author: yuhl
 * @Date: 2018/8/16 13:38
 * @Description:
 */
public class DouyuDanmuApplication {
    private static final String[] roomIds={"9999","110","208114"};
    public static void main(String args[]) throws Exception{
        for(String roomId:roomIds){
            Thread thread=new Thread(new StartCatchDouyuDanmu(new DouyuDanmuClient(roomId)));
            thread.start();
        }
    }
    static class StartCatchDouyuDanmu implements Runnable {
        private DouyuDanmuClient douyuDanmuClient;

        public StartCatchDouyuDanmu(DouyuDanmuClient douyuDanmuClient) {
            this.douyuDanmuClient = douyuDanmuClient;
        }

        public void run() {
            try {
                douyuDanmuClient.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

