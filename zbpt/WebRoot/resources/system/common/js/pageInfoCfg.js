/**
 * 页面信息配置
 * 
 * @type
 */
var pageInfoCfg = {
	datagrid : {
		/**
		 * 数据加载提示
		 * 
		 * @type String
		 */
		loadMsg : {
			msg : '<span style="font-size:12px;">正在加载数据...</span>'
		},
		/**
		 * 全页面数据每页显示条数
		 * 
		 * @type Number
		 */
		pageSize : 20,
		/**
		 * 半页面数据每页显示条数
		 * 
		 * @type Number
		 */
		halfPageSize : 5,
		store : {
			reader : {
				root : 'list',
				totalProperty : 'totalProperty'
			}
		},
		tbar : {
			field : {
				width : 120
			},
			tbspacer : {
				width : 15
			}
		}
	},
	window : {
		/**
		 * 单行form弹窗宽度
		 * 
		 * @type Number
		 */
		singleColumnWidth : 500,
		/**
		 * 双行form弹窗宽度
		 * 
		 * @type Number
		 */
		doubleColumnWidth : 614
	},
	form : {
		doubleColumnLabelWidth : 90,
		submit : {
			waitTitle : '请稍等...',
			waitMsg : '正在提交信息...'
		}
	},
	body : {
		paddingTopButton : 10
	}
}