package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.CITYDEFMapper;
import com.fengzkframework.basic.dao.MALLDEFMapper;
import com.fengzkframework.basic.dao.vo.CITYDEF;
import com.fengzkframework.basic.dao.vo.MALLDEF;
import com.fengzkframework.basic.domain.OutMallData;
import com.fengzkframework.basic.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallDefServiceImpl {
    @Autowired
    MALLDEFMapper malldefMapper;
    @Autowired
    CITYDEFMapper citydefMapper;

   public MALLDEF selectBycode(Integer code)
   {
       return malldefMapper.selectBycode(code);


   }

    public List<OutMallData> selectByytandcity2(MALLDEF def)
    {
        List<OutMallData> olist=new ArrayList<OutMallData>();
        OutMallData outMallData=null;

        List<MALLDEF> list= malldefMapper.selectByytandcity2(def);
        for(MALLDEF item:list) {
            outMallData = new OutMallData();
            outMallData.setCityid(item.getCityid().toString());
            outMallData.setMallcode(item.getCode());
            outMallData.setMallname(item.getName());
            outMallData.setCityname(item.getCityname());
            if(item.getLongtitude()!=null)
            outMallData.setLongtitude(item.getLongtitude().toString());
            outMallData.setPhotourl(item.getPhotourl());
            if(item.getLatitude()!=null)
            outMallData.setLatitude(item.getLatitude().toString());
            olist.add(outMallData);
        }
        return  olist;
    }

    public List<OutMallData> selectByytandcity(MALLDEF def)
    {
        List<OutMallData> olist=new ArrayList<OutMallData>();
        OutMallData outMallData=null;
        List<MALLDEF> list= malldefMapper.selectByytandcity(def);
        if(list.size()>0)
        {
            for(MALLDEF item:list)
            {
                outMallData=new OutMallData();
                if(item.getCityid()!=null)
                {
                    String name=getcityname(Long.valueOf(item.getCityid()));
                    item.setCityname(name);
                }
                outMallData.setCityid(item.getCityid().toString());
                outMallData.setMallcode(item.getCode());
                outMallData.setMallname(item.getName());
                outMallData.setCityname(item.getCityname());
                olist.add(outMallData);
            }
        }
        return  olist;
    }
    public  String getcityname(Long cityid)
    {
        String name="";
        CITYDEF citydef=citydefMapper.selectByPrimaryKey(cityid);
        if(citydef.getFatherid()!=null)
        {
            name+=getcityname(citydef.getFatherid());
        }
        name+=citydef.getCityname();
        return  name;
    }
}
