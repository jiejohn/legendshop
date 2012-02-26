#LEGENDSHOP STRUCT VERSION 3.0.3.2;
CREATE TABLE ls_adv (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  pic_url varchar(300) DEFAULT NULL,
  link_url varchar(50) DEFAULT NULL COMMENT '连接地址',
  user_id varchar(32) NOT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '用户名称,备用',
  enabled int(2) DEFAULT '1',
  type varchar(20) NOT NULL,
  title varchar(255) DEFAULT NULL COMMENT 'html title',
  source_input varchar(255) DEFAULT NULL COMMENT '广告来源',
  PRIMARY KEY (id),
  KEY advertisement_user_name (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='广告表';
CREATE TABLE ls_areas (
  id int(11) NOT NULL AUTO_INCREMENT,
  areaid varchar(20) NOT NULL,
  area varchar(50) NOT NULL,
  cityid varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3145 DEFAULT CHARSET=utf8;
CREATE TABLE ls_basket (
  basket_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  hw_id int(11) NOT NULL,
  hw_pic varchar(255) DEFAULT NULL,
  user_name varchar(50) NOT NULL,
  basket_count int(11) DEFAULT NULL,
  basket_check char(1) DEFAULT NULL COMMENT '是否已经生成订单, false: 只是存在于购物车， true： 已经生成了订单，saveto时设置为true',
  hw_name varchar(120) DEFAULT NULL COMMENT '货物名称',
  hw_price double(15,3) DEFAULT NULL,
  hw_cash double(15,3) DEFAULT NULL,
  carriage double(15,3) DEFAULT NULL,
  sub_number varchar(50) DEFAULT NULL COMMENT '订购流水号',
  basket_date datetime DEFAULT NULL COMMENT '购物时间',
  last_update_date datetime DEFAULT NULL COMMENT '最后更新时间',
  shop_name varchar(50) NOT NULL,
  attribute text,
  PRIMARY KEY (basket_id),
  KEY basket_shop_name_key (shop_name),
  KEY find_by_sub_number (sub_number,basket_check) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='购物车';
CREATE TABLE ls_brand (
  brand_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  brand_name varchar(30) DEFAULT NULL,
  brand_pic varchar(255) DEFAULT NULL COMMENT '图片路径',
  user_id varchar(32) NOT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '用户名称,备用',
  memo varchar(50) DEFAULT NULL,
  seq tinyint(3) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (brand_id)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
CREATE TABLE ls_cities (
  id int(11) NOT NULL AUTO_INCREMENT,
  cityid varchar(20) NOT NULL,
  city varchar(50) NOT NULL,
  provinceid varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;
CREATE TABLE ls_cst_table (
  type varchar(50) NOT NULL DEFAULT '' COMMENT '常量类型',
  key_value varchar(50) NOT NULL DEFAULT '',
  value varchar(100) DEFAULT NULL,
  seq int(5) DEFAULT NULL,
  PRIMARY KEY (key_value,type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE ls_dol_log (
  dl_id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  area varchar(100) DEFAULT NULL,
  country varchar(100) DEFAULT NULL,
  ip char(16) NOT NULL,
  user_name varchar(32) DEFAULT NULL,
  shop_name varchar(32) DEFAULT NULL,
  file_name varchar(200) NOT NULL,
  rec_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (dl_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下载历史';
CREATE TABLE ls_dyn_temp (
  Id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  content text COMMENT '内容',
  type tinyint(3) DEFAULT NULL COMMENT '类型，1:attributeute,2:parameter',
  user_name varchar(32) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (Id),
  UNIQUE KEY user_name (user_name,name,type)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='商品动态属性模板';
CREATE TABLE ls_extl_link (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告',
  url varchar(300) DEFAULT NULL COMMENT 'url',
  wordlink varchar(50) DEFAULT NULL COMMENT '关键字',
  content varchar(50) DEFAULT NULL COMMENT '内容',
  bs int(11) DEFAULT NULL COMMENT '顺序',
  user_id varchar(32) DEFAULT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '所属用户名',
  picture varchar(250) DEFAULT NULL COMMENT '链接广告图片',
  PRIMARY KEY (id),
  UNIQUE KEY ad_id_username (id,user_name)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='广告';
CREATE TABLE ls_favorite (
  id varchar(32) NOT NULL COMMENT '主键',
  hw_id varchar(32) DEFAULT NULL COMMENT '商品ID',
  hw_name varchar(100) DEFAULT NULL COMMENT '收藏的商品名称',
  addtime datetime DEFAULT NULL COMMENT '收藏时间',
  memo varchar(100) DEFAULT NULL COMMENT '收藏原因',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏';
CREATE TABLE ls_func (
  ID varchar(32) NOT NULL,
  NAME varchar(100) NOT NULL COMMENT '功能名称',
  PARENT_NAME varchar(100) DEFAULT NULL COMMENT '父功能名称',
  URL varchar(255) DEFAULT NULL COMMENT 'url地址',
  PROTECT_FUNCTION varchar(255) NOT NULL COMMENT '保护的权限',
  NOTE varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (ID),
  KEY PROTECT_FUNCTION (PROTECT_FUNCTION),
  KEY INDEX_PROTECT_FUNCTION (PROTECT_FUNCTION)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能表';
CREATE TABLE ls_hsearch (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL COMMENT '标题',
  sort int(11) DEFAULT NULL,
  msg varchar(255) NOT NULL COMMENT '内容',
  rec_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  user_id varchar(32) DEFAULT NULL COMMENT '用户ID',
  user_name varchar(100) DEFAULT NULL COMMENT '用户名称,备用',
  PRIMARY KEY (id),
  UNIQUE KEY id (id,user_name),
  KEY FK_Reference_19 (user_id),
  KEY user_name_sort_id (user_name,sort)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='hotsearch';
CREATE TABLE ls_hw (
  hw_id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  sort_id int(11) unsigned DEFAULT NULL COMMENT '一级分类',
  nsort_id int(11) unsigned DEFAULT NULL COMMENT '二级分类',
  sub_nsort_id int(11) DEFAULT NULL COMMENT '三级分类',
  model_id varchar(50) DEFAULT NULL COMMENT '型号',
  hw_name varchar(120) DEFAULT '' COMMENT '商品名称',
  hw_price double(15,3) DEFAULT NULL,
  hw_cash double(15,3) DEFAULT NULL,
  proxy_price double(15,3) DEFAULT NULL,
  carriage int(11) DEFAULT NULL COMMENT '运费',
  hw_brief text COMMENT '简要描述',
  hw_content text COMMENT '详细描述',
  hw_views int(11) NOT NULL DEFAULT '0' COMMENT '观看人数',
  hw_buys int(11) NOT NULL DEFAULT '0' COMMENT '以卖出数',
  hw_date datetime DEFAULT NULL COMMENT '录入时间',
  hw_pic varchar(255) DEFAULT NULL COMMENT '图片',
  commend char(1) DEFAULT NULL,
  status int(1) DEFAULT NULL COMMENT '默认是1，表示正常状态，如果管理员发现有非法图片，可以关闭该图片，设置为0即可',
  modify_date datetime DEFAULT NULL COMMENT '修改时间',
  user_id varchar(32) DEFAULT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '所属用户名称',
  start_date datetime DEFAULT NULL COMMENT '开始有效时间',
  end_date datetime DEFAULT NULL COMMENT '结束有效时间',
  stocks int(11) DEFAULT '0' COMMENT '库存量',
  hw_type char(1) NOT NULL DEFAULT '1' COMMENT '商品类型，1.普通商品，2团购商品，3打折商品，4二手商品',
  key_word varchar(255) DEFAULT NULL COMMENT '关键字',
  attribute text COMMENT '产品动态属性',
  parameter text COMMENT '商品动态参数',
  brand_id int(11) DEFAULT NULL COMMENT '品牌',
  actual_stocks int(11) DEFAULT '0' COMMENT '实际库存',
  PRIMARY KEY (hw_id),
  UNIQUE KEY hw_id_username (hw_id,user_name),
  KEY nsort_id (nsort_id),
  KEY sort_id (sort_id)
) ENGINE=InnoDB AUTO_INCREMENT=515 DEFAULT CHARSET=utf8 COMMENT='商品';
CREATE TABLE ls_hw_comm (
  ID int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  hw_id int(10) DEFAULT NULL COMMENT '商品ID',
  owner_Name varchar(50) NOT NULL COMMENT '商品所有人',
  user_name varchar(50) NOT NULL COMMENT '点评人ID',
  content varchar(300) NOT NULL COMMENT '点评内容',
  addtime datetime NOT NULL COMMENT '添加时间',
  postip varchar(16) DEFAULT NULL COMMENT 'IP来源',
  score smallint(6) DEFAULT NULL COMMENT '得分，0-5分',
  reply_content varchar(300) DEFAULT NULL COMMENT '回复内容',
  reply_name varchar(50) DEFAULT NULL COMMENT '回复人',
  reply_time datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (ID),
  KEY comment_hw_id (hw_id)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品点评';
CREATE TABLE ls_img_file (
  file_id int(11) NOT NULL AUTO_INCREMENT,
  user_name varchar(32) DEFAULT NULL,
  product_id int(11) DEFAULT NULL,
  product_type tinyint(3) DEFAULT NULL COMMENT '文件类型',
  file_path varchar(500) DEFAULT NULL COMMENT '文件路径',
  file_type varchar(16) DEFAULT NULL COMMENT '文件类型',
  file_size int(11) DEFAULT NULL COMMENT '文件大小',
  upoad_time datetime DEFAULT NULL,
  status tinyint(3) DEFAULT NULL,
  PRIMARY KEY (file_id),
  KEY img_file_index (product_type,user_name,product_id,status)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8 COMMENT='上传的文件表';
CREATE TABLE ls_index_jpg (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  href varchar(200) NOT NULL COMMENT '连接地址',
  img varchar(200) NOT NULL COMMENT '图片',
  alt varchar(200) NOT NULL COMMENT '说明文字',
  title varchar(200) NOT NULL COMMENT '标题',
  stitle varchar(200) NOT NULL COMMENT '小标题',
  link varchar(200) NOT NULL COMMENT '连接图片',
  titleLink varchar(200) NOT NULL COMMENT '文字连接图片',
  user_id varchar(32) DEFAULT NULL,
  user_name varchar(100) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (id),
  KEY indexjpg_user_name (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='主页轮换图片';
CREATE TABLE ls_league (
  ID int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id varchar(32) DEFAULT NULL COMMENT '用户ID',
  friend_id varchar(24) DEFAULT NULL COMMENT '好友ID',
  friend_name varchar(100) DEFAULT NULL COMMENT '好友名称',
  display_order int(10) DEFAULT NULL COMMENT '排序',
  status int(2) DEFAULT NULL COMMENT '1：已经成为好友，2：申请中，3：对方拒绝，当通知到用户的时候把该记录删除',
  addtime datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (ID),
  UNIQUE KEY unique_user_friend_id (user_id,friend_id),
  KEY FK_league2user (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='我的加盟店，店面之间的产品可以互相展现对方的产品';
CREATE TABLE ls_login_hist (
  ID int(32) NOT NULL AUTO_INCREMENT,
  AREA varchar(100) DEFAULT NULL,
  COUNTRY varchar(100) DEFAULT NULL,
  USER_NAME varchar(120) DEFAULT NULL,
  IP char(16) DEFAULT NULL,
  TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY ID (ID)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='登录历史表';
CREATE TABLE ls_logo (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  banner varchar(300) DEFAULT NULL,
  url varchar(50) DEFAULT NULL COMMENT '连接地址',
  user_id varchar(32) NOT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '用户名称,备用',
  memo varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY user_id (user_id),
  UNIQUE KEY logo_user_name_ind (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='主广告位表';
CREATE TABLE ls_news (
  news_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻ID',
  news_category_id int(10) DEFAULT NULL,
  sort int(11) DEFAULT NULL,
  news_title varchar(100) DEFAULT NULL COMMENT '新闻标题',
  news_brief varchar(300) DEFAULT NULL COMMENT '新闻提要，保存是截取新闻前100个字',
  news_content text COMMENT '新闻内容',
  news_date datetime DEFAULT NULL COMMENT '发表时间',
  status tinyint(1) NOT NULL DEFAULT '1',
  high_line tinyint(1) DEFAULT '0' COMMENT '1:yest,0:no',
  user_id varchar(32) DEFAULT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '所属用户名称',
  PRIMARY KEY (news_id),
  KEY news_category_id (news_category_id),
  KEY sort (sort),
  KEY user_name (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COMMENT='新闻';
CREATE TABLE ls_news_cat (
  news_category_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻栏目ID',
  news_category_name varchar(100) NOT NULL COMMENT '新闻栏目名称',
  status tinyint(4) NOT NULL,
  news_category_date datetime NOT NULL COMMENT '发表时间',
  user_id varchar(32) NOT NULL COMMENT '用户ID',
  user_name varchar(100) DEFAULT NULL COMMENT '所属用户名称',
  PRIMARY KEY (news_category_id),
  KEY FK_news_category_to_user (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='新闻栏目';
CREATE TABLE ls_ns_brand (
  nsort_id int(11) NOT NULL COMMENT '三级商品类型的ID',
  brand_id int(11) NOT NULL DEFAULT '0' COMMENT '品牌ID',
  user_name varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (brand_id,nsort_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三级类型和品牌的对照表';
CREATE TABLE ls_nsort (
  nsort_id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  nsort_name varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  seq int(5) DEFAULT NULL COMMENT '排序',
  sort_id int(11) unsigned NOT NULL DEFAULT '0' COMMENT '对应的一级类型',
  parent_nsort_id int(11) DEFAULT NULL COMMENT '父节点ID',
  PRIMARY KEY (nsort_id),
  KEY sort_id (sort_id),
  KEY parent_nsort_id (parent_nsort_id)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8 COMMENT='专柜，就是商品子类表';
CREATE TABLE ls_pay_type (
  pay_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_name varchar(32) NOT NULL,
  pay_type_id int(2) NOT NULL,
  pay_type_name varchar(50) DEFAULT NULL COMMENT '付款方式',
  partner varchar(100) DEFAULT NULL COMMENT 'partner',
  validate_key varchar(512) DEFAULT NULL,
  seller_email varchar(100) DEFAULT NULL COMMENT '签约支付宝账号或卖家收款支付宝帐户',
  memo varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (pay_id),
  UNIQUE KEY uni_user_name_pay_type_id (user_name,pay_type_id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='付款方式';
CREATE TABLE ls_perm (
  ROLE_ID varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  FUNCTION_ID varchar(32) NOT NULL DEFAULT '' COMMENT '功能ID',
  PRIMARY KEY (ROLE_ID,FUNCTION_ID),
  KEY FUNCTION_ID (FUNCTION_ID),
  KEY ROLE_ID (ROLE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
CREATE TABLE ls_provinces (
  id int(11) NOT NULL AUTO_INCREMENT,
  provinceid varchar(20) NOT NULL,
  province varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
CREATE TABLE ls_pub (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  msg varchar(255) NOT NULL COMMENT '内容',
  rec_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  user_id varchar(32) NOT NULL,
  user_name varchar(100) DEFAULT NULL COMMENT '用户名称,备用',
  PRIMARY KEY (id),
  UNIQUE KEY id (id)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='public';
CREATE TABLE ls_rel_hw (
  relation_id int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  hw_id int(10) DEFAULT NULL COMMENT '商品ID',
  relation_hw_id int(11) DEFAULT NULL COMMENT '相关产品ID',
  relation_hw_name varchar(100) DEFAULT NULL COMMENT '相关产品名称',
  addtime datetime DEFAULT NULL COMMENT '添加时间',
  user_name varchar(32) NOT NULL DEFAULT '' COMMENT '商品对应的用户名',
  PRIMARY KEY (relation_id)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COMMENT='相关产品';
CREATE TABLE ls_role (
  ID varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  NAME varchar(100) NOT NULL COMMENT '名称',
  ROLE_TYPE varchar(100) NOT NULL COMMENT '角色类型',
  ENABLED varchar(1) NOT NULL COMMENT '状态',
  NOTE varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
CREATE TABLE ls_score (
  score_id int(11) NOT NULL AUTO_INCREMENT,
  sub_id int(11) DEFAULT NULL COMMENT '订单ID',
  score int(11) NOT NULL COMMENT '用户积分',
  score_type char(1) NOT NULL COMMENT '积分类型',
  user_name varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户',
  rec_date datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (score_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE ls_shop_detail (
  shop_id int(11) NOT NULL AUTO_INCREMENT,
  user_id varchar(32) NOT NULL,
  web varchar(50) DEFAULT NULL COMMENT 'web地址',
  sitename varchar(50) DEFAULT NULL COMMENT '站点名称',
  maddr varchar(300) DEFAULT NULL COMMENT '银行卡',
  msn varchar(50) DEFAULT NULL COMMENT '银行汇款帐号',
  mname varchar(50) DEFAULT NULL COMMENT '收款人姓名',
  code varchar(100) DEFAULT NULL COMMENT '邮政编码',
  ymaddr varchar(300) DEFAULT NULL COMMENT '邮局汇款地址',
  ymname varchar(100) DEFAULT NULL COMMENT '邮递接收人',
  status int(2) DEFAULT NULL COMMENT '状态,是否上线1：在线，0下线，-1审核中,-2拒绝,-3关闭（管理员操作）',
  store_name varchar(100) DEFAULT NULL COMMENT '店名',
  visit_times int(10) NOT NULL DEFAULT '0' COMMENT '访问人数',
  product_num int(11) NOT NULL DEFAULT '0' COMMENT '产品数量',
  comm_num int(11) NOT NULL DEFAULT '0' COMMENT '评论个数',
  off_product_num int(11) NOT NULL DEFAULT '0' COMMENT '下线产品数量',
  modify_time datetime NOT NULL COMMENT '修改时间',
  addtime datetime NOT NULL COMMENT '创建时间',
  brief_desc varchar(300) DEFAULT NULL COMMENT '店铺简述',
  detail_desc varchar(4000) DEFAULT NULL COMMENT '店铺详细描述',
  shop_pic varchar(255) DEFAULT NULL COMMENT '店铺图片',
  color_style varchar(15) DEFAULT NULL COMMENT '网页风格',
  lang_style varchar(15) DEFAULT NULL COMMENT '语言选项，英语en_us,中文zh_cn',
  grade_id int(10) DEFAULT NULL COMMENT '商铺等级',
  type int(1) NOT NULL COMMENT '店铺类型0：个人用户，1：商家用户',
  id_card_pic varchar(250) DEFAULT NULL COMMENT '身份证验证图片',
  traffic_pic varchar(250) DEFAULT NULL COMMENT '营业执照图片',
  id_card_num varchar(20) DEFAULT NULL COMMENT '身份证号码',
  create_country_code varchar(50) DEFAULT NULL COMMENT '创建时的国家码',
  create_area_code varchar(50) DEFAULT NULL COMMENT '创建时的地区',
  provinceid int(11) DEFAULT NULL COMMENT '省份',
  cityid int(11) DEFAULT NULL COMMENT '城市',
  areaid int(11) DEFAULT NULL COMMENT '地级市',
  PRIMARY KEY (shop_id),
  UNIQUE KEY shop_id (shop_id),
  UNIQUE KEY user_id (user_id),
  UNIQUE KEY shop_detail_shop_name (store_name)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';
CREATE TABLE ls_sort (
  sort_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  sort_name varchar(50) NOT NULL,
  picture varchar(250) DEFAULT NULL COMMENT '分类的显示图片',
  seq int(11) DEFAULT NULL,
  user_id varchar(32) NOT NULL,
  user_name varchar(100) NOT NULL,
  PRIMARY KEY (sort_id),
  UNIQUE KEY sort_name (sort_name,sort_id)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='展柜，商品分类';
CREATE TABLE ls_sub (
  sub_id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订购ID',
  basket_id varchar(1024) DEFAULT NULL,
  hw_name varchar(2000) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL COMMENT '订购用户名称',
  order_name varchar(50) DEFAULT NULL COMMENT '表单中填写的接收人',
  sub_date datetime DEFAULT NULL COMMENT '订购时间',
  pay_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  sub_number varchar(50) NOT NULL COMMENT '订购流水号',
  sub_check char(1) DEFAULT NULL COMMENT '订购审批状态，True：审批通过，False：还没有审批',
  total double(15,2) NOT NULL DEFAULT '0.00' COMMENT '总值',
  actual_total double(15,3) DEFAULT NULL COMMENT '实际总值',
  sub_mail varchar(50) DEFAULT NULL COMMENT '邮箱',
  sub_tel varchar(50) DEFAULT NULL COMMENT '电话',
  pay_id int(10) DEFAULT NULL COMMENT '付款方式',
  pay_type_id int(2) DEFAULT NULL,
  pay_type_name varchar(30) DEFAULT NULL,
  sub_adds varchar(255) DEFAULT NULL COMMENT '地址',
  sub_post varchar(15) DEFAULT NULL COMMENT '邮编',
  other varchar(1024) DEFAULT NULL COMMENT '其他备注',
  shop_name varchar(50) NOT NULL,
  status int(2) DEFAULT NULL,
  score_id int(11) DEFAULT NULL COMMENT '鏄惁宸茬粡浣跨敤绉垎',
  score int(11) DEFAULT NULL COMMENT '使用积分数',
  trade_no varchar(30) DEFAULT NULL COMMENT '支付宝交易号',
  PRIMARY KEY (sub_id),
  UNIQUE KEY sub_number_unique_ind (sub_number)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='订购表';
CREATE TABLE ls_sub_hist (
  sub_hist_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  sub_id int(11) unsigned NOT NULL COMMENT '订购ID',
  basket_id varchar(1024) DEFAULT NULL,
  hw_name varchar(2000) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL COMMENT '订购用户名称',
  order_name varchar(50) DEFAULT NULL COMMENT '表单中填写的接收人',
  sub_date datetime DEFAULT NULL COMMENT '订购时间',
  pay_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  sub_number varchar(50) NOT NULL COMMENT '订购流水号',
  sub_check char(1) DEFAULT NULL COMMENT '订购审批状态，True：审批通过，False：还没有审批',
  total double(15,2) NOT NULL DEFAULT '0.00' COMMENT '总值',
  actual_total double(15,3) DEFAULT NULL COMMENT '瀹炰粯鏁伴噺',
  sub_mail varchar(50) DEFAULT NULL COMMENT '閭',
  sub_tel varchar(50) DEFAULT NULL COMMENT '电话',
  pay_id int(10) DEFAULT NULL COMMENT '付款方式',
  pay_type_id int(2) DEFAULT NULL,
  pay_type_name varchar(30) DEFAULT NULL,
  sub_adds varchar(255) DEFAULT NULL COMMENT '地址',
  sub_post varchar(15) DEFAULT NULL COMMENT '邮编',
  other varchar(1024) DEFAULT NULL COMMENT '其他备注',
  shop_name varchar(50) NOT NULL,
  status int(2) DEFAULT NULL,
  score_id int(11) DEFAULT NULL COMMENT '绉垎ID',
  score int(11) DEFAULT NULL COMMENT '使用积分数',
  update_user varchar(50) DEFAULT NULL COMMENT '修改人',
  update_time datetime DEFAULT NULL COMMENT '修改时间',
  sub_action char(2) DEFAULT NULL COMMENT '淇敼鍔ㄤ綔',
  trade_no varchar(30) DEFAULT NULL COMMENT '支付宝交易号',
  PRIMARY KEY (sub_hist_id)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='订购历史表';
CREATE TABLE ls_sys_param (
  name varchar(100) NOT NULL COMMENT '系统配置名称',
  value varchar(200) DEFAULT '' COMMENT '系统配置值',
  memo varchar(100) NOT NULL COMMENT '配置说明',
  type varchar(30) NOT NULL DEFAULT '' COMMENT '对应JAVA类型',
  optional varchar(255) DEFAULT '' COMMENT '如果不填写的默认值',
  change_online char(1) DEFAULT NULL COMMENT '是否可以线上修改',
  display_order int(11) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';
CREATE TABLE ls_user (
  ID varchar(32) NOT NULL,
  NAME varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  PASSWORD varchar(100) NOT NULL COMMENT '密码',
  ENABLED varchar(1) NOT NULL COMMENT '状态',
  NOTE varchar(255) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (ID),
  UNIQUE KEY INDEX_USER_NAME (NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
CREATE TABLE ls_usr_comm (
  ID int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  comment_type int(10) DEFAULT NULL COMMENT '留言类型,1:投诉，2：普通谈话',
  user_id varchar(32) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL COMMENT '发起谈话的用户名称',
  your_name char(10) DEFAULT NULL COMMENT '用户名称，由用户填写',
  to_user_name varchar(100) DEFAULT NULL COMMENT '通话对象',
  grade_id int(10) DEFAULT NULL COMMENT '商铺等级',
  content varchar(1000) DEFAULT NULL COMMENT '留言内容',
  answer varchar(1000) DEFAULT NULL COMMENT '回答',
  addtime datetime DEFAULT NULL COMMENT '添加时间',
  postip varchar(16) DEFAULT NULL COMMENT 'IP来源',
  status int(10) DEFAULT '0' COMMENT '阅读状态，0：未读，1：已读',
  answertime datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_usercomment (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的留言';
CREATE TABLE ls_usr_detail (
  user_id varchar(32) NOT NULL,
  grade_id int(10) DEFAULT NULL COMMENT '等级ID',
  user_name varchar(50) NOT NULL COMMENT '用户名称',
  nick_name varchar(50) DEFAULT NULL COMMENT '用户昵称',
  user_mail varchar(50) NOT NULL COMMENT '用户邮件',
  user_adds varchar(255) DEFAULT '' COMMENT '用户地址',
  user_tel varchar(50) DEFAULT NULL COMMENT '电话',
  user_mobile varchar(50) DEFAULT NULL,
  user_postcode varchar(50) DEFAULT NULL COMMENT '用户邮编',
  msn varchar(100) DEFAULT NULL COMMENT 'msn',
  qq varchar(50) DEFAULT NULL COMMENT 'qq号码',
  fax varchar(50) DEFAULT NULL COMMENT '传真',
  modify_time datetime NOT NULL COMMENT '修改时间',
  user_regtime datetime NOT NULL COMMENT '注册时间',
  user_regip varchar(50) NOT NULL COMMENT '注册IP',
  user_lasttime datetime DEFAULT NULL COMMENT '最后登录时间',
  user_lastip varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  user_memo varchar(500) DEFAULT NULL COMMENT '备注',
  sex char(1) DEFAULT 'M' COMMENT 'M(男) or F(女)',
  birth_date char(8) DEFAULT NULL COMMENT '例如：20100918',
  register_code varchar(200) DEFAULT NULL COMMENT '注册时生成的注册码',
  score int(11) DEFAULT NULL COMMENT '鐢ㄦ埛绉垎',
  PRIMARY KEY (user_id),
  UNIQUE KEY user_name (user_name),
  UNIQUE KEY user_name_mail_unique (user_name,user_mail),
  UNIQUE KEY user_mail (user_mail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细表';
CREATE TABLE ls_usr_grad (
  grade_id int(10) NOT NULL COMMENT '等级ID',
  name varchar(50) DEFAULT NULL COMMENT '名称',
  max_sort_size int(10) DEFAULT NULL COMMENT '允许的最大展柜',
  max_nsort_size int(10) DEFAULT NULL COMMENT '允许的最大展柜分类',
  max_hw int(10) DEFAULT NULL COMMENT '允许的最大产品数',
  PRIMARY KEY (grade_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户等级\r\n1幼儿园\r\n2小学\r\n3中学\r\n4大学\r\n5硕士';
CREATE TABLE ls_usr_role (
  USER_ID varchar(32) NOT NULL,
  ROLE_ID varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (ROLE_ID,USER_ID),
  KEY ROLE_ID (ROLE_ID),
  KEY USER_ID (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';
CREATE TABLE ls_vit_log (
  visit_id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  ip char(16) NOT NULL COMMENT 'IP',
  country varchar(100) DEFAULT NULL COMMENT '获得IP所在国家，如果在中国，直接显示省市',
  area varchar(100) DEFAULT NULL COMMENT '获得IP所在区域',
  user_name varchar(32) DEFAULT NULL COMMENT '用户名',
  shop_name varchar(32) DEFAULT NULL COMMENT '商铺名称',
  product_id varchar(32) DEFAULT NULL COMMENT '商品ID',
  product_name varchar(200) DEFAULT NULL COMMENT '产品名称',
  page varchar(200) NOT NULL COMMENT '访问页面',
  rec_date datetime DEFAULT '0000-00-00 00:00:00' COMMENT '访问时间',
  visit_num int(11) DEFAULT NULL COMMENT '访问次数',
  PRIMARY KEY (visit_id),
  KEY lgs_visit_log_visited_ind (ip,shop_name,rec_date,product_id)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='客户浏览历史';
CREATE TABLE ls_zipcode (
  id int(11) NOT NULL AUTO_INCREMENT,
  areaid varchar(20) NOT NULL,
  zip varchar(20) NOT NULL,
  code varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3145 DEFAULT CHARSET=utf8;
CREATE VIEW ls_hw_detail AS select hw.hw_id AS hw_id,hw.sort_id AS sort_id,hw.nsort_id AS nsort_id,hw.sub_nsort_id AS sub_nsort_id,hw.model_id AS model_id,hw.hw_name AS hw_name,hw.hw_price AS hw_price,hw.hw_cash AS hw_cash,hw.proxy_price AS proxy_price,hw.carriage AS carriage,hw.hw_brief AS hw_brief,hw.hw_content AS hw_content,hw.hw_views AS hw_views,hw.hw_buys AS hw_buys,hw.hw_date AS hw_date,hw.hw_pic AS hw_pic,hw.commend AS commend,hw.status AS status,hw.modify_date AS modify_date,hw.user_id AS user_id,hw.user_name AS user_name,hw.start_date AS start_date,hw.end_date AS end_date,hw.stocks AS stocks,hw.hw_type AS hw_type,hw.key_word AS key_word,hw.attribute AS attribute,hw.parameter AS parameter,hw.brand_id AS brand_id,sort.sort_name AS sort_name,nsort2.nsort_name AS nsort_name,nsort3.nsort_name AS sub_nsort_name,brand.brand_name AS brand_name from ((((ls_hw hw left join ls_sort sort on((hw.sort_id = sort.sort_id))) left join ls_nsort nsort2 on((hw.nsort_id = nsort2.nsort_id))) left join ls_nsort nsort3 on((hw.sub_nsort_id = nsort3.nsort_id))) left join ls_brand brand on((hw.brand_id = brand.brand_id)));
CREATE VIEW ls_shd_view AS select sd.shop_id AS shop_id,sd.user_id AS user_id,sd.web AS web,sd.sitename AS sitename,sd.maddr AS maddr,sd.msn AS msn,sd.mname AS mname,sd.code AS code,sd.ymaddr AS ymaddr,sd.ymname AS ymname,sd.status AS status,sd.store_name AS store_name,sd.visit_times AS visit_times,sd.product_num AS product_num,sd.comm_num AS comm_num,sd.off_product_num AS off_product_num,sd.modify_time AS modify_time,sd.addtime AS addtime,sd.brief_desc AS brief_desc,sd.detail_desc AS detail_desc,sd.shop_pic AS shop_pic,sd.color_style AS color_style,sd.lang_style AS lang_style,sd.grade_id AS grade_id,sd.type AS type,sd.id_card_pic AS id_card_pic,sd.traffic_pic AS traffic_pic,sd.id_card_num AS id_card_num,sd.create_country_code AS create_country_code,sd.create_area_code AS create_area_code,sd.provinceid AS provinceid,sd.cityid AS cityid,sd.areaid AS areaid,p.province AS province,c.city AS city,a.area AS area,u.user_tel AS user_tel,u.nick_name AS nick_name,u.user_mobile AS user_mobile,u.qq AS qq,u.msn AS msnNumber,u.user_postcode AS user_postcode,u.fax AS fax from ((((ls_shop_detail sd left join ls_usr_detail u on((sd.user_id = u.user_id))) left join ls_provinces p on((sd.provinceid = p.provinceid))) left join ls_cities c on((sd.cityid = c.cityid))) left join ls_areas a on((sd.areaid = a.areaid)));
CREATE VIEW ls_usr_view AS select u.user_id AS user_id,u.grade_id AS grade_id,u.user_name AS user_name,u.nick_name AS nick_name,u.user_mail AS user_mail,u.user_adds AS user_adds,u.user_tel AS user_tel,u.user_mobile AS user_mobile,u.user_postcode AS user_postcode,u.msn AS msn,u.qq AS qq,u.fax AS fax,u.modify_time AS modify_time,u.user_regtime AS user_regtime,u.user_regip AS user_regip,u.user_lasttime AS user_lasttime,u.user_lastip AS user_lastip,u.user_memo AS user_memo,u.sex AS sex,u.birth_date AS birth_date,u.register_code AS register_code,s.shop_id AS shop_id from (ls_usr_detail u left join ls_shop_detail s on((u.user_id = s.user_id)));