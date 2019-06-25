package clip.service;

import java.util.LinkedList;
import java.util.List;

import clip.model.ClipBean;

public interface ClipService {
	public ClipBean getClip(String clipNo);
	
	public List<ClipBean> getAllClips();
}
