package clip.service;

import java.util.List;

import clip.model.ScriptBean;

public interface ScriptService {
	public List<ScriptBean> getScriptList(String clipNo);
	public ScriptBean getScript(String ScriptNo);
}
