package com.surpass.utils;

public class Constants {
	private Constants() {
	}

	/**
	 * 
	 * 类名称： session<br>
	 * 类描述：存在session里的对象名 <br>
	 * 创建人： 方曙强<br>
	 * 创建时间：Oct 12, 2013<br>
	 * 修改人： 方曙强<br>
	 * 修改时间：Oct 12, 2013
	 */
	public static final class session {
		/**
		 * 记录登录用户的常量
		 */
		public static final String LOGIN_USER = "LOGINUSER";
	}
	/**
	 * 
	 * 类名称： systemSet<br>
	 * 类描述：系统设置标签 value值必须与数据库编号一致 <br>
	 * 创建人： 何扬<br>
	 * 修改人：  何扬<br>
	 * 修改时间：2018-10-25
	 */
	public static final class systemSet {
		/**
		 * 会员等级
		 */
		public static final String Level = "1";
		/**
		 * 签到积分
		 */
		public static final String QDJF = "QDJF";
		/**
		 * 分享积分
		 */
		public static final String FXJF = "FXJF";
		/**
		 * 课程返佣
		 */
		public static final String KCFY = "KCFY";
		/**
		 * 产品返佣
		 */
		public static final String CPFY = "CPFY";
		/**
		 * 推荐返佣
		 */
		public static final String TJFY = "TJFY";
		/**
		 * 课程类型
		 */
		public static final String CourseType = "1";
		/**
		 * 商品类型
		 */
		public static final String GoodsType = "2";
		/**
		 * 充值类型
		 */
		public static final String RechargeType = "3";
		/**
		 * 平台收入 购物类型
		 */
		public static final String AdminIncomeShoppingType = "1";
		/**
		 * 平台收入 课程类型
		 */
		public static final String AdminIncomeCourseType = "2";
		/**
		 * 平台收入 教师到期类型
		 */
		public static final String AdminIncomeTeacherEndType = "3";
		/**
		 * 平台收入 代理到期类型
		 */
		public static final String AdminIncomeAgentEndType = "4";
	}
	/**
	 * 
	 * 类名称： orderSet<br>
	 * 类描述：订单状态标签 value值必须与数据库编号一致 <br>
	 * 创建人： 何扬<br>
	 * 修改人：  何扬<br>
	 * 修改时间：2018-10-25
	 */
	public static final class orderSet {
		/**
		 * 订单支付状态 已支付
		 */
		public static final String EndPay="1";
		/**
		 * 订单支付状态 未支付
		 */
		public static final String Paying="2";
		/**
		 * 订单发货状态 未收货
		 */
		public static final String UnDelivery="0";
		/**
		 * 订单发货状态 已收货
		 */
		public static final String EndDelivery="1";
		/**
		 * 订单发货状态 送货中
		 */
		public static final String Deliveriing="3";
		/**
		 * 交易状态 交易中
		 */
		public static final String Tradeing="0";
		/**
		 * 交易状态 已完成
		 */
		public static final String EndTrade="3";
		/**
		 * 交易状态 已取消
		 */
		public static final String CancelTrade="3";
	}
	/**
	 * 
	 * 类名称： userType<br>
	 * 类描述： 用户类型<br>
	 * 创建人： 方曙强<br>
	 * 创建时间：2016-12-12<br>
	 * 修改人： 方曙强<br>
	 * 修改时间：2016-12-12
	 */
	public static final class userType {
		/**
		 * 系统用户
		 */
		public static final int sysUser = 1;
	}

	public static final class path {
		/** 路径前缀：jsp页面 */
		public static final String RETURN_PATH_JSP = "/WEB-INF/jsp/";

		/** 路径前缀：配置文件路径 */
		public static final String RETURN_PATH_CONF = "/WEB-INF/classes/conf/";

		/** 后台管理系统页面路径 */
		public static final String RETURN_PATH_SYSTEM = "/WEB-INF/jsp/system/";

		/** 路径前缀：HTML页面 */
		public static final String RETURN_PATH_HTML = "/html/";
	}

