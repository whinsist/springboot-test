package com.zimug.bootlaunch.service.impl;

import com.zimug.bootlaunch.generator.testdb.GeneInfoPOMapper;
import com.zimug.bootlaunch.model.GeneInfoPO;
import com.zimug.bootlaunch.service.GeneInfoService;
import com.zimug.bootlaunch.utils.BasicWebUtil;
import com.zimug.bootlaunch.utils.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Hong.Wu
 * @date: 0:16 2021/02/17
 */
@Service
public class GeneInfoServiceImpl implements GeneInfoService {

    @Autowired
    private GeneInfoPOMapper geneInfoPOMapper;

    @Override
    public void testGeneMethod() {
        GeneInfoPO geneInfoPO = new GeneInfoPO();
        geneInfoPO.setName("test");
        geneInfoPO.setAge((byte) 33);
        geneInfoPO.setSalary(new BigDecimal("12.34"));
        geneInfoPO.setSubmissionDate(new Date());
        //geneInfoPO.setCreateTimestamp(new Date());
        geneInfoPO.setBirthdayDatetime(new Date());
        geneInfoPO.setOrgSid(1L);
        geneInfoPO.setCreatedBy("test");
        geneInfoPO.setCreatedDt(new Date());
        geneInfoPO.setUpdatedBy("test");
        geneInfoPO.setUpdatedDt(new Date());
        geneInfoPOMapper.insertSelective(geneInfoPO);
        System.out.println(geneInfoPO);

        GeneInfoPO request = new GeneInfoPO();
        request.setId(geneInfoPO.getId());

        Criteria criteria = new Criteria();
        criteria.setConditionObject(request);
        BasicWebUtil.preparePageParams(request, criteria, "id desc");

        List<GeneInfoPO> geneInfoPOS = geneInfoPOMapper.selectByParams(criteria);
        System.out.println(geneInfoPOS);


    }


    public static byte[] toBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)number;
        bytes[1] = (byte) (number >> 8);
        bytes[2] = (byte) (number >> 16);
        bytes[3] = (byte) (number >> 24);
        return bytes;
    }
    public static int toInt(byte[] bytes){
        int number = 0;
        for(int i = 0; i < 4 ; i++){
            number += bytes[i] << i*8;
        }
        return number;
    }
}
