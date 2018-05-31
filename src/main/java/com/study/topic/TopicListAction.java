package com.study.topic;


import com.study.pdf.getTopicAndAnswer;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicListAction {

	public static Map<String, Object> execute() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		String cid = "2";
		int topicType = 1;
		String uid = "1212";

		String answer =null;
		Connection conn =null;
		//连接数据库
		try{
			 conn = getTopicAndAnswer.getConnection1();

			String ceshiIndex = TopicDB.getMaxCeshiIndex(conn, uid);

				answer= TopicDB.getLastTestResult(conn, uid, ceshiIndex);

				List<Topic> topicList = TopicDB.list(conn, cid, topicType);

				JsonArray itemList = new JsonArray();

				for (Topic topic : topicList) {
					JsonObject item = new JsonObject();

					item.set("id", topic.id);
					item.set("company_id", topic.company_id);
					item.set("title", topic.title);
					//item.set("answer", topic.answer);
					item.set("type", topic.type);

					item.set("grouping", topic.grouping);
					item.set("create_timestamp", topic.create_timestamp);
					item.set("create_id", topic.create_id);
					item.set("update_timestamp", topic.update_timestamp);
					item.set("update_id", topic.update_id);

					item.set("delete_timestamp", topic.delete_timestamp);
					item.set("delete_id", topic.delete_id);
					item.set("grade", topic.grade);

					List<Topic> topicItemList = TopicDB.getTopicItemlist(conn, topic.grouping, topicType, cid);
					JsonArray answersList = new JsonArray();
					for (Topic answers : topicItemList) {
						JsonObject topicItem = new JsonObject();

						topicItem.set("id", answers.id);
						topicItem.set("company_id", answers.company_id);
						topicItem.set("title", answers.title);
						topicItem.set("answer", answers.answer);
						topicItem.set("type", answers.type);

						topicItem.set("grouping", answers.grouping);
						topicItem.set("create_timestamp", answers.create_timestamp);
						topicItem.set("create_id", answers.create_id);
						topicItem.set("update_timestamp", answers.update_timestamp);
						topicItem.set("update_id", answers.update_id);

						topicItem.set("delete_timestamp", answers.delete_timestamp);
						topicItem.set("delete_id", answers.delete_id);
						topicItem.set("grade", answers.grade);
						answersList.add(topicItem);

					}
					item.set("answersList", answersList);

					itemList.add(item);
				}
				result.put("topiclist", itemList);

				result.put("result", "OK");

			result.put("answer", answer);
		}
		catch(Exception e) {
			e.printStackTrace();
			result.put("result", "NG");
			result.put("message", e.getMessage());
		}finally {
			conn.close();
		}
		
		return result;
	}

}
