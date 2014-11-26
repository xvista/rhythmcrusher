package logic;

import java.util.List;

import ui.RenderObject;

public interface IRenderableHolder {
	
	public abstract List<IRenderable> getSortedRenderableObject();
	
}
