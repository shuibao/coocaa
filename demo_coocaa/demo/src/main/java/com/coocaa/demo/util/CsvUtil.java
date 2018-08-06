package com.coocaa.demo.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CsvUtil {
    public static void main(String[] arg){
        String infile = "C:/Users/lxq/Desktop/coocaa/demo_res.csv";
        String outfile = "C:/Users/lxq/Desktop/coocaa/demo_res1.csv";
        String notusedfile = "C:/Users/lxq/Desktop/coocaa/notused.csv";
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(infile, ',', Charset.forName("GBK"));
            // 跳过表头 如果需要表头的话，这句可以忽略

            int indexs[] = new int[27];
            for(int i = 0; i < indexs.length; i++) {
                indexs[i] = -1;
            }

            Boolean ret = reader.readHeaders();

            if(ret) {
                String headers[] = reader.getHeaders();
                //System.out.println(headers[22]+" "+headers[23]+" "+headers[24]);
                for(int i = 0; i < headers.length; i++)
                {
                    //System.out.println(headers[i]);
                    if(headers[i].toLowerCase().contains("summary")) {
                        indexs[0] = i;
                    }

                    if(headers[i].toLowerCase().contains("issue key")) {
                        indexs[1] = i;
                    }

                    if(headers[i].toLowerCase().contains("issue id")) {
                        indexs[2] = i;
                    }

                    if(headers[i].toLowerCase().contains("issue type")) {
                        indexs[3] = i;
                    }

                    if(headers[i].toLowerCase().equals("status")) {
                        indexs[4] = i;
                    }

                    if(headers[i].toLowerCase().contains("project key")) {
                        indexs[5] = i;
                    }

                    if(headers[i].toLowerCase().contains("project name")) {
                        indexs[6] = i;
                    }

                    if(headers[i].toLowerCase().contains("project type")) {
                        indexs[7] = i;
                    }

                    if(headers[i].toLowerCase().contains("project lead")) {
                        indexs[8] = i;
                    }

                    if(headers[i].toLowerCase().contains("priority")) {
                        indexs[9] = i;
                    }

                    if(headers[i].toLowerCase().contains("resolution")) {
                        indexs[10] = i;
                    }

                    if(headers[i].toLowerCase().contains("assignee")) {
                        indexs[11] = i;
                    }

                    if(headers[i].toLowerCase().contains("reporter")) {
                        indexs[12] = i;
                    }

                    if(headers[i].toLowerCase().contains("creator")) {
                        indexs[13] = i;
                    }

                    if(headers[i].toLowerCase().contains("created")) {
                        indexs[14] = i;
                    }

                    if(headers[i].toLowerCase().contains("updated")) {
                        indexs[15] = i;
                    }

                    if(headers[i].toLowerCase().contains("last viewed")) {
                        indexs[16] = i;
                    }

                    if(headers[i].toLowerCase().contains("resolved")) {
                        indexs[17] = i;
                    }

                    if(headers[i].toLowerCase().equals("original estimate")) {
                        indexs[18] = i;
                    }

                    if(headers[i].toLowerCase().equals("remaining estimate")) {
                        indexs[19] = i;
                    }

                    if(headers[i].toLowerCase().contains("time spent")) {
                        indexs[20] = i;
                    }

                    if(headers[i].toLowerCase().contains("work ratio")) {
                        indexs[21] = i;
                    }

                    if(headers[i].equals("Σ Original Estimate")) {
                        indexs[22] = i;
                    }

                    if(headers[i].equals("Σ Remaining Estimate")) {
                        indexs[23] = i;
                    }

                    if(headers[i].equals("Σ Time Spent")) {
                        indexs[24] = i;
                    }

                    if(headers[i].toLowerCase().contains("sprint")) {
                        indexs[25] = i;
                    }

                    if(headers[i].toLowerCase().contains("story points")) {
                        indexs[26] = i;
                    }
                }
            }

            System.out.println("check if we get correct indexs");
            for(int i = 0; i < indexs.length; i++) {
                if(indexs[i] == -1) {
                    System.out.println(i+" Σ");
                }
            }
            System.out.println("check completed");

            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }


            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter outFileWriter = new CsvWriter(outfile, ',', Charset.forName("GBK"));

            CsvWriter notUsedWriter = new CsvWriter(notusedfile, ',', Charset.forName("GBK"));
            // 写表头
            //String[] csvHeaders = { "编号", "姓名", "年龄" };
            notUsedWriter.writeRecord(reader.getHeaders());
            reader.close();
            // 写内容
            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                //0,1,2,4,5,6,7,8,9,12,13,14,15,16,17,18,19,20,29,30,31,32,33,34,35,44,45
                //String cell = csvFileList.get(row)[0];
                //System.out.println("------------>"+cell);
                if(csvFileList.get(row)[indexs[10]].equals("已解决")){
                    csvFileList.get(row)[indexs[10]]="1";
                }else if(csvFileList.get(row)[indexs[10]].equals("未解决")||csvFileList.get(row)[indexs[10]].equals("")){
                    csvFileList.get(row)[indexs[10]]="0";
                }else{
                    csvFileList.get(row)[indexs[10]]="-1";
                }
                String[] csvContent = {csvFileList.get(row)[indexs[0]], csvFileList.get(row)[indexs[1]], csvFileList.get(row)[indexs[2]], csvFileList.get(row)[indexs[3]], csvFileList.get(row)[indexs[4]],
                        csvFileList.get(row)[indexs[5]], csvFileList.get(row)[indexs[6]], csvFileList.get(row)[indexs[7]], csvFileList.get(row)[indexs[8]], csvFileList.get(row)[indexs[9]],
                        csvFileList.get(row)[indexs[10]], csvFileList.get(row)[indexs[11]], csvFileList.get(row)[indexs[12]], csvFileList.get(row)[indexs[13]], csvFileList.get(row)[indexs[14]],
                        csvFileList.get(row)[indexs[15]], csvFileList.get(row)[indexs[16]], csvFileList.get(row)[indexs[17]], csvFileList.get(row)[indexs[18]], csvFileList.get(row)[indexs[19]],
                        csvFileList.get(row)[indexs[20]], csvFileList.get(row)[indexs[21]], csvFileList.get(row)[indexs[22]], csvFileList.get(row)[indexs[23]], csvFileList.get(row)[indexs[24]], csvFileList.get(row)[indexs[25]],
                        csvFileList.get(row)[indexs[26]]};

                if(csvFileList.get(row)[indexs[0]].equals("")|| csvFileList.get(row)[indexs[1]].equals("")|| csvFileList.get(row)[indexs[2]].equals("")|| csvFileList.get(row)[indexs[3]].equals("")|| csvFileList.get(row)[indexs[4]].equals("")||
                        csvFileList.get(row)[indexs[5]].equals("")|| csvFileList.get(row)[indexs[6]].equals("")|| csvFileList.get(row)[indexs[7]].equals("")|| csvFileList.get(row)[indexs[8]].equals("")|| csvFileList.get(row)[indexs[9]].equals("")||
                        csvFileList.get(row)[indexs[11]].equals("")|| csvFileList.get(row)[indexs[12]].equals("")|| csvFileList.get(row)[indexs[13]].equals("")|| csvFileList.get(row)[indexs[14]].equals("")||
                        csvFileList.get(row)[indexs[15]].equals("")|| csvFileList.get(row)[indexs[16]].equals("")|| csvFileList.get(row)[indexs[17]].equals("")|| csvFileList.get(row)[indexs[18]].equals("")|| csvFileList.get(row)[indexs[19]].equals("")||
                        csvFileList.get(row)[indexs[20]].equals("")|| csvFileList.get(row)[indexs[21]].equals("")|| csvFileList.get(row)[indexs[22]].equals("")|| csvFileList.get(row)[indexs[23]].equals("")|| csvFileList.get(row)[indexs[24]].equals("")|| csvFileList.get(row)[indexs[25]].equals("")|| csvFileList.get(row)[indexs[26]].equals("")) {
                    notUsedWriter.writeRecord(csvContent);
                }else{
                    outFileWriter.writeRecord(csvContent);
                }
            }

            outFileWriter.close();
            System.out.println("--------CSV文件已经写入--------");
            System.out.println("--------展示一个样例--------");
            int row = 0;

            System.out.printf("Summary : %s %n", csvFileList.get(row)[indexs[0]]);
            System.out.printf("IssueKey : %s %n", csvFileList.get(row)[indexs[1]]);
            System.out.printf("IssueID : %s %n", csvFileList.get(row)[indexs[2]]);
            System.out.printf("IssueType : %s %n", csvFileList.get(row)[indexs[3]]);
            System.out.printf("Status : %s %n", csvFileList.get(row)[indexs[4]]);
            System.out.printf("ProjectKey : %s %n", csvFileList.get(row)[indexs[5]]);
            System.out.printf("ProjectName : %s %n", csvFileList.get(row)[indexs[6]]);
            System.out.printf("ProjectType : %s %n", csvFileList.get(row)[indexs[7]]);
            System.out.printf("ProjectLead : %s %n", csvFileList.get(row)[indexs[8]]);
            System.out.printf("Priority : %s %n", csvFileList.get(row)[indexs[9]]);
            System.out.printf("Resolution : %s %n", csvFileList.get(row)[indexs[10]]);
            System.out.printf("Assignee : %s %n", csvFileList.get(row)[indexs[11]]);
            System.out.printf("Reporter : %s %n", csvFileList.get(row)[indexs[12]]);
            System.out.printf("Creator : %s %n", csvFileList.get(row)[indexs[13]]);
            System.out.printf("Created : %s %n", csvFileList.get(row)[indexs[14]]);
            System.out.printf("Updated : %s %n", csvFileList.get(row)[indexs[15]]);
            System.out.printf("LastViewed : %s %n", csvFileList.get(row)[indexs[16]]);
            System.out.printf("Resolved : %s %n", csvFileList.get(row)[indexs[17]]);
            System.out.printf("OriginalEstimate : %s %n", csvFileList.get(row)[indexs[18]]);
            System.out.printf("RemainingEstimate : %s %n", csvFileList.get(row)[indexs[19]]);
            System.out.printf("TimeSpent : %s %n", csvFileList.get(row)[indexs[20]]);
            System.out.printf("WorkRatio : %s %n", csvFileList.get(row)[indexs[21]]);
            System.out.printf("AllOriginalEstimate : %s %n", csvFileList.get(row)[indexs[22]]);
            System.out.printf("AllRemainingEstimate : %s %n", csvFileList.get(row)[indexs[23]]);
            System.out.printf("AllTimeSpent : %s %n", csvFileList.get(row)[indexs[24]]);
            System.out.printf("Sprint : %s %n", csvFileList.get(row)[indexs[25]]);
            System.out.printf("StroyPoint : %s %n", csvFileList.get(row)[indexs[26]]);
            System.out.println("--------All Done--------");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
