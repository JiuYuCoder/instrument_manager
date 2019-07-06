package com.gis.measure.service.impl;

import com.gis.measure.dataobject.BorrowRecord;
import com.gis.measure.enums.ConfirmStatusEnum;
import com.gis.measure.service.BorrowRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowRecordServiceImplTest {

    @Autowired
    private BorrowRecordService borrowRecordService;
    @Test
    public void create() {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setStudentId("1701400028");
        borrowRecord.setInstrumentId("XZ20180927");
        BorrowRecord result = borrowRecordService.create(borrowRecord);
        Assert.assertNotNull(result);
    }

    @Test
    public void update() {
        String instrumentId = "QZ20180928";
        borrowRecordService.update(instrumentId, ConfirmStatusEnum.NOT_CONFIRM.getCode());
    }

    @Test
    public void findAllByIsConfirm() {
        List<BorrowRecord> result = borrowRecordService.findAllByIsConfirm(ConfirmStatusEnum.CONFIRMED.getCode());
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void findAllByStudentId() {
        List<BorrowRecord> result = borrowRecordService.findAllByStudentId("1701400028");
        Assert.assertEquals(6, result.size());
    }
}