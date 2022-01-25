package woons.demo2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import woons.demo2.DBrepo;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
public class Test {

    private final DataSource dataSource ;

    public Test(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping(value = "/host")
    public HashMap<String, Object> printhost() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            String hostadd = InetAddress.getLocalHost().getHostAddress();
            map.put("tomhostname", hostname);
            map.put("tomhostaddress", hostadd);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DBrepo dBrepo = new DBrepo(dataSource);
        dBrepo.insert();
        List<Integer> lst = null;
        try {
            lst = dBrepo.find();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        map.put("DB", lst);
        return map;
    }
}
