CREATE TABLE IF NOT EXISTS webdav_users (
   id INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(50) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,                      --- 存储MD5加密后的密码
   user_group VARCHAR(50) NOT NULL DEFAULT 'user',      --- 用户组/角色
   status INT NOT NULL DEFAULT 0,                       --- 用户状态：0-有效, 1-停用
   webdav_token VARCHAR(255),                           --- webdav的登录认证
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ;

CREATE TABLE IF NOT EXISTS folders (
     folder_id VARCHAR(36) PRIMARY KEY,                 -- 使用UUID作为主键
     user_id INT NOT NULL,                              -- 所属用户ID
     parent_id VARCHAR(36),                             -- 父文件夹ID，用于构建层级结构
     name VARCHAR(255) NOT NULL,                        -- 文件夹名称
     path TEXT NOT NULL,                                -- 完整路径
     is_public BOOLEAN DEFAULT FALSE,                   -- 是否公开
     is_group_public BOOLEAN DEFAULT FALSE,             -- 用户组内是否公开
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS files (
       file_id VARCHAR(36) PRIMARY KEY,              -- 使用UUID作为主键
       folder_id VARCHAR(36) NOT NULL,               -- 所属文件夹ID
       user_id INT NOT NULL,                         -- 所属用户ID
       name VARCHAR(255) NOT NULL,                   -- 文件名
       access_token VARCHAR(64) UNIQUE,              -- 访问token
       share_token VARCHAR(64) UNIQUE,               -- 分享访问token
       is_public BOOLEAN DEFAULT FALSE,              -- 是否公开
       is_group_public BOOLEAN DEFAULT FALSE,        -- 用户组内是否公开
       size BIGINT,                                  -- 文件大小(字节)
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);