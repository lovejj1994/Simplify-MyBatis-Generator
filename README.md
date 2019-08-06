# Simplify-MyBatis-Generator
基于 Mapper(通用mapper) 的MyBatis Generator Plugin ，额外添加lombok的支持，如果项目同时用 MyBatis+通用Mapper+Lombok，可以使用这个MyBatis代码生成工具

## 使用教程
* 可以clone该项目或者下载包，在resources文件夹有必备环境,这些文件放在同一个目录里
* 打开 execute-training-infra-monitor-monitor.bat。 替换#{本地路径} 为当前这些文件的绝对路径
* 打开 generatorConfig-infra-monitor-monitor.xml。 
    + 替换 #{mysql驱动文件} 为 mysql-connector-java-8.0.11.jar的绝对路径，你也可以用自己版本的驱动
    + 替换 #{mysqlUrl}，#{account}，#{password} 为数据库连接信息。
    + 替换 #{JAVA vo类包名}，#{JAVA Mapper文件包名}  为 项目的包名
    + 替换 #{JAVA vo类名}， #{表名} 等MyBatis基本生成信息
    + 替换 #{本地生成路径} 。
* 执行 execute-training-infra-monitor-monitor.bat 文件，文件就在 #{本地生成路径} 生成好了。
