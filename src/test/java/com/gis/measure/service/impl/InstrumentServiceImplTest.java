package com.gis.measure.service.impl;

import com.gis.measure.dataobject.Instrument;
import com.gis.measure.service.InstrumentService;
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
public class InstrumentServiceImplTest {
    private String instrumentId = "DL20180928";

    @Autowired
    private InstrumentService instrumentService;

    @Test
    public void findOne() {
        Instrument instrument = instrumentService.findOne(instrumentId);
        Assert.assertNotNull(instrument);
    }

   /* @Test
    public void findAll() {
        List<Instrument> result = instrumentService.findAll();
        Assert.assertEquals(3, result.size());
    }*/

    @Test
    public void delete() {
        instrumentService.delete(instrumentId);
    }

    @Test
    public void deleteAllByName() {
        instrumentService.deleteAllByName("全站仪");
    }

    @Test
    public void create() {
        Instrument instrument = new Instrument();
        instrument.setInstrumentId(instrumentId);
        instrument.setInstrumentName("大棱镜");
        Assert.assertNotNull(instrumentService.create(instrument));
    }

    @Test
    public void findByInstrumentName(){
        List<Instrument> result = instrumentService.findAllByInstrumentName("全站仪");
        Assert.assertEquals(2, result.size());
    }
}