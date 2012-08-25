#LEGENDSHOP STRUCT VERSION 3.0.3.5;
CREATE TABLE `ls_adv` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pic_url` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `link_url` varchar(50) DEFAULT NULL COMMENT '连接地址',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称,备用',
  `enabled` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '广告类型',
  `title` varchar(255) DEFAULT NULL COMMENT '显示的标题',
  `source_input` varchar(255) DEFAULT NULL COMMENT '广告来源',
  PRIMARY KEY (`id`),
  KEY `advertisement_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='广告表';
CREATE TABLE `ls_areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaid` varchar(20) NOT NULL DEFAULT '' COMMENT '地区ID',
  `area` varchar(50) NOT NULL DEFAULT '' COMMENT '地区名称',
  `cityid` varchar(20) NOT NULL DEFAULT '' COMMENT '城市ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3145 DEFAULT CHARSET=utf8 COMMENT='地区表';
CREATE TABLE `ls_ask` (
  `ask_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '提问人ID',
  `user_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '提问人名称',
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品Id',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '商城ID',
  `type` char(1) NOT NULL COMMENT '提问类型',
  `content` char(255) DEFAULT NULL COMMENT '提问内容',
  `comment` char(255) DEFAULT NULL COMMENT '回复内容',
  `create_time` datetime NOT NULL COMMENT '提问时间',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`ask_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户提问表';
CREATE TABLE `ls_basket` (
  `basket_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '产品主图片路径',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `basket_count` int(11) NOT NULL DEFAULT '0' COMMENT '购物车产品个数',
  `basket_check` char(4) NOT NULL DEFAULT '' COMMENT '是否已经生成订单,N: 只是存在于购物车， Y： 已经生成了订单，saveto时设置为Y',
  `prod_name` varchar(120) NOT NULL DEFAULT '' COMMENT '产品名称',
  `price` double(15,3) DEFAULT NULL COMMENT '产品原价',
  `cash` double(15,3) DEFAULT NULL COMMENT '产品现价',
  `carriage` double(15,3) DEFAULT NULL COMMENT '运费',
  `sub_number` varchar(50) DEFAULT NULL COMMENT '订购流水号',
  `basket_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '购物时间',
  `last_update_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `shop_name` varchar(50) NOT NULL DEFAULT '' COMMENT '订购的店铺',
  `attribute` text COMMENT '产品动态属性',
  PRIMARY KEY (`basket_id`),
  KEY `basket_shop_name_key` (`shop_name`),
  KEY `find_by_sub_number` (`sub_number`,`basket_check`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';
CREATE TABLE `ls_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand_name` varchar(30) NOT NULL DEFAULT '' COMMENT '品牌名称',
  `brand_pic` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称,备用',
  `memo` varchar(50) DEFAULT NULL COMMENT '备注',
  `seq` tinyint(3) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='品牌表';
CREATE TABLE `ls_cash` (
  `cash_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代金券ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `code` varchar(50) NOT NULL COMMENT '识别码，系统唯一',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '商家ID',
  `prod_id` int(11) DEFAULT NULL COMMENT '产品ID',
  `partner_id` varchar(50) DEFAULT NULL COMMENT '商户ID',
  `sub_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `detail` varchar(255) DEFAULT NULL COMMENT '描述',
  `money` double(15,3) NOT NULL DEFAULT '0.000' COMMENT '代金券金额',
  `status` char(1) NOT NULL COMMENT '状态，是否使用过',
  `begin_time` datetime NOT NULL COMMENT '有效期开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `ip` varchar(16) DEFAULT NULL COMMENT '使用时的IP',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  PRIMARY KEY (`cash_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代金券';
CREATE TABLE `ls_cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cityid` varchar(20) NOT NULL DEFAULT '' COMMENT '城市ID',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市名称',
  `provinceid` varchar(20) NOT NULL DEFAULT '' COMMENT '省份ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8 COMMENT='城市表';
CREATE TABLE `ls_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `shop_id` int(11) NOT NULL COMMENT '商家ID',
  `partner_id` int(11) NOT NULL COMMENT '商户ID',
  `prod_id` int(11) NOT NULL COMMENT '产品ID',
  `sub_id` int(11) NOT NULL COMMENT '订单ID',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `score` int(11) DEFAULT NULL COMMENT '获得积分',
  `secret` varchar(10) DEFAULT NULL COMMENT '优惠卷密码',
  `status` char(1) DEFAULT NULL COMMENT '状态，是否使用过',
  `ip` varchar(16) DEFAULT NULL COMMENT '使用优惠卷时的ip',
  `sms_status` char(1) DEFAULT NULL COMMENT '是否成功发送短信',
  `sms_content` varchar(70) DEFAULT NULL COMMENT '发送的短信内容',
  `expire_time` datetime NOT NULL COMMENT '优惠券过期时间',
  `consume_time` datetime DEFAULT NULL COMMENT '优惠券使用时间',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `sms_time` datetime DEFAULT NULL COMMENT '短信发送时间',
  `buy_id` int(11) NOT NULL COMMENT '该项目下的购买次序',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团购优惠券';
CREATE TABLE `ls_cst_table` (
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '常量类型',
  `key_value` varchar(50) NOT NULL DEFAULT '' COMMENT '常量关键字',
  `value` varchar(100) DEFAULT NULL COMMENT '常量值',
  `seq` int(5) DEFAULT NULL COMMENT '顺序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常量表';
CREATE TABLE `ls_delivery` (
  `dvy_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户名称',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '物流公司名称',
  `url` varchar(255) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`dvy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='物流公司';
CREATE TABLE `ls_dol_log` (
  `dl_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `area` varchar(100) DEFAULT NULL COMMENT '地区',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `24` varchar(16) NOT NULL DEFAULT '' COMMENT 'ip',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `shop_name` varchar(50) DEFAULT NULL COMMENT '商城名称',
  `file_name` varchar(200) NOT NULL DEFAULT '' COMMENT '文件名称',
  `rec_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下载时间',
  PRIMARY KEY (`dl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下载历史';
CREATE TABLE `ls_dvy_type` (
  `dvy_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户名称',
  `dvy_id` int(11) NOT NULL COMMENT '物流公司ID',
  `type` varchar(50) DEFAULT NULL COMMENT '配送类型',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '配送方式名称',
  `notes` varchar(150) DEFAULT '' COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`dvy_type_id`),
  KEY `dvy_id` (`dvy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='物流配送方式';
CREATE TABLE `ls_dyn_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '动态模板名称',
  `content` text COMMENT '内容',
  `type` tinyint(3) DEFAULT NULL COMMENT '类型，1:attributeute,2:parameter',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`,`name`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品动态属性模板';
CREATE TABLE `ls_event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '操作员ID',
  `user_name` varchar(50) NOT NULL COMMENT '操作员名称',
  `type` char(1) DEFAULT NULL COMMENT '操作类型',
  `operation` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `relate_data` text COMMENT '相关数据',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统事件';
CREATE TABLE `ls_extl_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告ID',
  `url` varchar(300) DEFAULT NULL COMMENT 'url',
  `wordlink` varchar(50) DEFAULT NULL COMMENT '关键字',
  `content` varchar(50) DEFAULT NULL COMMENT '内容',
  `bs` int(11) DEFAULT NULL COMMENT '顺序',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户名',
  `picture` varchar(250) DEFAULT NULL COMMENT '链接广告图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='友情链接表';
CREATE TABLE `ls_favorite` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `prod_name` varchar(120) NOT NULL DEFAULT '' COMMENT '收藏的商品名称',
  `addtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '收藏时间',
  `memo` varchar(100) DEFAULT NULL COMMENT '收藏说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品收藏表';
CREATE TABLE `ls_flow` (
  `flow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `admin_id` varchar(32) NOT NULL COMMENT '操作管理员ID',
  `detail_id` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `detail` varchar(255) DEFAULT NULL COMMENT '详细信息',
  `direction` char(1) NOT NULL COMMENT '金额流动情况(Credit or Debit)',
  `money` double(15,3) DEFAULT NULL COMMENT '金额数量',
  `action` varchar(16) NOT NULL COMMENT '金额用途',
  `create_time` int(10) NOT NULL COMMENT '金额变动时间',
  PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金流动表';
CREATE TABLE `ls_func` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '权限名称',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '父权限名称，备用',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `protect_function` varchar(255) NOT NULL DEFAULT '' COMMENT '保护的权限',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `PROTECT_FUNCTION` (`protect_function`),
  KEY `INDEX_PROTECT_FUNCTION` (`protect_function`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Function权限表';
CREATE TABLE `ls_group_prod` (
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `partner_id` int(11) DEFAULT '0' COMMENT '商户ID',
  `success_cause` char(1) NOT NULL DEFAULT '' COMMENT 'M:以购买成功人数成团，P:以产品购买数成团',
  `buy_condition` char(1) NOT NULL DEFAULT '' COMMENT '购买次数限制 O:仅购买一次，M:可购买多次',
  `visual_buys` int(10) DEFAULT NULL COMMENT '虚拟购买数',
  `max_buys` int(10) DEFAULT NULL COMMENT '每人限购数',
  `dvy_type_id` int(10) DEFAULT NULL COMMENT '递送方式',
  `max_money` double(15,3) DEFAULT NULL COMMENT '可使用代金券最大面额',
  `coupon_start_time` datetime DEFAULT NULL COMMENT '券开始有效期',
  `coupon_end_time` datetime DEFAULT NULL COMMENT '券结束有效期',
  `sold_out` char(1) DEFAULT NULL COMMENT '是否售完',
  `sales_min` int(10) DEFAULT NULL COMMENT '最小成团数',
  `sales_num` int(10) DEFAULT NULL COMMENT '已经销售数量',
  `sales_max` int(10) DEFAULT NULL COMMENT '最大的销售数量，销售完不能再订购',
  `is_post` char(1) DEFAULT NULL COMMENT '是否已经跟商家结算',
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团购产品表';
CREATE TABLE `ls_hsearch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `sort` int(11) DEFAULT NULL COMMENT '产品分类',
  `msg` varchar(255) NOT NULL COMMENT '内容',
  `rec_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称,备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='热门产品表';
CREATE TABLE `ls_img_file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `product_id` int(11) DEFAULT NULL COMMENT '产品ID',
  `product_type` tinyint(3) DEFAULT NULL COMMENT '文件类型',
  `file_path` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(16) DEFAULT NULL COMMENT '文件类型',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `upoad_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上传时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`file_id`),
  KEY `img_file_index` (`product_type`,`user_name`,`product_id`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=576 DEFAULT CHARSET=utf8 COMMENT='上传的文件表';
CREATE TABLE `ls_index_jpg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `href` varchar(200) DEFAULT '' COMMENT '连接地址',
  `img` varchar(200) NOT NULL COMMENT '图片',
  `alt` varchar(200) NOT NULL COMMENT '说明文字',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `stitle` varchar(200) NOT NULL COMMENT '小标题',
  `link` varchar(200) NOT NULL COMMENT '连接图片',
  `titleLink` varchar(200) NOT NULL COMMENT '文字连接图片',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  PRIMARY KEY (`id`),
  KEY `indexjpg_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='主页轮换图片';
CREATE TABLE `ls_league` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `friend_id` varchar(50) NOT NULL DEFAULT '' COMMENT '好友名称',
  `friend_name` varchar(50) NOT NULL DEFAULT '' COMMENT '好友名称',
  `display_order` int(10) DEFAULT NULL COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '1：已经成为好友，2：申请中，3：对方拒绝，当通知到用户的时候把该记录删除',
  `addtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_friend_id` (`user_id`,`friend_id`),
  KEY `FK_league2user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='我的加盟店，店面之间的产品可以互相展现对方的产品';
CREATE TABLE `ls_login_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `area` varchar(100) DEFAULT NULL COMMENT '地区',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `user_name` varchar(120) DEFAULT NULL COMMENT '用户名称',
  `ip` varchar(24) NOT NULL DEFAULT '' COMMENT 'IP',
  `time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '时间',
  PRIMARY KEY (`id`),
  KEY `ID` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录历史表';
CREATE TABLE `ls_logo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `banner` varchar(300) DEFAULT NULL COMMENT '标语位置',
  `url` varchar(50) DEFAULT NULL COMMENT '连接地址',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称,备用',
  `memo` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `logo_user_name_ind` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='主广告位表';
CREATE TABLE `ls_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻ID',
  `news_category_id` int(11) DEFAULT NULL COMMENT '新闻分类',
  `sort` int(11) DEFAULT NULL COMMENT '商品分类',
  `news_title` varchar(100) DEFAULT NULL COMMENT '新闻标题',
  `news_brief` varchar(300) DEFAULT NULL COMMENT '新闻提要，保存是截取新闻前100个字',
  `news_content` text COMMENT '新闻内容',
  `news_date` datetime DEFAULT NULL COMMENT '发表时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '新闻状态，1：上线，0：下线',
  `position` tinyint(3) DEFAULT NULL COMMENT '新闻位置',
  `high_line` tinyint(1) DEFAULT '0' COMMENT '是否高亮,1:yes,0:no',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户名称',
  PRIMARY KEY (`news_id`),
  KEY `news_category_id` (`news_category_id`),
  KEY `sort` (`sort`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='新闻';
CREATE TABLE `ls_news_cat` (
  `news_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻栏目ID',
  `news_category_name` varchar(100) NOT NULL COMMENT '新闻栏目名称',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态',
  `news_category_date` datetime NOT NULL COMMENT '发表时间',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户名称',
  PRIMARY KEY (`news_category_id`),
  KEY `FK_news_category_to_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='新闻栏目';
CREATE TABLE `ls_ns_brand` (
  `nsort_id` int(11) NOT NULL COMMENT '三级商品类型的ID',
  `brand_id` int(11) NOT NULL DEFAULT '0' COMMENT '品牌ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`brand_id`,`nsort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三级类型和品牌的对照表';
CREATE TABLE `ls_nsort` (
  `nsort_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nsort_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `seq` int(5) DEFAULT NULL COMMENT '排序',
  `sort_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应的一级类型',
  `parent_nsort_id` int(11) DEFAULT NULL COMMENT '父节点ID',
  `sort_deputy` tinyint(1) DEFAULT '0' COMMENT '是否代表，0否1是',
  PRIMARY KEY (`nsort_id`),
  KEY `sort_id` (`sort_id`),
  KEY `parent_nsort_id` (`parent_nsort_id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8 COMMENT='专柜，就是商品子类表';
CREATE TABLE `ls_partner` (
  `partner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `partner_name` varchar(50) NOT NULL DEFAULT '' COMMENT '供应商登录名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '供应商密码',
  `title` varchar(128) NOT NULL DEFAULT '' COMMENT '商户名称',
  `homepage` varchar(128) DEFAULT NULL COMMENT '商户网站主页',
  `user_id` varchar(32) NOT NULL DEFAULT '用户ID' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `shop_id` int(11) NOT NULL COMMENT '所属商家',
  `bank_name` varchar(128) DEFAULT NULL COMMENT '商户银行帐户名称',
  `bank_no` varchar(128) DEFAULT NULL COMMENT '商户银行帐户帐号',
  `bank_user` varchar(128) DEFAULT NULL COMMENT '商户银行用户名',
  `location` varchar(255) DEFAULT '' COMMENT '商户所处位置',
  `contact` varchar(32) DEFAULT NULL COMMENT '商户联系人',
  `image` varchar(255) DEFAULT NULL COMMENT '商户图片',
  `image1` varchar(128) DEFAULT NULL COMMENT '商户图片1',
  `image2` varchar(128) DEFAULT NULL COMMENT '商户图片2',
  `phone` varchar(18) DEFAULT NULL COMMENT '商户电话号码',
  `address` varchar(128) DEFAULT NULL COMMENT '商户联系地址',
  `other` varchar(255) DEFAULT NULL COMMENT '商户其他信息',
  `mobile` varchar(12) DEFAULT NULL COMMENT '商户手机号码',
  `show_info` char(1) NOT NULL DEFAULT '' COMMENT '商户是否展示相关信息',
  `status` char(1) NOT NULL COMMENT '商户状态',
  `display` char(1) NOT NULL COMMENT '是否显示首页',
  `comment_good` int(11) NOT NULL COMMENT '对商户评论满意数量',
  `comment_none` int(11) NOT NULL COMMENT '对商户评论一般数量',
  `comment_bad` int(11) NOT NULL COMMENT '对商户评论失望数量',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  PRIMARY KEY (`partner_id`),
  KEY `UNQ_ct` (`shop_id`,`title`),
  KEY `UNQ_u` (`partner_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商户（产品供应商）';
CREATE TABLE `ls_pay_type` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `pay_type_id` int(2) NOT NULL DEFAULT '0' COMMENT '付款方式ID',
  `pay_type_name` varchar(50) DEFAULT NULL COMMENT '付款方式',
  `partner` varchar(100) DEFAULT NULL COMMENT '供应商名称',
  `validate_key` varchar(100) DEFAULT NULL COMMENT '验证码',
  `seller_email` varchar(100) DEFAULT NULL COMMENT '签约支付宝账号或卖家收款支付宝帐户',
  `memo` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pay_id`),
  UNIQUE KEY `uni_user_name_pay_type_id` (`user_name`,`pay_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='付款方式';
CREATE TABLE `ls_perm` (
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  `function_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FUNCTION_ID` (`function_id`),
  KEY `ROLE_ID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
CREATE TABLE `ls_prod` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `sort_id` int(11) DEFAULT NULL COMMENT '一级分类',
  `nsort_id` int(11) DEFAULT NULL COMMENT '二级分类',
  `sub_nsort_id` int(11) DEFAULT NULL COMMENT '三级分类',
  `model_id` varchar(50) DEFAULT NULL COMMENT '型号',
  `name` varchar(120) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` double(15,3) DEFAULT NULL COMMENT '原价',
  `cash` double(15,3) DEFAULT NULL COMMENT '现价',
  `proxy_price` double(15,3) DEFAULT NULL COMMENT '代理价',
  `carriage` int(11) DEFAULT NULL COMMENT '运费',
  `brief` text COMMENT '简要描述',
  `content` text COMMENT '详细描述',
  `views` int(11) NOT NULL DEFAULT '0' COMMENT '观看人数',
  `buys` int(11) NOT NULL DEFAULT '0' COMMENT '以卖出数',
  `rec_date` datetime DEFAULT NULL COMMENT '录入时间',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `commend` char(1) DEFAULT NULL COMMENT '是否精品推荐',
  `status` int(1) DEFAULT NULL COMMENT '默认是1，表示正常状态，如果管理员发现有非法图片，可以关闭该图片，设置为0即可',
  `modify_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '所属用户名称',
  `start_date` datetime DEFAULT NULL COMMENT '开始有效时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束有效时间',
  `stocks` int(11) DEFAULT '0' COMMENT '库存量',
  `prod_type` char(1) NOT NULL DEFAULT 'P' COMMENT '商品类型，P.普通商品，G:团购商品，S:二手商品，D:打折商品',
  `key_word` varchar(255) DEFAULT NULL COMMENT '关键字',
  `attribute` text COMMENT '产品动态属性',
  `parameter` text COMMENT '商品动态参数',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌',
  `actual_stocks` int(11) DEFAULT '0' COMMENT '实际库存',
  PRIMARY KEY (`prod_id`),
  UNIQUE KEY `hw_id_username` (`prod_id`,`user_name`),
  KEY `key_product_status` (`status`,`start_date`,`end_date`),
  KEY `key_prod_nsort_id` (`nsort_id`),
  KEY `key_prod_sort_id` (`sort_id`)
) ENGINE=InnoDB AUTO_INCREMENT=537 DEFAULT CHARSET=utf8 COMMENT='商品';
CREATE TABLE `ls_prod_comm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `owner_Name` varchar(50) NOT NULL COMMENT '商品所有人',
  `user_name` varchar(50) NOT NULL COMMENT '点评人ID',
  `content` varchar(300) NOT NULL COMMENT '点评内容',
  `addtime` datetime NOT NULL COMMENT '添加时间',
  `postip` varchar(16) DEFAULT NULL COMMENT 'IP来源',
  `score` smallint(6) DEFAULT NULL COMMENT '得分，0-5分',
  `reply_content` varchar(300) DEFAULT NULL COMMENT '回复内容',
  `reply_name` varchar(50) DEFAULT NULL COMMENT '回复人',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`),
  KEY `comment_hw_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品点评';
CREATE TABLE `ls_prod_rel` (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `rel_prod_id` int(11) NOT NULL DEFAULT '0' COMMENT '相关产品ID',
  `rel_prod_name` varchar(100) DEFAULT NULL COMMENT '相关产品名称',
  `addtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品对应的用户名',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='相关产品';
CREATE TABLE `ls_provinces` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `provinceid` varchar(20) NOT NULL DEFAULT '' COMMENT '省份ID',
  `province` varchar(50) NOT NULL DEFAULT '' COMMENT '省份名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='省份表';
CREATE TABLE `ls_pub` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `msg` varchar(255) NOT NULL COMMENT '内容',
  `rec_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称,备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商城公告表';
CREATE TABLE `ls_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `role_type` varchar(50) NOT NULL DEFAULT '' COMMENT '角色类型',
  `enabled` varchar(1) NOT NULL DEFAULT '' COMMENT '状态',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
CREATE TABLE `ls_score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sub_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `score` int(11) NOT NULL COMMENT '用户积分',
  `score_type` char(1) NOT NULL COMMENT '积分类型',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户',
  `rec_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='积分表';
CREATE TABLE `ls_shop_detail` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '对应用户名称',
  `site_name` varchar(50) DEFAULT NULL COMMENT '商城名称',
  `shop_addr` varchar(300) DEFAULT NULL COMMENT '网店地址',
  `bank_card` varchar(50) DEFAULT NULL COMMENT '银行汇款帐号',
  `payee` varchar(50) DEFAULT NULL COMMENT '收款人姓名',
  `code` varchar(30) DEFAULT NULL COMMENT '邮政编码',
  `post_addr` varchar(300) DEFAULT NULL COMMENT '邮局汇款地址',
  `recipient` varchar(50) DEFAULT NULL COMMENT '邮递接收人',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态,是否上线1：在线，0下线，-1审核中,-2拒绝,-3关闭（管理员操作）',
  `visit_times` int(10) NOT NULL DEFAULT '0' COMMENT '访问人数',
  `product_num` int(11) NOT NULL DEFAULT '0' COMMENT '产品数量',
  `comm_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论个数',
  `off_product_num` int(11) NOT NULL DEFAULT '0' COMMENT '下线产品数量',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `addtime` datetime NOT NULL COMMENT '创建时间',
  `brief_desc` varchar(300) DEFAULT NULL COMMENT '店铺简述',
  `detail_desc` varchar(4000) DEFAULT NULL COMMENT '店铺详细描述',
  `shop_pic` varchar(255) DEFAULT NULL COMMENT '店铺图片',
  `color_style` varchar(15) DEFAULT NULL COMMENT '网页风格',
  `lang_style` varchar(15) DEFAULT NULL COMMENT '语言选项，英语en_us,中文zh_cn',
  `grade_id` int(10) DEFAULT NULL COMMENT '商铺等级',
  `type` int(1) NOT NULL COMMENT '店铺类型0：个人用户，1：商家用户',
  `id_card_pic` varchar(250) DEFAULT NULL COMMENT '身份证验证图片',
  `traffic_pic` varchar(250) DEFAULT NULL COMMENT '营业执照图片',
  `id_card_num` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `create_country_code` varchar(50) DEFAULT NULL COMMENT '创建时的国家码',
  `create_area_code` varchar(50) DEFAULT NULL COMMENT '创建时的地区',
  `provinceid` int(11) DEFAULT NULL COMMENT '省份',
  `cityid` int(11) DEFAULT NULL COMMENT '城市',
  `areaid` int(11) DEFAULT NULL COMMENT '地级市',
  `front_type` varchar(15) DEFAULT NULL COMMENT '前台风格',
  `end_type` varchar(15) DEFAULT NULL COMMENT '后台风格',
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `shop_id` (`shop_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';
CREATE TABLE `ls_sort` (
  `sort_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品分类ID',
  `sort_name` varchar(50) NOT NULL DEFAULT '' COMMENT '产品分类名称',
  `picture` varchar(250) DEFAULT NULL COMMENT '分类的显示图片',
  `sort_type` char(1) DEFAULT NULL COMMENT '产品分类类型',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `header_menu` tinyint(1) DEFAULT '0' COMMENT '是否Header菜单展示，0否，1是',
  `navigation_menu` tinyint(1) DEFAULT '0' COMMENT '导航菜单中显示，0否1是',
  PRIMARY KEY (`sort_id`),
  UNIQUE KEY `sort_name` (`sort_name`,`sort_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='展柜，商品分类';
CREATE TABLE `ls_sub` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订购ID',
  `basket_id` varchar(1024) DEFAULT NULL COMMENT '购物车ID,多个将会以逗号隔开',
  `prod_name` varchar(2000) NOT NULL DEFAULT '' COMMENT '产品名称,多个产品将会以逗号隔开',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '订购用户名称',
  `order_name` varchar(50) NOT NULL DEFAULT '' COMMENT '表单中填写的接收人',
  `sub_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订购时间',
  `pay_date` datetime DEFAULT NULL COMMENT '购买时间',
  `update_date` datetime DEFAULT NULL COMMENT '订单更新时间',
  `sub_number` varchar(50) NOT NULL COMMENT '订购流水号',
  `sub_type` char(1) DEFAULT NULL COMMENT '订单类型',
  `sub_check` char(1) DEFAULT NULL COMMENT '订购审批状态，True：审批通过，False：还没有审批',
  `total` double(15,3) NOT NULL DEFAULT '0.000' COMMENT '总值',
  `actual_total` double(15,3) DEFAULT NULL COMMENT '实际总值',
  `sub_mail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sub_tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `pay_id` int(10) DEFAULT NULL COMMENT '付款方式',
  `pay_type_id` int(2) DEFAULT NULL COMMENT '付款类型ID',
  `pay_type_name` varchar(30) DEFAULT NULL COMMENT '付款类型名称',
  `sub_adds` varchar(255) DEFAULT NULL COMMENT '地址',
  `sub_post` varchar(15) DEFAULT NULL COMMENT '邮编',
  `other` varchar(1024) DEFAULT NULL COMMENT '其他备注',
  `shop_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商城名称',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `score_id` int(11) DEFAULT NULL COMMENT '用户积分ID',
  `score` int(11) DEFAULT NULL COMMENT '使用积分数',
  `trade_no` varchar(30) DEFAULT NULL COMMENT '支付宝交易号',
  `dvy_type_id` int(11) DEFAULT NULL COMMENT '物流方式ID',
  `dvy_flow` varchar(100) DEFAULT '' COMMENT '物流单号',
  PRIMARY KEY (`sub_id`),
  UNIQUE KEY `sub_number_unique_ind` (`sub_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订购表';
CREATE TABLE `ls_sub_hist` (
  `sub_hist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sub_id` int(11) NOT NULL DEFAULT '0' COMMENT '订购ID',
  `basket_id` varchar(1024) DEFAULT NULL COMMENT '购物车ID,多个将会以逗号隔开',
  `prod_name` varchar(2000) DEFAULT NULL COMMENT '产品名称，多个产品将会以逗号隔开',
  `user_name` varchar(50) DEFAULT NULL COMMENT '订购用户名称',
  `order_name` varchar(50) DEFAULT NULL COMMENT '表单中填写的接收人',
  `sub_date` datetime DEFAULT NULL COMMENT '订购时间',
  `pay_date` datetime DEFAULT NULL COMMENT '订购时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `sub_number` varchar(50) NOT NULL COMMENT '订购流水号',
  `sub_type` char(1) DEFAULT NULL COMMENT '订单类型',
  `sub_check` char(1) DEFAULT NULL COMMENT '订购审批状态，True：审批通过，False：还没有审批',
  `total` double(15,3) NOT NULL DEFAULT '0.000' COMMENT '总值',
  `actual_total` double(15,3) DEFAULT NULL COMMENT '实际总数',
  `sub_mail` varchar(50) DEFAULT NULL COMMENT '邮件',
  `sub_tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `pay_id` int(10) DEFAULT NULL COMMENT '付款方式',
  `pay_type_id` int(4) DEFAULT NULL COMMENT '付款类型ID',
  `pay_type_name` varchar(30) DEFAULT NULL COMMENT '付款类型名称',
  `sub_adds` varchar(255) DEFAULT NULL COMMENT '地址',
  `sub_post` varchar(15) DEFAULT NULL COMMENT '邮编',
  `other` varchar(1024) DEFAULT NULL COMMENT '其他备注',
  `shop_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商城名称',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `score_id` int(11) DEFAULT NULL COMMENT '积分ID',
  `score` int(11) DEFAULT NULL COMMENT '使用积分数',
  `update_user` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `sub_action` char(4) DEFAULT NULL COMMENT '订单动作',
  `trade_no` varchar(30) DEFAULT NULL COMMENT '支付宝交易号',
  `dvy_type_id` int(11) DEFAULT NULL COMMENT '物流方式ID',
  `dvy_flow` varchar(100) DEFAULT NULL COMMENT '物流单号',
  PRIMARY KEY (`sub_hist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订购历史表';
CREATE TABLE `ls_sys_param` (
  `name` varchar(100) NOT NULL COMMENT '系统配置名称',
  `value` varchar(200) DEFAULT '' COMMENT '系统配置值',
  `memo` varchar(100) NOT NULL COMMENT '配置说明',
  `type` varchar(30) NOT NULL DEFAULT '' COMMENT '对应JAVA类型',
  `optional` varchar(255) DEFAULT '' COMMENT '如果不填写的默认值',
  `change_online` char(1) DEFAULT NULL COMMENT '是否可以线上修改',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';
CREATE TABLE `ls_user` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `enabled` varchar(1) NOT NULL DEFAULT '' COMMENT '状态',
  `note` varchar(100) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_USER_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
CREATE TABLE `ls_usr_addr` (
  `addr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户名称',
  `receiver` varchar(50) DEFAULT NULL COMMENT '接受人名称',
  `sub_adds` varchar(255) DEFAULT NULL COMMENT '地址',
  `sub_post` varchar(15) DEFAULT NULL COMMENT '邮编',
  `province_id` int(11) DEFAULT NULL COMMENT '省份ID',
  `city_id` int(11) DEFAULT NULL COMMENT '城市ID',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `telphone` varchar(20) DEFAULT NULL COMMENT '固话',
  `email` varchar(50) DEFAULT NULL COMMENT 'Email地址',
  `status` char(1) NOT NULL DEFAULT '' COMMENT '状态,1正常，0无效',
  `common_addr` char(1) DEFAULT NULL COMMENT '是否常用地址',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  PRIMARY KEY (`addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户配送地址';
CREATE TABLE `ls_usr_comm` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `comment_type` int(10) DEFAULT NULL COMMENT '留言类型,1:投诉，2：普通谈话',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '发起谈话的用户名称',
  `your_name` varchar(50) DEFAULT NULL COMMENT '用户名称，由用户填写',
  `to_user_name` varchar(50) DEFAULT NULL COMMENT '通话对象',
  `grade_id` int(10) DEFAULT NULL COMMENT '商铺等级',
  `content` varchar(1000) DEFAULT NULL COMMENT '留言内容',
  `answer` varchar(1000) DEFAULT NULL COMMENT '回答',
  `addtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `postip` varchar(16) DEFAULT NULL COMMENT 'IP来源',
  `status` int(10) DEFAULT '0' COMMENT '阅读状态，0：未读，1：已读',
  `answertime` datetime DEFAULT NULL COMMENT '回答时间',
  PRIMARY KEY (`id`),
  KEY `FK_usercomment` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的留言';
CREATE TABLE `ls_usr_detail` (
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `grade_id` int(10) DEFAULT NULL COMMENT '等级ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `user_mail` varchar(50) NOT NULL COMMENT '用户邮件',
  `user_adds` varchar(255) DEFAULT '' COMMENT '用户地址',
  `user_tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `user_postcode` varchar(50) DEFAULT NULL COMMENT '用户邮编',
  `msn` varchar(100) DEFAULT NULL COMMENT 'msn',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq号码',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `user_regtime` datetime NOT NULL COMMENT '注册时间',
  `user_regip` varchar(50) NOT NULL COMMENT '注册IP',
  `user_lasttime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_lastip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `user_memo` varchar(500) DEFAULT NULL COMMENT '备注',
  `sex` char(1) DEFAULT 'M' COMMENT 'M(男) or F(女)',
  `birth_date` char(8) DEFAULT NULL COMMENT '例如：20100918',
  `register_code` varchar(200) DEFAULT NULL COMMENT '注册时生成的注册码',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `total_cash` double(15,3) NOT NULL DEFAULT '0.000' COMMENT '总钱数',
  `total_consume` double(15,3) NOT NULL DEFAULT '0.000' COMMENT '总消耗钱数',
  `addr_id` int(11) DEFAULT NULL COMMENT '默认地址ID',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_name_mail_unique` (`user_name`,`user_mail`),
  UNIQUE KEY `user_mail` (`user_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细表';
CREATE TABLE `ls_usr_grad` (
  `grade_id` int(10) NOT NULL COMMENT '等级ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `max_sort_size` int(10) DEFAULT NULL COMMENT '允许的最大展柜',
  `max_nsort_size` int(10) DEFAULT NULL COMMENT '允许的最大展柜分类',
  `max_prod` int(10) DEFAULT NULL COMMENT '允许的最大产品数',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户等级\r\n1幼儿园\r\n2小学\r\n3中学\r\n4大学\r\n5硕士';
CREATE TABLE `ls_usr_role` (
  `user_id` varchar(32) NOT NULL DEFAULT '',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `ROLE_ID` (`role_id`),
  KEY `USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';
CREATE TABLE `ls_vit_log` (
  `visit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(24) NOT NULL DEFAULT '' COMMENT 'IP',
  `country` varchar(100) DEFAULT NULL COMMENT '获得IP所在国家，如果在中国，直接显示省市',
  `area` varchar(100) DEFAULT NULL COMMENT '获得IP所在区域',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `shop_name` varchar(50) DEFAULT NULL COMMENT '商城名称',
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `product_name` varchar(120) DEFAULT NULL COMMENT '产品名称',
  `page` varchar(200) NOT NULL COMMENT '访问页面',
  `rec_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '访问时间',
  `visit_num` int(10) DEFAULT NULL COMMENT '访问次数',
  PRIMARY KEY (`visit_id`),
  KEY `lgs_visit_log_visited_ind` (`ip`,`shop_name`,`rec_date`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户浏览历史';
CREATE TABLE `ls_zipcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `areaid` varchar(20) NOT NULL DEFAULT '' COMMENT '地区ID',
  `zip` varchar(20) NOT NULL DEFAULT '' COMMENT '邮编',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '区号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3145 DEFAULT CHARSET=utf8 COMMENT='邮政编码表';

ALTER TABLE `ls_dvy_type`
ADD CONSTRAINT `ls_dvy_type_ibfk_1` FOREIGN KEY (`dvy_id`) REFERENCES `ls_delivery` (`dvy_id`);