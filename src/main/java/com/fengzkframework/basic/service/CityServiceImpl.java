package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.CITYDEFMapper;
import com.fengzkframework.basic.dao.vo.CITYDEF;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CityServiceImpl {
    Logger logger= LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    CITYDEFMapper mapper;
    public ResultData GetallCity()
    {
        List<CITYDEF> list=mapper.selectall();
        List<citycjdata> rlist=new ArrayList<citycjdata>();
        citycjdata data=null;

        Map<String, List<CITYDEF>> groupdata=groupdata(list);
        for (Map.Entry<String, List<CITYDEF>> entry : groupdata.entrySet()) {

            data=new citycjdata();
            data.setCj(entry.getKey());
            data.setDatalist(entry.getValue());
            rlist.add(data);
        }

        return ResultUtil.success(rlist);
    }

    private Map<String, List<CITYDEF>> groupdata(List<CITYDEF> billingList) {
        Map<String, List<CITYDEF>> resultMap = new HashMap<String, List<CITYDEF>>();

        try{
            for(CITYDEF tmExcpNew : billingList){

                if(resultMap.containsKey(tmExcpNew.getCj())){
                    resultMap.get(tmExcpNew.getCj()).add(tmExcpNew);
                }else{//map中不存在，新建key，用来存放数据
                    List<CITYDEF> tmpList = new ArrayList<CITYDEF>();
                    tmpList.add(tmExcpNew);
                    resultMap.put(tmExcpNew.getCj(), tmpList);

                }
            }

        }catch(Exception e){
            logger.error(e.getMessage());
            //throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
        }

        return resultMap;
    }

}
 class citycjdata
{
    private String cj;

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    public List<CITYDEF> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<CITYDEF> datalist) {
        this.datalist = datalist;
    }

    private List<CITYDEF> datalist;
}
