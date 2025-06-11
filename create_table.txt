CREATE OR REPLACE SEQUENCE `sq_comments` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1 nocycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `sq_address` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1 nocycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `sq_delivery` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1 nocycle ENGINE=InnoDB;


-- shopping.cart definition

CREATE TABLE `cart` (
  `userId` varchar(100) DEFAULT NULL,
  `productId` varchar(100) DEFAULT NULL,
  `cnt` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.chat_history definition

CREATE TABLE `chat_history` (
  `cdate` timestamp NULL DEFAULT current_timestamp(),
  `message` text DEFAULT NULL,
  `sender` varchar(100) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  KEY `chat_history_userId_IDX` (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.comments definition

CREATE TABLE `comments` (
  `commentId` int(11) DEFAULT nextval(`shopping`.`sq_comments`) COMMENT '댓글',
  `rating` int(11) DEFAULT NULL COMMENT '평점',
  `content` text DEFAULT NULL COMMENT '내용',
  `title` varchar(100) DEFAULT NULL COMMENT '제목',
  `productId` varchar(100) DEFAULT NULL COMMENT '제품키',
  `cdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `userId` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.courierCompanyCode definition

CREATE TABLE `courierCompanyCode` (
  `companyName` varchar(100) DEFAULT NULL,
  `companyCode` varchar(100) DEFAULT NULL,
  `international` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.deliveryAddressList definition

CREATE TABLE `deliveryAddressList` (
  `userId` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `defaultYn` varchar(100) DEFAULT NULL,
  `phon` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `deliveryAddressId` int(11) NOT NULL DEFAULT nextval(`shopping`.`sq_address`),
  `postcode` varchar(100) DEFAULT NULL,
  `detailAddress` varchar(100) DEFAULT '',
  `extraAddress` varchar(100) DEFAULT '',
  PRIMARY KEY (`deliveryAddressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.deliveryDetail definition

CREATE TABLE `deliveryDetail` (
  `productId` varchar(100) DEFAULT NULL,
  `cnt` text DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `productName` varchar(100) DEFAULT NULL,
  `deliveryId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.deliveryList definition

CREATE TABLE `deliveryList` (
  `address` varchar(100) DEFAULT NULL COMMENT '보내는 주소',
  `totalAmount` int(11) DEFAULT NULL COMMENT '총 가격',
  `userId` varchar(100) DEFAULT NULL COMMENT '사용자 아이디',
  `cdate` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '주문 날짜',
  `status` varchar(100) DEFAULT '미결제' COMMENT '주문 상태',
  `deliveryId` int(11) NOT NULL DEFAULT nextval(`shopping`.`sq_delivery`) COMMENT '주문키',
  `name` varchar(100) DEFAULT NULL,
  `phon` varchar(100) DEFAULT NULL,
  `request` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `tid` varchar(100) DEFAULT NULL,
  `trackingCode` varchar(100) DEFAULT NULL,
  `companyName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`deliveryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.products definition

CREATE TABLE `products` (
  `productName` varchar(100) DEFAULT NULL COMMENT '제품이름',
  `price` bigint(20) DEFAULT NULL COMMENT '가격',
  `category` varchar(100) DEFAULT NULL COMMENT '제품유형',
  `explanation` varchar(100) DEFAULT NULL COMMENT '제풀설명',
  `productId` varchar(100) DEFAULT NULL COMMENT '제품 고유값',
  `saleYn` varchar(100) DEFAULT 'Y' COMMENT '판매여부',
  `img` varchar(100) DEFAULT NULL,
  `discount` int(11) DEFAULT 0 COMMENT '할인가',
  `numberOfSalse` bigint(20) DEFAULT 0 COMMENT '판매개수',
  `smallCategory` varchar(100) DEFAULT NULL,
  `cnt` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.replay definition

CREATE TABLE `replay` (
  `replayId` varchar(100) DEFAULT NULL,
  `commentId` varchar(100) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `writeId` varchar(100) DEFAULT NULL COMMENT '작성자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.tmpCart definition

CREATE TABLE `tmpCart` (
  `userId` varchar(100) DEFAULT NULL,
  `productId` varchar(100) DEFAULT NULL,
  `cnt` text DEFAULT NULL,
  `keyData` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- shopping.`user` definition

CREATE TABLE `user` (
  `name` varchar(100) DEFAULT NULL COMMENT '이름',
  `userId` varchar(100) DEFAULT NULL COMMENT '아이디',
  `address` varchar(100) DEFAULT NULL COMMENT '주소',
  `phonNumber` varchar(100) DEFAULT NULL COMMENT '핸드폰 번호',
  `gender` varchar(100) DEFAULT NULL COMMENT '성별',
  `password` varchar(100) DEFAULT NULL COMMENT '비밀번호',
  `yyyymmdd` date DEFAULT NULL COMMENT '생년월일',
  `email` varchar(100) DEFAULT NULL COMMENT '이메일',
  `deleteYn` varchar(100) DEFAULT 'N' COMMENT '삭제여부',
  `postcode` varchar(100) DEFAULT NULL,
  `detailAddress` varchar(100) DEFAULT NULL,
  `extraAddress` varchar(100) DEFAULT NULL,
  `partnerUserId` varchar(100) DEFAULT concat('user',nextval(`shopping`.`sq_delivery`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;