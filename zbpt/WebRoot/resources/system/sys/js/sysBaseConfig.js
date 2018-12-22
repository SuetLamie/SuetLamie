/**
 * 
 * 函数名称：Role<br>
 * 函数说明: <br>
 * 创建人: 方曙强<br>
 * 创建日期: 2016-3-29<br>
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {Role}
 */

function SysBaseConfig() {

	var panel = this;

	this.form = new Ext.form.FormPanel({
		border : false,
		anchor:'100% 100%',
		buttonAlign : 'center',
		frame : false,
		bodyStyle : 'padding:5px;',
		labelAlign : 'right',
		autoHeight : true,
		labelWidth : 130,
		items : [ 
		new Ext.form.FieldSet({
			title : '基本配置信息',
			autoHeight : true,
			border : true,
			layout : "column",
			anchor : '100%',
			defaults : {
				columnWidth : 1,
				border : false,
				defaults : {
					anchor : '100%'
				}
			},
			items : [ {
				layout : 'form',
				items : [ {
					xtype : 'textfield',
					fieldLabel : '<font color=#467CD5>数据库地址</font>',
					name : 'jdbc_url',
					allowBlank : false,
					blankText : '数据库地址不能为空!',
					maxLength : 200,
					maxLengthText : '数据库地址不能超过200个字符!',
					listeners : {
						scope : this
					}
				} ]
			}, {
				layout : 'form',
				items : [ {
					xtype : 'textfield',
					fieldLabel : '<font color=#467CD5>用户名</font>',
					name : 'jdbc_username',
					allowBlank : false,
					blankText : '用户名不能为空!',
					maxLength : 20,
					maxLengthText : '用户名不能超过20个字符!',
					listeners : {
						scope : this
					}
				} ]
			}, {
				layout : 'form',
				items : [ {
					xtype : 'textfield',
					fieldLabel : '<font color=#467CD5>密码</font>',
					name : 'jdbc_password',
					allowBlank : false,
					blankText : '密码不能为空!',
					maxLength : 120,
					maxLengthText : '密码不能超过120个字符!',
					listeners : {
						scope : this
					}
				} ]
			} ]
		}) ],
		buttons : [ {
			text : '保存',
			handler : function() {
				if (panel.form.getForm().isValid() == true) {
					panel.form.getForm().submit({
						url : 'system/sys/sysBaseConfigController/saveSysBaseConfig.action',
						waitTitle : '请稍等...',
						waitMsg : '正在提交信息...',
						method : 'post',
						params : {
							jsonData : Ext.encode(panel.form.getForm().getValues())
						},
						success : function(form, action) {
							var data = eval(action.result);
							if (data.execute) {

								Ext.Msg.alert('温馨提示', data.msg);

								// 获取数据
								var sbcData = PassOwner.RequestOfPost('system/sys/sysBaseConfigController/getSysBaseConfig.action');
								// 加载数据
								var ss = new Ext.data.Record(sbcData);
								panel.form.getForm().loadRecord(ss);
							} else {
								msgShow(data.msg);
							}
						},
						failure : function(form, action) {
							msgShow('数据操作失败!');
						}
					});
				}

			}
		} ]
	});

	// 初始化数据方法
	this.infoData = function() {
		// 按钮 权限 控制
		buttonPowerFn("1001_104_101", panel.form.buttons[0]);

		// 获取数据
		var sbcData = PassOwner.RequestOfPost('system/sys/sysBaseConfigController/getSysBaseConfig.action');
		// 显示form
		panel.form.show();
		// 加载数据
		var ss = new Ext.data.Record(sbcData);
		panel.form.getForm().loadRecord(ss);
	}
	// 初始化
	panel.infoData();
}