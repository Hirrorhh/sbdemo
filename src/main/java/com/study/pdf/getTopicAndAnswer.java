package com.study.pdf;/**
 * Created by Hirror on 2018/5/31.
 */

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;


/**
 * @author jf_hirror
 * @version basic 1.0 2018-05-31
 * @create 2018-05-31 13:47
 * @desc 获取问卷的answer
 **/
public class getTopicAndAnswer {
    protected static BasicDataSource datasource1 = null;
    protected static BasicDataSource datasource2 = null;
    protected static boolean useDB2 = false;
    protected static long useDB2StartTimestamp = 0;
    public static Connection getConnection1() throws Exception
    {
        if (datasource1 == null) {
            //ResourceBundle resource = ResourceBundle.getBundle("Application");

            datasource1 = new BasicDataSource();

            datasource1.setDriverClassName("com.mysql.jdbc.Driver");
            datasource1.setUrl("jdbc:mysql://localhost:3306/sdxglpg?characterEncoding=utf-8&useUnicode=true");
            datasource1.setUsername("root");
            datasource1.setPassword("123456");
            datasource1.setMaxActive(10);
            datasource1.setMaxWait(500);
            datasource1.setDefaultAutoCommit(false);
            datasource1.setDefaultReadOnly(false);
            datasource1.setValidationQuery("SELECT 1");
        }

        return datasource1.getConnection();
    }
    public static void getTopic(){

    }

}
