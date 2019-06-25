package post.service;

import java.util.List;

import post.model.RecordBean;
import register.model.MemberBean;

public interface RecordService {
	public List<RecordBean> getRecordList(String postNo);
	public int saveRecord(RecordBean rb);
	public int updateRecord(Integer postNo, Integer scriptNo, String recordPath, Integer memberNo);
	public int updateNotDoneRecord(Integer postNo,Integer memberNo2,Integer scriptNo,String recordPath);
	public RecordBean getRecordBean(Integer recordNo);
}
