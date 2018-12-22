/**
 * 
 */
package com.surpass.mvc.core.bean;

/**
 * 类名称： TreeNodeState<br>
 * 类描述： <br>
 * 创建人： wangli<br>
 * 创建时间：2017-11-2<br>
 * 修改人： wangli<br>
 * 修改时间：2017-11-2
 */
public class TreeNodeState {
	private  boolean checked=false;
	private  boolean disabled=false;
	private  boolean expanded=false;
	private  boolean selected=false;
	/**  
	 * 获取 checked  
	 * @return checked checked  
	 */
	public boolean isChecked() {
		return checked;
	}
	/**  
	 * 设置 checked  
	 * @param checked checked  
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**  
	 * 获取 disabled  
	 * @return disabled disabled  
	 */
	public boolean isDisabled() {
		return disabled;
	}
	/**  
	 * 设置 disabled  
	 * @param disabled disabled  
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	/**  
	 * 获取 expanded  
	 * @return expanded expanded  
	 */
	public boolean isExpanded() {
		return expanded;
	}
	/**  
	 * 设置 expanded  
	 * @param expanded expanded  
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	/**  
	 * 获取 selected  
	 * @return selected selected  
	 */
	public boolean isSelected() {
		return selected;
	}
	/**  
	 * 设置 selected  
	 * @param selected selected  
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