	public static final class log {
		/**
		 * 业务名称
		 */
		public static final class YWMC {
			public static final String ROLE = "角色管理";
			public static final String SYS = "系统日志";
			/** 通知公告/内部新闻 */
			public static final String SC = "通知公告/内部新闻";
			public static final String DLJCXXGL = "道路基础信息管理";
			public static final String DLLKLDGL = "道路路口路段管理";
			public static final String WFDM = "违法代码";
			public static final String DICTDETAIL = "单位管理";

			/** pfwang */

			public static final String PROJECT = "项目管理";
			public static final String CHILD_PROJECT = "子项目管理";
			public static final String HTMANAGE = "合同管理";
			public static final String WZMANAGE = "物资管理";
			public static final String CSMANAGE = "厂商管理";
			public static final String CGDDMANAGE = "采购订单管理";
			public static final String RKMXMANAGE = "入库明细管理";
			public static final String INVENTORY = "库存管理";
			public static final String INVENTORYINFO = "库存明细管理";
			public static final String CKDDMANAGE = "出库订单管理";
			public static final String CKMXMANAGE = "出库明细管理";

			public static final String CUSTOMERMANAGE = "客户管理";
			public static final String FILEMANDGE = "文件管理";
		}

		/**
		 * 操作类型
		 */
		public static final class CZLX {
			public static final String QUERY = "查询";
			public static final String INSERT = "添加";
			public static final String UPDATE = "修改";
			public static final String DELETE = "删除";

			public static final String LOGIN = "登录";
			public static final String UPDATE_PWD = "修改密码";
			public static final String SET_PWD = "重置密码";
			public static final String LOGOUT = "退出";
			public static final String CHUKU = "出库";
			public static final String RUKU = "入库";

		}

	}

	public static final class numberStr {
		/**
		 * 常量 枚举值 String
		 */
		public static final String ZERO_STR = "0";
		public static final String ONE_STR = "1";
		public static final String TWO_STR = "2";
		public static final String THREE_STR = "3";
		public static final String FOUR_STR = "4";
		public static final String FIVE_STR = "5";
		public static final String SIX_STR = "6";
		public static final String SEVEN_STR = "7";
		public static final String EIGHT_STR = "8";
		public static final String NINE_STR = "9";
	}

	public static final class numberInt {
		/**
		 * 常量 枚举值 int
		 */
		public static final int MINUS_FOUR_INTEGER = -4;
		public static final int MINUS_THREE_INTEGER = -3;
		public static final int MINUS_TWO_INTEGER = -2;
		public static final int MINUS_ONE_INTEGER = -1;
		public static final int ZERO_INTEGER = 0;
		public static final int ONE_INTEGER = 1;
		public static final int TWO_INTEGER = 2;
		public static final int THREE_INTEGER = 3;
		public static final int FOUR_INTEGER = 4;
		public static final int FIVE_INTEGER = 5;
		public static final int SIX_INTEGER = 6;
		public static final int SEVEN_INTEGER = 7;
		public static final int EIGHT_INTEGER = 8;
		public static final int NINE_INTEGER = 9;
	}

	/**
	 * 
	 * 类名称： state<br>
	 * 类描述：状态 <br>
	 * 创建人： 方曙强<br>
	 * 创建时间：2016-12-21<br>
	 * 修改人： 方曙强<br>
	 * 修改时间：2016-12-21
	 */

	public static final class state {
		/**
		 * 禁用
		 */
		public static final String disable = "0";
		/***
		 * 启用
		 */
		public static final String enable = "1";

	}
/*	public static final class ULine {

		public static final String MCH_ID = "100005340483";// 商户号 由UCHANG分配
		public static final String SIGN = "E384F7803C01B18F2FF13EB9061F4D03";// Sign
		
		// 微信支付统一接口(POST)
		public static final String UNIFIED_ORDER_URL = "http://mapi.bosc.uline.cc/wechat/orders";
		// 异步通知URL
		public static final String NOTIFY_URL = "http://localhost:8080/zbpt/app/bas/order/notify.action";
					

	}*/
	public static final class PayConstant {

