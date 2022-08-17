/*commodity数据库的创建*/
CREATE DATABASE commodity;

USE commodity;

CREATE TABLE IF NOT EXISTS `commodity_with_classify` (
  `cc_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commodity_code` VARCHAR(20) NOT NULL COMMENT '商品编码',
  `classify_id` INT NOT NULL COMMENT '分类id',
  PRIMARY KEY (`cc_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `commodity_with_vendor`  (
  `cv_id` INT NOT NULL AUTO_INCREMENT COMMENT '商品和供应商的编码',
  `commodity_code` VARCHAR(20) NOT NULL,
  `vendor_code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cv_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_commodity`  (
  `commodity_code` VARCHAR(20)  COMMENT '商品编码' NOT NULL,
  `commodity_name` VARCHAR(50)  COMMENT '商品名称' NOT NULL,
  `specification` VARCHAR(255)  COMMENT '商品规格' NULL,
  `model` VARCHAR(20)  COMMENT '商品型号' NULL,
  `unit` VARCHAR(10)  COMMENT '商品计量单位' NULL,
  `market_price` DECIMAL(10, 2)  COMMENT '商品市场价' NULL,
  `sales_price` DECIMAL(10, 2)  COMMENT '商品销售价' NULL,
  `cost_price` DECIMAL(10, 2)  COMMENT '商品成本价' NULL,
  `commodity_url` VARCHAR(100)  COMMENT '商品缩略图路径' NULL,
  `commodity_introduce` VARCHAR(255)  COMMENT '商品介绍' NULL,
  `inventory_count` BIGINT COMMENT '商品库存量' NULL,
  `brand_code` VARCHAR(20) COMMENT '商品品牌编码' NULL,
  PRIMARY KEY (`commodity_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_commodity_brand`  (
  `brand_code` VARCHAR(20) COMMENT '商品品牌编码' NOT NULL,
  `brand_name` VARCHAR(50) COMMENT '商品品牌名' NOT NULL,
  `brand_url` VARCHAR(100) COMMENT '商品品牌网站' NULL,
  `brand_img_url` VARCHAR(100) COMMENT '商品品牌图片地址' NULL,
  PRIMARY KEY (`brand_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_commodity_classify`  (
  `classify_id` INT NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `img_url` VARCHAR(100) COMMENT '商品分类图片路径' NULL,
  `name` VARCHAR(100) COMMENT '商品分类的名字' NOT NULL,
  PRIMARY KEY (`classify_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_commodity_vendor`  (
  `vendor_code` VARCHAR(20) COMMENT '供货商编码' NOT NULL,
  `vendor_name` VARCHAR(50) COMMENT '供货商名' NOT NULL,
  `contact` VARCHAR(20) COMMENT '联系人' NOT NULL,
  `phone` VARCHAR(20) COMMENT '联系电话' NOT NULL,
  `brief_introduction` VARCHAR(255) COMMENT '供货商简介' NULL,
  PRIMARY KEY (`vendor_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_order`  (
  `order_code` VARCHAR(100) COMMENT '订单编号' NOT NULL,
  `zip` VARCHAR(100) COMMENT '邮编' NOT NULL,
  `order_time` DATETIME COMMENT '订单时间' NOT NULL,
  `order_amount` DECIMAL(10, 2) COMMENT '订单总金额' NULL,
  `user_code` VARCHAR(20) COMMENT '用户编码' NOT NULL,
  PRIMARY KEY (`order_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
`c_v`
CREATE TABLE `t_order_details`  (
  `details_code` VARCHAR(20) COMMENT '订单明细编码' NOT NULL,
  `unit_price` DECIMAL(10, 2) NULL COMMENT '商品单价',
  `count` INT NOT NULL COMMENT '商品数量',
  `amount` DECIMAL(10, 2) COMMENT '商品金额' NULL,
  `order_code` VARCHAR(20) NOT NULL COMMENT '订单编号',
  `commodity_code` VARCHAR(20) NOT NULL COMMENT '商品编号',
  PRIMARY KEY (`details_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `t_user`  (
  `user_code` VARCHAR(20) COMMENT '用户编码' NOT NULL,
  `user_name` VARCHAR(20) COMMENT '用户姓名' NOT NULL,
  `phone` VARCHAR(20) COMMENT '用户电话' NULL,
  `address` VARCHAR(255) COMMENT '用户地址' NULL,
  PRIMARY KEY (`user_code`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

ALTER TABLE `commodity_with_classify` ADD CONSTRAINT `commodity_code_r` FOREIGN KEY (`commodity_code`) REFERENCES `t_commodity` (`commodity_code`);
ALTER TABLE `commodity_with_classify` ADD CONSTRAINT `classify_id_r` FOREIGN KEY (`classify_id`) REFERENCES `t_commodity_classify` (`classify_id`);
ALTER TABLE `commodity_with_vendor` ADD CONSTRAINT `commodity_code_r` FOREIGN KEY (`commodity_code`) REFERENCES `t_commodity` (`commodity_code`);
ALTER TABLE `commodity_with_vendor` ADD CONSTRAINT `vendor_code_r` FOREIGN KEY (`vendor_code`) REFERENCES `t_commodity_vendor` (`vendor_code`);
ALTER TABLE `t_commodity` ADD CONSTRAINT `brand_code_r` FOREIGN KEY (`brand_code`) REFERENCES `t_commodity_brand` (`brand_code`);
ALTER TABLE `t_order` ADD CONSTRAINT `user_code_r` FOREIGN KEY (`user_code`) REFERENCES `t_user` (`user_code`);
ALTER TABLE `t_order_details` ADD CONSTRAINT `order_code_r` FOREIGN KEY (`order_code`) REFERENCES `t_order` (`order_code`);
ALTER TABLE `t_order_details` ADD CONSTRAINT `commodity_code_r` FOREIGN KEY (`commodity_code`) REFERENCES `t_commodity` (`commodity_code`);

/*视图的创建*/
CREATE VIEW c_v AS (SELECT commodity_name,specification,model,t_user.user_code,user_name,`count`,amount
FROM t_order_details,t_user,t_commodity,t_order WHERE t_order_details.`commodity_code`=t_commodity.`commodity_code` AND 
t_order.`order_code`=t_order_details.`order_code` AND t_user.`user_code` = t_order.`user_code`)


INSERT INTO `t_user`(user_code,user_name,phone,address)
VALUES
('019_00101','张三','13215647896','重庆市江津区创建交通大学德园1舍'),
('019_00102','李四','13896969898','重庆市江津区创建交通大学德园2舍'),
('019_00103','王五','15569699894','重庆市江津区创建交通大学德园3舍'),
('019_00104','赵六','15623568478','重庆市江津区创建交通大学德园4舍'),
('019_00105','吴七','13658984545','重庆市江津区创建交通大学德园5舍');

INSERT INTO `t_commodity_vendor`(vendor_code,vendor_name,contact,phone,brief_introduction)
VALUES
('BJ_GYS001','北京三木堂商贸有限公司','张国强','13566667777','地址：北京市丰台区育芳园北路,长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等'),
('HB_GYS001','石家庄帅益食品贸易有限公司','王军','13309094212','地址：河北省石家庄新华区，长期合作伙伴，主营产品:饮料、水饮料、植物蛋白饮料、休闲食品、果汁饮料、功能饮料等'),
('GZ_GYS001','深圳市泰香米业有限公司','郑程瀚','13402013312','地址：广东省深圳市福田区深南大道6006华丰大厦,初次合作伙伴，主营产品：良记金轮米,龙轮香米等'),
('GZ_GYS002','深圳市喜来客商贸有限公司','林妮','18599897645','地址：广东省深圳市福龙工业区B2栋3楼西,长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉');


INSERT INTO `t_commodity_brand`(brand_code,brand_name,brand_url,brand_img_url)
VALUES
('MOUTAI','茅台','http://www,moutaichina.com/',NULL),
('cola','可乐','http://www.coca-coal,com,cn/',NULL),
('jinlongyu','金龙鱼大米','http://www.jinlongyu,cn',NULL),
('ThreeSquirrels','三只松鼠','http://www.3songshu.com/',NULL);
INSERT INTO `t_commodity_brand`(brand_code,brand_name,brand_url,brand_img_url)
VALUES('Mizone','脉动','http://www.mizone.com/',NULL);

INSERT INTO `t_commodity_classify` (`name`,`img_url`)
VALUES
('酒水',NULL),
('饮料',NULL),
('粮食',NULL),
('水果',NULL),
('蔬菜',NULL),
('零食',NULL);


INSERT INTO `t_commodity`(commodity_code,commodity_name,specification,model,unit,market_price,sales_price,cost_price,commodity_url,commodity_introduce,inventory_count,brand_code)
VALUES
('BILL2202_001','可乐','1.25L','A','瓶',10,8,6,NULL,'一种能提神,解乏,治头疼的混合饮料',1000,'cola'),
('BILL2202_002','大可乐','2L','C','瓶',15,10,8,NULL,'一种能提神,解乏,治头疼的混合饮料',2000,'cola'),
('BILL2202_003','脉动','600ml','A','瓶',5,14,3,NULL,'一种维生素饮料',1500,'Mizone'),
('BILL2202_004','茅台','500ml','D','瓶',900,800,600,NULL,'茅台酒是大曲酱香型酒的鼻祖,它具有色清透明、酱香突出、醇香馥郁、幽雅细腻、入口柔绵、清冽甘爽、酒体醇厚丰满、回味悠长',8000,'MOUTAI'),
('BILL2202_005','炭烧腰果','185g','F','袋',23.9,20.23,18.17,NULL,'浓香酥脆,营养健康,三重锁鲜',4000,'ThreeSquirrels'),
('BILL2202_006','手剥巴旦木','120g','F','袋',36,32,29,NULL,'均匀饱满,壳薄易剥,香酥滑嫩',5500,'ThreeSquirrels'),
('BILL2202_007','御品稻花香大米','5kg','F','袋',91.23,89.2,75.2,NULL,'香甜柔弱,口感好,粒粒饱满',500,'jinlongyu'),
('BILL2202_008','五常大米','5kg','F','袋',69.9,65.3,60.4,NULL,'清香软糯,米香四溢,颗粒饱满',700,'jinlongyu');
INSERT INTO `t_commodity`(commodity_code,commodity_name,specification,model,unit,market_price,sales_price,cost_price,commodity_url,commodity_introduce,inventory_count,brand_code)
VALUES
('BILL2202_010','大白菜','250g-400g','F','斤',4.56,3.2,2.5,NULL,'一种可以补充维生素的蔬菜，绿色食品',4500,NULL);

INSERT INTO `commodity_with_classify`(commodity_code,classify_id)
VALUES
('BILL2202_001',2),
('BILL2202_002',2),
('BILL2202_003',2),
('BILL2202_004',1),
('BILL2202_005',6),
('BILL2202_006',6),
('BILL2202_007',3),
('BILL2202_008',3);


INSERT INTO `commodity_with_vendor`(commodity_code,vendor_code)
VALUES
('BILL2202_004','BJ_GYS001'),
('BILL2202_004','HB_GYS001'),
('BILL2202_007','GZ_GYS002'),
('BILL2202_008','GZ_GYS002'),
('BILL2202_007','GZ_GYS001'),
('BILL2202_008','GZ_GYS001'),
('BILL2202_005','GZ_GYS001'),
('BILL2202_006','GZ_GYS001'),
('BILL2202_006','GZ_GYS002'),
('BILL2202_001','BJ_GYS001'),
('BILL2202_002','BJ_GYS001'),
('BILL2202_003','BJ_GYS001'),
('BILL2202_001','GZ_GYS001'),
('BILL2202_002','HB_GYS001');

INSERT INTO `t_order`(order_code,zip,order_time,order_amount,user_code)
VALUES
('16464760757381','402260','2022-03-05 18:27:56',992,'019_00101'),/*10*2+900+36*2*/
('16464762452531','402260','2022-03-05 18:30:45',270.26,'019_00102'),/*5*8+23.9*2+91.23*2*/
('16465440457325','402260','2022-03-06 13:20:46',3054.3,'019_00103'),/*15*5+900*3+69.9*4*/
('16465440954956','402260','2022-03-06 13:21:35',101.23,'019_00104'),/*5*2+91.23*/
('16465441297157','402260','2022-03-06 13:22:10',1151.03,'019_00105');
INSERT INTO `t_order`(order_code,zip,order_time,order_amount,user_code)
VALUES
('16465589745841','402260','2022-03-06 21:21:21',101.23,'019_00103');


INSERT INTO `t_order_details`(details_code,unit_price,`count`,`amount`,order_code,commodity_code)
VALUES
('SSY_16464760757381_001',10,2,20,'16464760757381','BILL2202_001'),
('SSY_16464760757381_002',900,1,900,'16464760757381','BILL2202_004'),
('SSY_16464760757381_003',36,2,72,'16464760757381','BILL2202_005'),
('SSY_16464762452531_001',5,8,40,'16464762452531','BILL2202_003'),
('SSY_16464762452531_002',23.9,2,47.8,'16464762452531','BILL2202_005'),
('SSY_16464762452531_003',91.23,2,182.46,'16464762452531','BILL2202_007'),
('SSY_16465440457325_001',15,5,75,'16465440457325','BILL2202_002'),
('SSY_16465440457325_002',900,3,2700,'16465440457325','BILL2202_004'),
('SSY_16465440457325_003',69.9,4,276.6,'16465440457325','BILL2202_008'),
('SSY_16465440954956_001',5,2,10,'16465440954956','BILL2202_003'),
('SSY_16465440954956_002',91.23,1,91.23,'16465440954956','BILL2202_007'),
('SSY_16465441297157_001',10,1,10,'16465441297157','BILL2202_001'),
('SSY_16465441297157_002',15,1,15,'16465441297157','BILL2202_002'),
('SSY_16465441297157_003',5,1,5,'16465441297157','BILL2202_003'),
('SSY_16465441297157_004',900,1,900,'16465441297157','BILL2202_004'),
('SSY_16465441297157_005',23.9,1,23.9,'16465441297157','BILL2202_005'),
('SSY_16465441297157_006',36,1,36,'16465441297157','BILL2202_006'),
('SSY_16465441297157_007',91.23,1,91.23,'16465441297157','BILL2202_007'),
('SSY_16465441297157_008',69.9,1,69.9,'16465441297157','BILL2202_008');
INSERT INTO `t_order_details`(details_code,unit_price,`count`,`amount`,order_code,commodity_code)
VALUES
('SSY_16465589745841_001',5,2,10,'16465589745841','BILL2202_003'),
('SSY_16465589745841_002',91.23,1,91.23,'16465589745841','BILL2202_007');
