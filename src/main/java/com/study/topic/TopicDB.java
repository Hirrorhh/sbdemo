package com.study.topic;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDB {
    public static List<Topic> list(Connection conn, String cid, int topicType) throws SQLException {
        String sql = "SELECT id,company_id,title,answer,type,grouping,create_timestamp,create_id,update_timestamp,update_id,delete_timestamp,delete_id,grade"
                + " FROM topic"
                + " where type = " + topicType
                + " AND company_id =" + cid
                + " group by grouping"
                + " order by id";

        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        List<Topic> result = new ArrayList<Topic>();

        while (rst.next()) {
            Topic item = new Topic();

            item.id = rst.getInt(1);
            item.company_id = rst.getInt(2);
            item.title = rst.getString(3);
            item.answer = rst.getString(4);
            item.type = rst.getInt(5);

            item.grouping = rst.getInt(6);
            item.create_timestamp = rst.getDate(7);
            item.create_id = rst.getInt(8);
            item.update_timestamp = rst.getDate(9);
            item.update_id = rst.getInt(10);

            item.delete_timestamp = rst.getDate(11);
            item.delete_id = rst.getInt(12);
            item.grade = rst.getInt(13);

            result.add(item);
        }

        rst.close();
        stmt.close();

        return result;
    }

    public static List<Topic> getTopicItemlist(Connection conn, int grouping, int topicType, String cid) throws SQLException {
        String sql = "SELECT id,company_id,title,answer,type,grouping,create_timestamp,create_id,update_timestamp,update_id,delete_timestamp,delete_id,grade"
                + " FROM topic"
                + " where grouping = " + grouping
                + " and type = " + topicType
                + " AND company_id =" + cid
                + " order by id";

        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery(sql);

        List<Topic> result = new ArrayList<Topic>();

        while (rst.next()) {
            Topic item = new Topic();

            item.id = rst.getInt(1);
            item.company_id = rst.getInt(2);
            item.title = rst.getString(3);
            item.answer = rst.getString(4);
            item.type = rst.getInt(5);

            item.grouping = rst.getInt(6);
            item.create_timestamp = rst.getDate(7);
            item.create_id = rst.getInt(8);
            item.update_timestamp = rst.getDate(9);
            item.update_id = rst.getInt(10);

            item.delete_timestamp = rst.getDate(11);
            item.delete_id = rst.getInt(12);
            item.grade = rst.getInt(13);

            result.add(item);
        }

        rst.close();
        stmt.close();

        return result;
    }

    public static void add(Connection conn, YongHu yongHu) throws SQLException {
        String sql = "INSERT INTO zc_user (user_type, jigou_name, name, password, card_type, id_card, phone, company_id, deleted, create_timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, yongHu.user_type);
//		pstmt.setString(2, yongHu.jigou_name);
        pstmt.setString(3, yongHu.name);
        pstmt.setString(4, yongHu.password);
        pstmt.setString(5, yongHu.card_type);

        pstmt.setString(6, yongHu.id_card);
        pstmt.setString(7, yongHu.phone);

        pstmt.setInt(8, yongHu.company_id);
        pstmt.setInt(9, yongHu.deleted);
        pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

        pstmt.executeUpdate();
        conn.commit();
        pstmt.close();
    }


    public static List<TopicJiegou> getTopicList(Connection conn, String cid, int topicType, String grade) throws SQLException {
        ArrayList<TopicJiegou> topicJiegous = new ArrayList<>();
        List<TopicJiegou> result = topicJiegous;

        Statement stmt1 = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rst1 = null;
        ResultSet rst2 = null;

        String sql1 = "SELECT id,company_id,title,answer,type,grouping,create_timestamp,create_id,update_timestamp,update_id,delete_timestamp,delete_id,grade"
                + " FROM topic"
                + " where type = " + topicType + " and company_id = " + cid
                + " order by id";

        rst1 = stmt1.executeQuery(sql1);
        while (rst1.next()) {
            TopicJiegou item = new TopicJiegou();
            item.id = rst1.getInt(1);
            item.company_id = rst1.getInt(2);
            item.title = rst1.getString(3);
            item.answer = rst1.getString(4);
            item.type = rst1.getInt(5);
            item.grouping = rst1.getInt(6);
            item.create_timestamp = rst1.getDate(7);
            item.create_id = rst1.getInt(8);
            item.update_timestamp = rst1.getDate(9);
            item.update_id = rst1.getInt(10);
            item.delete_timestamp = rst1.getDate(11);
            item.delete_id = rst1.getInt(12);
            item.grade = rst1.getInt(13);


            String sql2 = "SELECT title FROM agreement_questionnaire"
                    + " WHERE type = " + rst1.getInt(5)
                    + " AND company_id = " + rst1.getInt(2)
                    + " AND score_min <= " + Integer.parseInt(grade)
                    + " AND score_max >= " + Integer.parseInt(grade);
            rst2 = stmt2.executeQuery(sql2);
            while (rst2.next()) {
                item.fengxiannengli = rst2.getString(1);
            }

            result.add(item);
        }
        rst2.close();
        stmt2.close();
        rst1.close();
        stmt1.close();

        return result;
    }


    public static void add_topic_jiegou(Connection conn, TopicJiegou topicJiegou, String uid, String userType, long ceshi_index_timeMillis, String grade, String answer_list) throws SQLException {
        String sql = "INSERT INTO topic_jieguo (company_id, title, answer, type, grouping, uid, userType, ceshi_index, ceshishijian, defen, fengxiannengli, create_timestamp, grade, answer_list) " +
                "VALUES (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, topicJiegou.company_id);
        pstmt.setString(2, topicJiegou.title);
        pstmt.setString(3, topicJiegou.answer);
        pstmt.setInt(4, topicJiegou.type);
        pstmt.setInt(5, topicJiegou.grouping);
        pstmt.setString(6, uid);
        pstmt.setString(7, userType);
        pstmt.setString(8, String.valueOf(ceshi_index_timeMillis));
        pstmt.setTimestamp(9, new Timestamp(ceshi_index_timeMillis));
        pstmt.setString(10, grade);
        pstmt.setString(11, topicJiegou.fengxiannengli);
        pstmt.setTimestamp(12,new Timestamp(ceshi_index_timeMillis));
        pstmt.setInt(13, topicJiegou.grade);
        pstmt.setString(14, answer_list);

        pstmt.executeUpdate();
        conn.commit();
        pstmt.close();
    }

    public static String getMaxCeshiIndex(Connection conn, String uid) throws SQLException {
        String sql = "select max(ceshi_index) from topic_jieguo where uid = " + uid;

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        String anInt = null;
        while (resultSet.next()) {
            anInt = resultSet.getString(1);

        }
        stmt.close();
        return anInt;
    }

    public static String getLastTestResult(Connection conn, String uid, String ceshi_index) throws SQLException {
        String sql = "select answer_list " +
                "from topic_jieguo " +
                "where uid = " + uid +
                " and ceshi_index = " + ceshi_index +
                " LIMIT 1 ";
        String result= null;
        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery(sql);


        while (rst.next()) {
           result = rst.getString(1);

        }

        rst.close();
        stmt.close();

        return result;
    }
}
