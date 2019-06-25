package clip.repository;

import java.util.LinkedList;
import java.util.List;

import clip.model.ClipBean;

public interface ClipDao {
	public ClipBean getClip(String clipNo);
	
	public List<ClipBean> getAllClips();
}
