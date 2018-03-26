package com.sun.task;

import com.sun.task.dao.OpenIdMapingDao;
import com.sun.task.dto.OpenIdMaping;
import com.sun.task.service.TransferOpenIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengjikun on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TransferOpenIdServiceTest {

    @Autowired
    private TransferOpenIdService transferOpenIdService;
    @Autowired
    private OpenIdMapingDao openIdMapingDao;


    @Test
    public void transferFansOpenId(){
        transferOpenIdService.transferFansOpenId(0, 10);
    }

    @Test
    public void batchInsert(){
        List<OpenIdMaping> openIdMapings = new ArrayList<>();
        OpenIdMaping openIdMaping;
        for (int i = 1; i <= 10; i++) {
            openIdMaping = OpenIdMaping.builder()
                    .oldOpenId("old"+i)
                    .newOpenId("new"+i)
                    .mobile("187"+i)
                    .No(i)
                    .build();
            openIdMapings.add(openIdMaping);
        }
        openIdMapingDao.batchInsert(openIdMapings);

    }


    @Test
    public void transferKfOpenId(){
        transferOpenIdService.transferKfOpenId();
    }

}
