package com.coocaa.demo.util;

import com.coocaa.demo.vo.RequestVo;
import com.csvreader.CsvReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameUtil {
    public static Map<String,String> nameMap;
    static String dinfile = "C:/Users/lxq/Desktop/nameDic.csv";
    public static int a = 1;


    public  Map<String,String> insertMap() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        String infile =path.getAbsolutePath()+"\\"+"nameDic.csv";
        nameMap = new HashMap<>();
        // 用来保存数据
        ArrayList<String[]> csvFileList = new ArrayList<String[]>();
        // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
        try {
            CsvReader reader = new CsvReader(dinfile, ',', Charset.forName("GBK"));
            while (reader.readRecord()) {
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            for (int i = 0; i < csvFileList.size(); i++) {
                if(!nameMap.containsKey(csvFileList.get(i)[0])){
                    nameMap.put(csvFileList.get(i)[0],csvFileList.get(i)[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameMap;
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }

    public RequestVo CtoEName(RequestVo requestVo){
        List<String>CName = requestVo.getAssignee();
        List<String>EName = new ArrayList<>();
        for (int i = 0; i <CName.size() ; i++) {
            if(NameUtil.nameMap.containsValue(CName.get(i))){
                for (String getKey:NameUtil.nameMap.keySet()){
                    if(NameUtil.nameMap.get(getKey).equals(CName.get(i))){
                       EName.add(getKey);
                    }
                }
            }else{
                EName.add(CName.get(i));
            }
        }
        requestVo.setAssignee(EName);
        return requestVo;
    }
    public List<String> EtoCName(List<String> ENameList){
        List<String> CNameList = new ArrayList<>();
        for (int i = 0; i < ENameList.size(); i++) {
            if(NameUtil.nameMap.containsKey(ENameList.get(i))){
                CNameList.add(NameUtil.nameMap.get(ENameList.get(i)));
            }else{
                CNameList.add(ENameList.get(i));
            }
        }
        return CNameList;
    }

}
