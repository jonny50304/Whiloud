package clip.repository;

import java.util.List;

import clip.model.ScriptBean;

public interface ScriptDao {
	public List<ScriptBean> getScriptList(String clipNo);
	public ScriptBean getScript(String scriptNo);
}
