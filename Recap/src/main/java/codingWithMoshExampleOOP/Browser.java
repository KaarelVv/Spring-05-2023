package codingWithMoshExampleOOP;

public class Browser {

    public void navigate(String address){
        String ip = findIpAddress(address,false);
        String html = sendHttpRequest(ip);
        System.out.println(html);
    }

    private String sendHttpRequest(String ip) { //we wanna hide those methods thats why we made them private ie implication details
        return "<html></html>";
    }

    private String findIpAddress(String address,boolean cache) {  //we wanna hide those methods thats why we made them private ie implication details
        return "127.0.0.1";
    }
}