		public static final String MCH_ID = "1516414861";// 商户号
		public static final String API_KEY = "changliandakeji15091627683000000";// API密钥

	}
	public static final class WechatOpen {
		
		public static final String MCH_ID = "1520446641";// 商户号
		public static final String APP_ID = "wx164a47bc254a0141";// 微信开放平台
		public static final String API_KEY = "xianouchangkeji15091627683000000";// API密钥
		public static final String Trade_Type ="APP";
	}
	public static final class WechatConfig {
		public static final String APP_ID = "wxef760905bcb1d363";
		public static final String APP_SECRET = "07f90fc4acfb30a48a75d380c2d0eb8a";
		public static final String WECHAT_TOKEN = "zbpt";
		public static final String REDIRECT_URL = "http://zbpt.ockeji.com/course/index.jsp";
		
		// 异步通知URL
		public static final String NOTIFY_URL = "http://zbpt.ockeji.com/zbpt/app/bas/order/notify.action";
		
		// 微信支付统一接口(POST)
		public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// 微信退款接口(POST)
		public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		// 订单查询接口(POST)
		public static final String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
		// 关闭订单接口(POST)
		public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
		// 退款查询接口(POST)
		public static final String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
		// 对账单接口(POST)
		public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
		// 短链接转换接口(POST)
		public static final String PAY_SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
		// 接口调用上报接口(POST)
		public static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
		// Token
		public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
		// 创建二维码
		public static final String CREATE_TICKET_PATH = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
		// 通过ticket换取二维码
		public static final String SHOW_QRCODE_PATH = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
		// 长链接转成短链接
		public static final String WECHAT_SHORT_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/shorturl";

		// 创建菜单
		public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
		// 查询自定义菜单
		public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
		// 删除自定义菜单
		public static final String MENU_DELTE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

		// 发送模板消息
		public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";

		// 授权链接
		public static final String AUTHORIZE_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
		// 获取token的链接
		public static final String GET_OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
		// 刷新token
		public static final String REFRESH_OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
		// 获取授权用户信息
		public static final String SNS_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
		// 判断用户accessToken是否有效
		public static final String CHECK_SNS_AUTH_STATUS_URL = "https://api.weixin.qq.com/sns/auth";
		// 授权登陆链接
		public static final String QR_CONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect";
		// 获取jsapi_ticket
		public static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";// ?access_token=ACCESS_TOKEN&type=jsapi

		// 多媒体上传
		public static final String UPLOAD_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload"; // ?access_token=ACCESS_TOKEN&type=TYPE
		public static final String UPLOAD_FOREVER_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news";
		public static final String UPLOAD_TEMP_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
		public static final String UPLOAD_FOREVER_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material";

		//获取群发视频post中的media_id  
		public static final String UPLOAD_VIDEO_MEDIA_URL = " https://api.weixin.qq.com/cgi-bin/media/uploadvideo";  
		// 上传图文消息内的图片获取URL
		public static final String UPLOAD_IMG_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
		// 上传图文消息素材的path
		public static final String UPLOAD_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
		// 按分组进行群发
		public static final String SEND_ALL_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
		// 按照openid进行群发消息(OpenID最少2个，最多10000个 10000个)  
		public static final String SEND_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
		// 删除群发消息
		public static final String DELETE_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete";
		// 预览接口
		public static final String PREVIEW_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
		// 查询群发消息的发送状态
		public static final String GET_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/get";

	}
	public static final class SystemConstant {
		
		public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
		public static final String GET_METHOD = "GET";
		public static final String POST_METHOD = "POST";
		public static final int DEF_CONN_TIMEOUT = 30000;
		public static final int DEF_READ_TIMEOUT = 30000;
		public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	}
	public static final class HuaXinSendMsg {
		 /**
	     * 用户账号
	     */
	    public static final String ACCOUNT = "8M00035";
	    /**
	     * 密钥
	     */
	    public static final String PASSWORD = "8M0003577";
	    /**
	     * 请求地址前半部分
	     */
	    public static final String BASE_URL = "https://dx.ipyy.net/smsJson.aspx";//请求地址是固定的不用改

	}
}
