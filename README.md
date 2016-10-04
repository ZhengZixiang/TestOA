# TestOA
基于SSH的OA小项目练习

#### 2016/10/4更新
* 完善权限管理中模块、角色、用户模块的前后端逻辑，修复部分后端代码的BUG
* 调整了前端首页的文件结构
* 引入dom4j、jaxen、saxpath等jar包
* 新增数据初始化模块，通过读取init_datas.xml的形式实现初始化数据注入数据库

#### 2016/10/3更新
* 加入权限管理模块并完善其后端从manager到dao的逻辑，包括模块、角色、用户等模型
* 对权限各个模型进行了hibernate的OR映射
* 加入了AccessControlList和UserRoleMapping两个模型来实现权限逻辑模块的剥离，后端逻辑尚未完成

#### 2016/10/2更新
* 新增人员管理模块，重构部分代码

#### 2016/9/27更新
* 进一步对项目分层，增加DAO层，对Organization后台模块进行了代码重构
* 考虑到分页逻辑在各个模块的通用性，此版本进一步剥离了分页逻辑，新增了PagerDao
</br>

#### 2016/9/26更新
* 此版本引入了pager-taglib相关jar包，以增加对Organization模块展示页面的分页处理。
* 为增强代码可读性和逻辑性，针对Organization模块的分页逻辑添加了DTO层，考虑到分页的通用性，下个版本将进一步剥离分页逻辑
* 将公用的标签库包装到common.jsp中
* 修复了部分逻辑问题和BUG

