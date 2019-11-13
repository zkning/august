package com.august;

import com.august.sharding.JDBCApplication;
import com.august.sharding.domain.Order;
import com.august.sharding.domain.Users;
import com.august.sharding.mapper.OrderMapper;
import com.august.sharding.mapper.UsersMapper;
import io.shardingsphere.core.routing.router.masterslave.MasterVisitedManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JDBCApplication.class)
public class AppTest {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UsersMapper usersMapper;

    @Test
    public void inserUsers() {
        Users users = new Users();
        users.setName("张三");
        users.setTime(new Date());
        usersMapper.insert(users);
    }

    @Test
    public void insertOrder() {
        for (int i = 0; i <= 10; i++) {
            Order order = new Order();
            order.setName(i + "同学");
            order.setTime(new Date());
            orderMapper.insert(order);
        }
    }

    /**
     1. 不是查询类型的语句，比如更新字段
     2. DML_FLAG变量为true的时候 HintManager hintManager = HintManager.getInstance(); hintManager.setMasterRouteOnly();
     3. 强制Hint方式走主库 MasterVisitedManager.setMasterVisited();
     **/
    @Test
    public void queryByUserId() {
        List<Order> list = orderMapper.findByUserId(1194190550462656513L);
//        List<Order> list = orderMapper.selectByUserId(1194190550462656513L);
        System.out.println(list.size());
    }
}
